<?php

namespace App\Http\Controllers\ShipRocket;

use App\Enums\{CommonStatusEnum, OrderStatusEnum};
use App\Http\Controllers\Controller;
use App\Http\Helpers\{ErrorHelper, ErrorResponse, Helper};
use App\Http\Requests\PickupSheduleRequest;
use App\Http\Requests\Shiprocket\{ApiSecretKeyRequest, SellerPickupAddressRequest, ShiprocketIdRequest};
use App\Models\{CustomerShippingAddress, GuestShippingAddress, Orders, Product, Seller, SellerShipment, User, Warehouse};
use Illuminate\Support\Facades\{Http, File, Notification, Auth};
use App\Notifications\LablePdfLinkNotification;
use App\Notifications\SellerShipmentNotification;
use GuzzleHttp\Client;
use Illuminate\Http\Request;
use Exception;

class Shiprocket extends Controller
{

  public function __construct()
  {
    $admin = auth()->guard('admin')->user();

    if ($admin && !$admin->hasPermissionTo('order_management')) {
      return  ErrorResponse::sendErrorResponse("You do not have permission", 401);
    }
  }

  public function check_order($id)
  {

    $order = Orders::where('id', $id)->first();

    if (!$order) return Helper::sendErrorResponse(404, "Order not found.");
    return $order;
  }

  public function check_warehouse($id)
  {
    $warehouse = Warehouse::find($id);
    if (!$warehouse) return Helper::sendErrorResponse(404, "warehouse does not exist.");
  }

  public function check_customer($id)
  {
    $customer = User::find($id);
    if (!$customer) return Helper::sendErrorResponse(404, "Customer does not exist");
  }

  public function check_product($id)
  {
    $product = Product::find($id);
    if (!$product) return Helper::sendErrorResponse(404, "Product does not exist.");
  }
  public function check_shipping_address($id)
  {
    $shipping_address = CustomerShippingAddress::find($id);
    if (!$shipping_address) return Helper::sendErrorResponse(404, "Shipping address does not exist");
  }

  // create and store shiprocket auth token
  public function token(ApiSecretKeyRequest $request)
  {
    // Get the email and password from the request
    $email = $request->input('email');
    $password = $request->input('password');

    try {   // Send a POST request to the ShipRocket API to authenticate

      $response = Http::post('https://apiv2.shiprocket.in/v1/external/auth/login', [
        'email' => $email,
        'password' => $password,
      ]);

      // Extract the authentication token from the response
      $newToken = $response['token'];
      $envFile = base_path('.env');
      $oldToken = env('SHIPROCKET_API_KEY');
      $newEnvFile = str_replace("SHIPROCKET_API_KEY={$oldToken}", "SHIPROCKET_API_KEY={$newToken}", File::get($envFile));

      File::put($envFile, $newEnvFile);

      return Helper::sendSuccessResponse(200, 'Shiprocket new token has been created and added successfully.');
    } catch (Exception $e) {
      return Helper::sendErrorResponse(400, "Invalid Credantials ", $e);
    }
  }
  public static function auto_token()
  {
    try {   // Send a POST request to the ShipRocket API to authenticate

      $response = Http::post('https://apiv2.shiprocket.in/v1/external/auth/login', [
        'email' => env('SHIPROCKET_EMAIL'),
        'password' => env('SHIPROCKET_PASSWORD'),
      ]);

      // Extract the authentication token from the response
      $newToken = $response['token'];
      $envFile = base_path('.env');
      $oldToken = env('SHIPROCKET_API_KEY');
      $newEnvFile = str_replace("SHIPROCKET_API_KEY={$oldToken}", "SHIPROCKET_API_KEY={$newToken}", File::get($envFile));

      File::put($envFile, $newEnvFile);

      return Helper::sendSuccessResponse(404, 'Please try again.');
    } catch (Exception $e) {
      return Helper::sendErrorResponse(400, "Something went wrong please retry after some time.", $e);
    }
  }
  // check service availability and shipping cost
  public static function service_availability($pickup_postcode, $delivery_postcode, $weight, $length, $width, $height)
  {
    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . env('SHIPROCKET_API_KEY'),
    ])->get('https://apiv2.shiprocket.in/v1/external/courier/serviceability/', [

      'pickup_postcode' => $pickup_postcode,
      'delivery_postcode' => $delivery_postcode,
      'cod' => 0,
      'weight' => $weight,
      'height' => $height,
      'breadth' => $width,
      'length' => $length,
    ]);
    if (isset($response['status_code'])) {
      if ($response['status_code'] == 401)
        if ($response['message'] == 'Token has expired' || str_contains($response['message'], 'Could not decode token')) {
          try {   // Send a POST request to the ShipRocket API to authenticate

            $response = Http::post('https://apiv2.shiprocket.in/v1/external/auth/login', [
              'email' => env('SHIPROCKET_EMAIL'),
              'password' => env('SHIPROCKET_PASSWORD'),
            ]);

            // Extract the authentication token from the response
            $newToken = $response['token'];
            $envFile = base_path('.env');
            $oldToken = env('SHIPROCKET_API_KEY');
            $newEnvFile = str_replace("SHIPROCKET_API_KEY={$oldToken}", "SHIPROCKET_API_KEY={$newToken}", File::get($envFile));

            File::put($envFile, $newEnvFile);

            return Helper::sendSuccessResponse(404, 'Please retry now.');
          } catch (Exception $e) {
            return Helper::sendErrorResponse(400, "Something went wrong please retry after some time.", $e);
          }
        }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);

      if ($response['status_code'] == 422)
        return Helper::sendErrorResponse($response['status_code'], $response['message'], $response['errors']);
    }
    if (isset($response['status'])) {
      if ($response['status'] == 404) return Helper::sendErrorResponse($response['status'], $response['message']);
    }
    $data = [
      'courier_company_id' => $response['data']['available_courier_companies'][0]['courier_company_id'],
      'shipping_cost' => $response['data']['available_courier_companies'][0]['freight_charge'],
      'status' => $response['status']
    ];

    return Helper::sendSuccessResponse($response['status'], "Data retrived successfully.", $data);
  }

  //warehouse to customer order
  public function customer_order(SellerPickupAddressRequest $request)
  {
    $this->check_order($request->order_id);
    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      // ->where('payment_status', CommonStatusEnum::CAPTURED)//this is on hold now
      ->first();

    $this->check_warehouse($request->warehouse_id);
    $warehouse = Warehouse::find($request->warehouse_id);

    $billing_customer_name = null;
    $billing_last_name = null;
    $billing_address = null;
    $billing_address_2 = null;
    $billing_city = null;
    $billing_state = null;
    $billing_pincode = null;
    $billing_phone = null;
    $billing_email = null;

    if ($order->customer_type == 'registered') {

      $this->check_customer($order->customer_id);
      $customer = User::find($order->customer_id);

      $this->check_shipping_address($order->shipping_addresses_id);
      $shipping_address = CustomerShippingAddress::find($order->shipping_addresses_id);

      $billing_customer_name = $customer->f_name;
      $billing_last_name = $customer->l_name;
      $billing_address = $shipping_address->street_address;
      $billing_address_2 = $shipping_address->street_address;
      $billing_city = $shipping_address->city;
      $billing_state = $shipping_address->state;
      $billing_pincode =  $shipping_address->zip_code;
      $billing_phone = $shipping_address->phone_number;
      $billing_email = $shipping_address->contact_email;
    } else if ($order->customer_type == 'guest') {
      $guest = GuestShippingAddress::find($order->guest_shipping_addresses_id);
      $billing_customer_name = $guest->name;
      $billing_last_name = $guest->name;
      $billing_address = $guest->address;
      $billing_address_2 = $guest->address;
      $billing_city = $guest->city;
      $billing_state = $guest->state;
      $billing_pincode = $guest->pincode;
      $billing_phone = $guest->phone;
      $billing_email = $guest->email;
    }

    $this->check_product($order->product_id);
    $product = Product::find($order->product_id);

    $order_data = [
      'order_id' => $order->order_id . "2",
      'order_date' => $order->created_at,
      'channel_id' => env('SHIPROCKET_CHANNEL_ID'),
      'pickup_location' => $warehouse->warehouse_name,
      'billing_customer_name' => $billing_customer_name,
      'billing_last_name' => $billing_last_name,
      'billing_address' => $billing_address,
      'billing_address_2' => $billing_address_2,
      'billing_city' => $billing_city,
      'billing_pincode' => $billing_pincode,
      'billing_state' => $billing_state,
      'billing_country' => "India",
      'billing_email' => $billing_email,
      'billing_phone' => $billing_phone,
      'shipping_is_billing' => true,

      'order_items' => [
        [
          'name' => $product->name,
          'sku' => $product->sku,
          'units' => 1,
          'selling_price' => $product->selling_price,
          'discount' => 0,
          // 'tax' => 0,
          // 'hsn' => "093393"
        ]
      ],
      'payment_method' => "prepaid", // this data will come from payment table
      'shipping_charges' => $order->shipping_cost,
      'giftwrap_charges' => 0,
      'transaction_charges' => 0,
      'total_discount' => 0,
      'sub_total' => $order->order_amount,

      'length' => $product->length,
      'breadth' => $product->width,
      'height' => $product->height,
      'weight' => $product->weight,
    ];

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' .  env('SHIPROCKET_API_KEY'),

    ])->post('https://apiv2.shiprocket.in/v1/external/orders/create/adhoc', $order_data);

    if ($response->successful()) {
      $order->shiprocket_order_id = $response['order_id'];
      $order->shiprocket_shipment_id = $response['shipment_id'];
      $order->shiprocket_status = $response['status'];
      $order->save();
    } else {
      if ($response['message'] == 'Token has expired' || str_contains($response['message'], 'Could not decode token')) {
        return $this->auto_token();
      }

      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }

    return Helper::sendSuccessResponse(201, 'Order created successfully.', $response->json());
  }

  //seller to warehouse 
  public function seller_order(SellerPickupAddressRequest $request)
  {

    $this->check_order($request->order_id);
    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      ->where('payment_status', CommonStatusEnum::CAPTURED)
      ->first();

    $this->check_warehouse($request->warehouse_id);
    $warehouse = Warehouse::find($request->warehouse_id);

    $this->check_product($order->product_id);
    $product = Product::find($order->product_id);

    $seller = Seller::find($product->seller_id);
    if (!$seller) {
      return ErrorHelper::sendError("seller does not exist", 404);
    }

    $shipping_service_data  = $this->service_availability($seller->zip, $warehouse->pin_code, $product->weight, $product->length, $product->width, $product->height);
    if ($shipping_service_data->getData()->status != 200) {
      return $shipping_service_data;
    }
    $order->seller_freight_price = $shipping_service_data->getData()->data->shipping_cost;
    $order->seller_courier_company_id = $shipping_service_data->getData()->data->courier_company_id;
    $order->save();

    if ($seller->pickup_location == null) return Helper::sendErrorResponse('409', 'Please create pickup locaiton of seller before order.');
    $order_data = [
      'order_id' => $order->order_id . "1",
      'order_date' => $order->created_at,
      'pickup_location' => $seller->pickup_location,
      'channel_id' => env('SHIPROCKET_CHANNEL_ID'),
      'billing_customer_name' => $warehouse->contact_person_name,
      'billing_last_name' => $warehouse->contact_person_name,
      'billing_address' => $warehouse->address,
      'billing_address_2' => $warehouse->address_2,
      'billing_city' => $warehouse->city,
      'billing_pincode' => $warehouse->pin_code,
      'billing_state' => $warehouse->state,
      'billing_country' => "India",
      'billing_email' => $warehouse->email,
      'billing_phone' => $warehouse->phone,
      'shipping_is_billing' => true,
      'order_items' => [
        [
          'name' => $product->name,
          'sku' => $product->sku,
          'units' => 1,
          'selling_price' => $product->selling_price,
          'discount' => 0,
          // 'tax' => 15,
          // 'hsn' => "093393"
        ]
      ],
      'payment_method' => "prepaid", // this data will come from payment table
      'shipping_charges' => $shipping_service_data->getData()->data->shipping_cost,
      'giftwrap_charges' => 0,
      'transaction_charges' => 0,
      'total_discount' => 0,
      'sub_total' => $product->selling_price,

      'length' => $product->length,
      'breadth' => $product->width,
      'height' => $product->height,
      'weight' => $product->weight,
    ];



    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . env('SHIPROCKET_API_KEY'),

    ])->post('https://apiv2.shiprocket.in/v1/external/orders/create/adhoc', $order_data);

    // return $response;
    if ($response->successful()) {
      $order->shiprocket_order_id = $response['order_id'];
      $order->shiprocket_shipment_id = $response['shipment_id'];
      $order->shiprocket_status = $response['status'];
      $order->save();
    } else {
      if ($response['message'] == 'Token has expired' || str_contains($response['message'], 'Could not decode token')) {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response->json());
    }

    return Helper::sendSuccessResponse(201, 'Data retrive successfully.', $response->json());
  }



  // create order and shipment in shiprocket for C2B 
  public function seller_to_admin_ship(SellerPickupAddressRequest $request)
  {
    $order = $this->check_order($request->order_id);
    return $order;
    $check_payment_status = Orders::where('id', $request->order_id)->where('payment_status', CommonStatusEnum::CAPTURED)
      ->first();
    if ($check_payment_status == null) return Helper::sendErrorResponse(405, 'Payment is not captured for this order.');


    $order = Orders::find($request->order_id);
    if ($order->order_status == CommonStatusEnum::SELLER_SHIPMENT_CREATED) {
      return Helper::sendErrorResponse(405, 'This shipment is already created AWB number -> ' . $order->seller_awb_code);
    }

    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      ->first();


    $warehouse = Warehouse::find($request->warehouse_id);

    $product = Product::find($order->product_id);

    if ($product->added_by == "admin") {

      return ErrorHelper::sendError("This is in house product", 410);
    }

    $seller = Seller::find($product->seller_id);

    $shipping_service_data  = $this->service_availability($seller->zip, $warehouse->pin_code, $product->weight, $product->length, $product->width, $product->height);
    if ($shipping_service_data->getData()->status != 200) {
      return $shipping_service_data;
    }

    $shipping_cost = $shipping_service_data->getData()->data->shipping_cost;
    $courier_company_id = $shipping_service_data->getData()->data->courier_company_id;

    $order->seller_freight_price = $shipping_cost;
    $order->seller_courier_company_id = $courier_company_id;
    $order->save();


    $pickup_name = $seller->f_name . substr(str_replace(' ', '', $seller->street_address), 0, 2) . substr(str_replace(' ', '', $seller->zip), 3, 5) .
      substr(str_replace(' ', '', $seller->phone), 0, 3);
    $billing_customer_name = explode(' ', $warehouse->contact_person_name);

    $seller->pickup_location = $pickup_name;
    $seller->save();


    $order_data = [
      "order_id" => $order->order_id . "1",
      "order_date" => $order->created_at,
      "company_name" => "Joy Rejoy",
      'channel_id' => env('SHIPROCKET_CHANNEL_ID'),
      'billing_customer_name' => $billing_customer_name[0],
      'billing_last_name' => isset($billing_customer_name[1]) ? $billing_customer_name[1] : $billing_customer_name[0],
      'billing_address' => $warehouse->address,
      'billing_address_2' => $warehouse->address_2,
      'billing_city' => $warehouse->city,
      'billing_pincode' => $warehouse->pin_code,
      'billing_state' => $warehouse->state,
      'billing_country' => "India",
      'billing_email' => $warehouse->email,
      'billing_phone' => $warehouse->phone,
      // "billing_alternate_phone" => $warehouse->phone,
      "shipping_is_billing" => true,

      "order_items" => [
        [
          "name" => $product->name,
          "sku" => $product->sku,
          "units" => "1",
          "selling_price" => $product->selling_price,
        ]
      ],

      "payment_method" => "prepaid",
      "sub_total" => $product->selling_price,
      "shipping_charges" => $order->seller_freight_price,
      "weight" => $product->weight,
      "length" => $product->length,
      "breadth" => $product->width,
      "height" => $product->height,
      "pickup_location" => $pickup_name,
      "vendor_details" => [
        "email" => $seller->email,
        "phone" => $seller->phone,
        "name" => $seller->f_name,
        "address" => $seller->street_address,
        "city" => $seller->city,
        "state" => $seller->state,
        "country" => "India",
        "pin_code" => $seller->zip,
        "pickup_location" => $pickup_name
      ]
    ];


    $response = HTTP::withheaders([
      'content-type' => 'application/json',
      'Authorization' => 'bearer ' . env('SHIPROCKET_API_KEY'),
    ])->post('https://apiv2.shiprocket.in/v1/external/shipments/create/forward-shipment', $order_data);



    if (isset($response['status_code'])) {
      return Helper::sendErrorResponse($response['status_code'], $response['message'], $response['errors']);
    }

    if (isset($response['status'])) {
      if ($response['status'] == 1) {


        $data = [
          'label' => $response['payload']['label_url'], // need to send this to the seller by email
          'manifest' => $response['payload']['manifest_url'],
          'pickup_scheduled_date' => $response['payload']['pickup_scheduled_date'],
        ];


        $seller_shipment = SellerShipment::create([
          'order_id' => $order->id,
          'seller_id' => $seller->id,
          'shiprocket_order_id' => $response['payload']['order_id'],
          'shiprocket_shipment_id' => $response['payload']['shipment_id'],
          'awb_code' => $response['payload']['awb_code'],
          'label' => $response['payload']['label_url'],
          'manifest' => $response['payload']['manifest_url'],
          'courier_company_id' => $courier_company_id,
          'freight_price' => $shipping_cost,
          'pickup_location' => $pickup_name,
          'date_of_shipment' =>  $response['payload']['pickup_scheduled_date']
        ]);

        $order->seller_awb_code = $response['payload']['awb_code'];
        $order->pickup_location = $pickup_name;
        $order->order_status = CommonStatusEnum::SELLER_SHIPMENT_CREATED;
        $order->save();

        return Helper::sendSuccessResponse(201, 'Order & shipment created successfully.', $seller_shipment);
      } elseif ($response['status'] == 0) {

        return Helper::sendErrorResponse(405, $response['payload']['error_message'], $response->json());
      } else {
        if ($response['message'] == 'Token has expired' || str_contains($response['message'], 'Could not decode token')) {
          return $this->auto_token();
        }
        return Helper::sendErrorResponse(409, 'Unable to create order.', $response->json());
      }
    }
  }

  // admin to customer shipment wrapper api 
  public function admin_to_customer_ship(SellerPickupAddressRequest $request)
  {
    $check_payment_status = Orders::where('id', $request->order_id)->where('payment_status', CommonStatusEnum::CAPTURED)
      ->first();
    if ($check_payment_status == null) return Helper::sendErrorResponse(405, 'Payment is not captured for this order.');

    $this->check_order($request->order_id);
    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      ->where('payment_status', CommonStatusEnum::CAPTURED)
      ->first();

    if ($order == null) {
      return Helper::sendErrorResponse(404, 'Order not available.');
    }
    $warehouse = Warehouse::find($request->warehouse_id);

    $product = Product::find($order->product_id);

    // if ($product->added_by == "admin") {

    //   return ErrorHelper::sendError("This is in house product", 410);
    // }


    $customer = User::find($order->customer_id);
    $customer_shipping_address = CustomerShippingAddress::find($order->shipping_addresses_id);
    if (!$customer_shipping_address) return Helper::sendErrorResponse(404, 'Customer shipping address not found.');


    $shipping_service_data  = $this->service_availability($warehouse->pin_code, $customer_shipping_address->zip_code, $product->weight, $product->length, $product->width, $product->height);
    if ($shipping_service_data->getData()->status != 200) {
      return $shipping_service_data;
    }

    $shipping_cost = $shipping_service_data->getData()->data->shipping_cost;
    $courier_company_id = $shipping_service_data->getData()->data->courier_company_id;

    $order->shipping_cost = $shipping_cost;
    $order->courier_company_id = $courier_company_id;
    $order->save();




    $billing_customer_name = explode(' ', $customer_shipping_address->contact_person);
    $order_data = [
      "order_id" => $order->order_id . "2",
      "order_date" => $order->created_at,
      "company_name" => "Joy Rejoy",
      'channel_id' => env('SHIPROCKET_CHANNEL_ID'),
      'billing_customer_name' =>  $billing_customer_name[0],
      'billing_last_name' => isset($billing_customer_name[1]) ? $billing_customer_name[1] : $billing_customer_name[0],
      'billing_address' => $customer_shipping_address->street_address,
      'billing_address_2' => $customer_shipping_address->landmark,
      'billing_city' => $customer_shipping_address->city,
      'billing_pincode' => $customer_shipping_address->zip_code,
      'billing_state' => $customer_shipping_address->state,
      'billing_country' => "India",
      'billing_email' => $customer_shipping_address->contact_email,
      'billing_phone' => $customer_shipping_address->phone_number,
      "shipping_is_billing" => true,

      "order_items" => [
        [
          "name" => $product->name,
          "sku" => $product->sku,
          "units" => "1",
          // "hsn" => $product->hsn,
          "selling_price" => $order->order_amount,
          "tax" => 18 // need to add dynamic value
        ]
      ],

      "payment_method" => "prepaid",
      "shipping_charges" => $order->shipping_cost,
      "sub_total" => $order->order_amount,
      "weight" => $product->weight,
      "length" => $product->length,
      "breadth" => $product->width,
      "height" => $product->height,
      "pickup_location" => $warehouse->warehouse_name

    ];

    $response = HTTP::withheaders([
      'content-type' => 'application/json',
      'Authorization' => 'bearer ' . env('SHIPROCKET_API_KEY'),
    ])->post('https://apiv2.shiprocket.in/v1/external/shipments/create/forward-shipment', $order_data);



    if (isset($response['status_code'])) {
      return Helper::sendErrorResponse($response['status_code'], $response['message'], $response['errors']);
    }

    if (isset($response['status'])) {
      if ($response['status'] == 1) {


        $data = [
          'label' => $response['payload']['label_url'], // need to send this to the seller by email
          'manifest' => $response['payload']['manifest_url'],
          'pickup_scheduled_date' => $response['payload']['pickup_scheduled_date'],
        ];


        $seller_shipment = SellerShipment::create([
          'order_id' => $order->id,
          'admin_id' => Auth::id(),
          'shiprocket_order_id' => $response['payload']['order_id'],
          'shiprocket_shipment_id' => $response['payload']['shipment_id'],
          'awb_code' => $response['payload']['awb_code'],
          'label' => $response['payload']['label_url'],
          'manifest' => $response['payload']['manifest_url'],
          'courier_company_id' => $courier_company_id,
          'freight_price' => $shipping_cost,
          'pickup_location' => $warehouse->warehouse_name,
          'date_of_shipment' =>  $response['payload']['pickup_scheduled_date']
        ]);

        $order->customer_awb_code = $response['payload']['awb_code'];
        $order->save();

        return Helper::sendSuccessResponse(201, 'Order & shipment created successfully.', $seller_shipment);
      } elseif ($response['status'] == 0) {

        return Helper::sendErrorResponse(405, $response['payload']['error_message'], $response->json());
      } else {
        if ($response['message'] == 'Token has expired' || str_contains($response['message'], 'Could not decode token')) {
          return $this->auto_token();
        }
        return Helper::sendErrorResponse(409, 'Unable to create order.', $response->json());
      }
    }
  }

  // generate awb id. This is api is now on hold because implemented wrapper api 
  // public function awb(ShiprocketIdRequest $request)
  // {

  //   $this->check_order($request->order_id);
  //   $order = Orders::where('id', $request->order_id)
  //     ->where('order_status', OrderStatusEnum::CONFIRMED)
  //     // ->where('payment_status', CommonStatusEnum::CAPTURED)
  //     ->first();
  //   $response = Http::withHeaders([
  //     'Content-Type' => 'application/json',
  //     'Authorization' => 'Bearer ' . env('SHIPROCKET_API_KEY'),
  //   ])->post('https://apiv2.shiprocket.in/v1/external/courier/assign/awb', [
  //     'shipment_id' => $order->shiprocket_shipment_id,
  //     'courier_company_id' => $order->courier_company_id,
  //   ]);
  //   if (isset($response['awb_assign_status'])) {
  //     if ($response['awb_assign_status'] == 0) {
  //       return Helper::sendErrorResponse(404, $response['response']['data']['awb_assign_error'], $response['response']['data']);
  //     }
  //   }
  //   if (isset($response['status_code'])) {
  //     if ($response['message'] == 'Token has expired') {
  //       return $this->auto_token();
  //     }
  //     return Helper::sendErrorResponse($response['status_code'], $response['message']);
  //   }

  //   $order->awb_code = $response['response']['data']['awb_code'];
  //   $order->delivery_service_name = $response['response']['data']['courier_name'];
  //   $order->save();

  //   // return $shiprocket_response;
  //   return Helper::sendSuccessResponse(201, 'AWB created successfully.', $response['response']['data']);
  // }

  //generate invoice
  public function invoice(ShiprocketIdRequest $request)
  {

    $this->check_order($request->order_id);
    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      // ->where('payment_status', CommonStatusEnum::CAPTURED) //this is on hold now
      ->first();

    $shiprocket_order_id = $order->shiprocket_order_id;

    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->post('https://apiv2.shiprocket.in/v1/external/orders/print/invoice', [
      "ids" => [$shiprocket_order_id],
    ]);

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired' || str_contains($response['message'], 'Could not decode token')) {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }

    if (isset($response['is_invoice_created'])) {

      if ($response['is_invoice_created']) {

        $invoice_pdf_link = $response['invoice_url'];
        return Helper::sendSuccessResponse(201, "Invoice generated successfully.", $invoice_pdf_link);
      } else if ($response['is_invoice_created'] == false) {
        return Helper::sendSuccessResponse(404, 'Unable to create invoice.');
      }
    }
  }

  //generate invoice for seller
  public function seller_invoice(ShiprocketIdRequest $request)
  {
    $this->check_order($request->order_id);
    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      ->where('payment_status', CommonStatusEnum::CAPTURED)
      ->first();

    $seller_shipment = SellerShipment::where('awb_code', $order->seller_awb_code)
      ->where('order_id', $request->order_id)->first();

    if (!$seller_shipment) return Helper::sendErrorResponse(404, 'awb is not created yet.');

    $shiprocket_order_id = $seller_shipment->shiprocket_order_id;


    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->post('https://apiv2.shiprocket.in/v1/external/orders/print/invoice', [
      "ids" => [$shiprocket_order_id],
    ]);

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired' || str_contains($response['message'], 'Could not decode token')) {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }

    if (isset($response['is_invoice_created'])) {

      if ($response['is_invoice_created']) {

        $invoice_pdf_link = $response['invoice_url'];
        $seller_shipment->invoice = $invoice_pdf_link;
        $seller_shipment->save();
        return Helper::sendSuccessResponse(201, "Invoice generated successfully.", $invoice_pdf_link);
      } else if ($response['is_invoice_created'] == false) {

        return Helper::sendSuccessResponse(404, 'Unable to create invoice.');
      }
    }
  }
  //generate invoice for customer
  public function customer_invoice(ShiprocketIdRequest $request)
  {
    $this->check_order($request->order_id);
    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      ->where('payment_status', CommonStatusEnum::CAPTURED)
      ->first();

    $seller_shipment = SellerShipment::where('awb_code', $order->customer_awb_code)
      ->where('order_id', $request->order_id)->first();

    if (!$seller_shipment) return Helper::sendErrorResponse(404, 'awb is not created yet.');

    $shiprocket_order_id = $seller_shipment->shiprocket_order_id;


    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->post('https://apiv2.shiprocket.in/v1/external/orders/print/invoice', [
      "ids" => [$shiprocket_order_id],
    ]);

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired' || str_contains($response['message'], 'Could not decode token')) {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }

    if (isset($response['is_invoice_created'])) {

      if ($response['is_invoice_created']) {

        $invoice_pdf_link = $response['invoice_url'];
        $seller_shipment->invoice = $invoice_pdf_link;
        $seller_shipment->save();
        return Helper::sendSuccessResponse(201, "Invoice generated successfully.", $invoice_pdf_link);
      } else if ($response['is_invoice_created'] == false) {

        return Helper::sendSuccessResponse(404, 'Unable to create invoice.');
      }
    }
  }
  //generate lable 
  public function label(ShiprocketIdRequest $request)
  {

    $this->check_order($request->order_id);
    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      // ->where('payment_status', CommonStatusEnum::CAPTURED) //this is on hold now
      ->first();

    $shipment_id = $order->shiprocket_shipment_id;

    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->post('https://apiv2.shiprocket.in/v1/external/courier/generate/label', [
      "shipment_id" => [$shipment_id],
    ]);

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired') {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }

    if (isset($response['label_created'])) {

      if ($response['label_created'] == 1) {
        $label_pdf_link = $response['label_url'];
        $product  = Product::find($order->product_id);
        $seller = Seller::find($product->seller_id);
        Notification::send($seller, new LablePdfLinkNotification($label_pdf_link));

        return Helper::sendSuccessResponse(201, "Label created successfully.", $label_pdf_link);
      } else {
        return Helper::sendErrorResponse(404, 'Unable to create label.');
      }
    }
  }
  // generate shipment pickup date
  public function schedule_pickup(PickupSheduleRequest $request)
  {

    $this->check_order($request->order_id);
    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      // ->where('payment_status', CommonStatusEnum::CAPTURED) //this is on hold now
      ->first();

    $shipment_id = $order->shiprocket_shipment_id;

    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->post('https://apiv2.shiprocket.in/v1/external/courier/generate/pickup', [
      "shipment_id" => [$shipment_id],
      "pickup_date" => $request->pickup_date //2021-12-10 12:39:54 this is the formate for pickup shedule time
    ]);

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired') {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }
    if (isset($response['pickup_status'])) {
      if ($response['pickup_status'] == 1) {
        return Helper::sendSuccessResponse(201, $response['response']['data'], $response['response']);
      } else {
        return Helper::sendErrorResponse(400, 'Unable to shcedule', $response);
      }
    }

    if (isset($response['status'])) {
      return Helper::sendErrorResponse($response['status'], $response['message']);
    }
  }
  // generate manifest
  public function manifest(ShiprocketIdRequest $request)
  {

    $this->check_order($request->order_id);
    $order = Orders::where('id', $request->order_id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      // ->where('payment_status', CommonStatusEnum::CAPTURED) //this is on hold now
      ->first();


    $shipment_id = $order->shiprocket_shipment_id;

    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->post('https://apiv2.shiprocket.in/v1/external/manifests/generate', [
      "shipment_id" => [$shipment_id],
    ]);

    if (isset($response['status_code'])) {

      if ($response['message'] == 'Token has expired') {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }
    if (isset($response['status'])) {
      if ($response['status'] == 1) {

        return Helper::sendSuccessResponse(201, 'Success', $response['manifest_url']);
      }
      if ($response['status'] == 0) {
        return Helper::sendErrorResponse(400, 'Unable to create manifest');
      }
    }
    return $response;
  }

  //tracking api 
  public function tracking_shipment($id)
  {

    $this->check_order($id);
    $order = Orders::where('id', $id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      // ->where('payment_status', CommonStatusEnum::CAPTURED) //this is on hold now
      ->first();


    $shipment_id = $order->shiprocket_shipment_id;

    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->get('https://apiv2.shiprocket.in/v1/external/courier/track/shipment/' . $shipment_id);

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired') {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }

    if (isset($response['tracking_data'])) {
      if ($response['tracking_data']['track_status'] == 1) {
        $data = [
          'shipment_track' => $response['tracking_data']['shipment_track'],
          'shipment_track_activities' => $response['tracking_data']['shipment_track_activities'],
          'track_url' => $response['tracking_data']['track_url']
        ];
        return Helper::sendSuccessResponse(200, 'Data retrived successfully.', $data);
      } else if ($response['tracking_data']['track_status'] == 0) {
        return Helper::sendErrorResponse(409, $response['tracking_data']['error']);
      }
    }
  }

  // track order by awb id
  public function tracking_by_awb($id)
  {

    $this->check_order($id);
    $order = Orders::where('id', $id)
      ->where('order_status', OrderStatusEnum::CONFIRMED)
      // ->where('payment_status', CommonStatusEnum::CAPTURED)//this is on hold now
      ->first();

    $awb_id = $order->seller_awb_code;
    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->get('https://apiv2.shiprocket.in/v1/external/courier/track/shipment/' . $awb_id);

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired') {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }

    if (isset($response['tracking_data'])) {
      if ($response['tracking_data']['track_status'] == 1) {
        $data = [
          'shipment_track' => $response['tracking_data']['shipment_track'],
          'shipment_track_activities' => $response['tracking_data']['shipment_track_activities'],
          'track_url' => $response['tracking_data']['track_url']
        ];
        return Helper::sendSuccessResponse(200, 'Data retrived successfully.', $data);
      } else if ($response['tracking_data']['track_status'] == 0) {
        return Helper::sendErrorResponse(409, $response['tracking_data']['error']);
      }
    }
  }

  // cancle shiprocket order
  function cancel_seller_shipment(ShiprocketIdRequest $request)
  {

    $order = Orders::find($request->order_id);

    if (!$order) {
      return Helper::sendErrorResponse(404, 'Order not found.');
    }

    $seller_shipment = SellerShipment::where('awb_code', $order->seller_awb_code)
      ->where('order_id', $request->order_id)->first();

    if (!$seller_shipment) return Helper::sendErrorResponse(404, 'Shiprocket order not found.');

    $shiprocket_awb_id = $seller_shipment->awb_code;


    if (!$shiprocket_awb_id) {
      return Helper::sendErrorResponse(404, 'awb is not created yet.');
    }

    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->post('https://apiv2.shiprocket.in/v1/external/orders/cancel/shipment/awbs', [
      'awbs' => [$shiprocket_awb_id]
    ]);
    if (isset($response['status_code'])) {
      return Helper::sendErrorResponse($response['status_code'], $response['message'], $response['errors']);
    }

    $order->order_status = CommonStatusEnum::CONFIRMED;
    $order->save();

    return $response;
  }

  // cancle shiprocket order
  function cancel_customer_shipment(ShiprocketIdRequest $request)
  {

    $order = Orders::find($request->order_id);

    if (!$order) {
      return Helper::sendErrorResponse(404, 'Order not found.');
    }

    $seller_shipment = SellerShipment::where('awb_code', $order->customer_awb_code)
      ->where('order_id', $request->order_id)->first();

    if (!$seller_shipment) return Helper::sendErrorResponse(404, 'Shiprocket order not found.');

    $shiprocket_awb_id = $seller_shipment->awb_code;


    if (!$shiprocket_awb_id) {
      return Helper::sendErrorResponse(404, 'awb is not created yet.');
    }

    $shiprocket_token = env('SHIPROCKET_API_KEY');

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . $shiprocket_token,
    ])->post('https://apiv2.shiprocket.in/v1/external/orders/cancel/shipment/awbs', [
      'awbs' => [$shiprocket_awb_id]
    ]);
    if (isset($response['status_code'])) {
      return Helper::sendErrorResponse($response['status_code'], $response['message'], $response['errors']);
    }
    return $response;
  }

  // pickup warehouse address for admin
  function warehouse_pickup_address($warehouse_id)
  {
    $this->check_warehouse($warehouse_id);
    $warehouse = Warehouse::find($warehouse_id);

    $address_data = [
      'pickup_location' => $warehouse->warehouse_name,
      'name' => $warehouse->contact_person_name,
      'email' => $warehouse->email,
      'phone' => $warehouse->phone,
      'alternate_phone' => $warehouse->alternate_phone,
      'address' => $warehouse->address,
      'city' => $warehouse->city,
      'pin_code' => $warehouse->pin_code,
      'country' => "India",
      'state' => $warehouse->state
    ];

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . env('SHIPROCKET_API_KEY'),
    ])->post('https://apiv2.shiprocket.in/v1/external/settings/company/addpickup', $address_data);

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired') {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message'], $response['errors']);
    }

    $response_data = json_decode($response, true);
    if ($response->successful()) {
      $warehouse->pickup_id = $response_data['pickup_id'];
      $warehouse->save();
      return Helper::sendSuccessResponse(201, 'Pickup location added successfully.', $response_data);
    }
  }

  // store pickup address for seller 
  function seller_pickup_address(SellerPickupAddressRequest $request)
  {
    $order = Orders::find($request->order_id);
    if (!$order) return Helper::sendErrorResponse(404, "order not found.");

    $this->check_product($order->product_id);
    $product = Product::find($order->product_id);
    if ($product->added_by == "admin") return Helper::sendErrorResponse(410, "This is in house product.");

    $seller = Seller::find($product->seller_id);
    if (!$seller) return Helper::sendErrorResponse(404, 'Seller not found.');

    $this->check_warehouse($request->warehouse_id);
    $warehouse = Warehouse::find($request->warehouse_id);
    if (!$warehouse) {
      return ErrorHelper::sendError("warehouse not found.", 404);
    }

    $service_data = $this->service_availability($seller->zip, $warehouse->pin_code, $product->weight, $product->length, $product->width, $product->height);


    if ($service_data->getData()->status == 404) {
      return $service_data;
    }

    $pickup_name = $seller->f_name . substr(str_replace(' ', '', $seller->street_address), 0, 2) . substr(str_replace(' ', '', $seller->zip), 3, 5) .
      substr(str_replace(' ', '', $seller->phone), 0, 3);

    $address_data = [
      'pickup_location' => $pickup_name,
      'name' => $seller->f_name . $seller->l_name,
      'email' => $seller->email,
      'phone' => $seller->phone,
      'address' => $seller->street_address,
      'city' => $seller->city,
      'address_type' => "vendor",
      'pin_code' => $seller->zip,
      'country' => "India",
      'state' => $seller->state
    ];

    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . env('SHIPROCKET_API_KEY'),
    ])->post('https://apiv2.shiprocket.in/v1/external/settings/company/addpickup', $address_data);

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired') {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message']);
    }

    if ($response->successful()) {
      $response_data = json_decode($response, true);
      $seller->pickup_location = $response_data['address']['pickup_code'];
      $seller->pickup_id = $response_data['pickup_id'];
      $seller->save();
    }
    return Helper::sendSuccessResponse(201, 'Data retrive successfully.', $response->json());
  }

  // list of pickup locations
  function all_pickup_location()
  {
    $response = Http::withHeaders([
      'Content-Type' => 'application/json',
      'Authorization' => 'Bearer ' . env('SHIPROCKET_API_KEY'),
    ])->get('https://apiv2.shiprocket.in/v1/external/settings/company/pickup');

    if (isset($response['status_code'])) {
      if ($response['message'] == 'Token has expired') {
        return $this->auto_token();
      }
      return Helper::sendErrorResponse($response['status_code'], $response['message'], $response->json());
    }
    return Helper::sendSuccessResponse(200, 'Data retrieved successfully.', $response->json());
  }


  function seller_to_admin_serviceavailability(Request $request)
  {
    $warehouse = Warehouse::find(env('WAREHOUSE_ID'));


    $seller = Seller::find($request->seller_id);
    if (!$seller) return Helper::sendErrorResponse(404, 'Seller not found.');

    $shipping_service_data  = $this->service_availability($seller->zip, $warehouse->pin_code, $request->weight, $request->length, $request->width, $request->height);
    if ($shipping_service_data->getData()->status != 200) {
      return $shipping_service_data;
    }
    return $shipping_service_data;
  }

  // print manifest
  // public function print_manifest(ShiprocketIdRequest $request)
  // {

  //   $this->check_order($request->order_id);
  //   $order = Orders::where('id', $request->order_id)
  //     ->where('order_status', OrderStatusEnum::CONFIRMED)
  //     ->where('payment_status', CommonStatusEnum::CAPTURED)
  //     ->first();

  //   $shipment_id = $order->shiprocket_order_id;

  //   $shiprocket_token = env('SHIPROCKET_API_KEY');

  //   $response = Http::withHeaders([
  //     'Content-Type' => 'application/json',
  //     'Authorization' => 'Bearer ' . $shiprocket_token,
  //   ])->post('https://apiv2.shiprocket.in/v1/external/manifests/print', [
  //     "shipment_id" => [$shipment_id]
  //   ]);

  //   return $response;
  // }
  // generate seller to admin order and shipment wrapper api 
  // public function seller_to_admin_ship2(SellerPickupAddressRequest $request)
  // {
  //   $check_payment_status = Orders::where('id', $request->order_id)->where('payment_status', CommonStatusEnum::CAPTURED)
  //     ->first();
  //   if ($check_payment_status == null) return Helper::sendErrorResponse(405, 'Payment is not captured for this order.');

  //   $this->check_order($request->order_id);
  //   $order = Orders::where('id', $request->order_id)
  //     ->where('order_status', OrderStatusEnum::CONFIRMED)
  //     ->where('payment_status', CommonStatusEnum::CAPTURED)
  //     ->first();
  //   if ($order == null) {
  //     return Helper::sendErrorResponse(404, 'Order not available.');
  //   }
  //   $warehouse = Warehouse::find($request->warehouse_id);

  //   $product = Product::find($order->product_id);

  //   if ($product->added_by == "admin") {

  //     return ErrorHelper::sendError("This is in house product", 410);
  //   }

  //   $seller = Seller::find($product->seller_id);

  //   $shipping_service_data  = $this->service_availability($seller->zip, $warehouse->pin_code, $product->weight, $product->length, $product->width, $product->height);
  //   if ($shipping_service_data->getData()->status != 200) {
  //     return $shipping_service_data;
  //   }

  //   $shipping_cost = $shipping_service_data->getData()->data->shipping_cost;
  //   $courier_company_id = $shipping_service_data->getData()->data->courier_company_id;

  //   $order->seller_freight_price = $shipping_cost;
  //   $order->seller_courier_company_id = $courier_company_id;
  //   $order->save();


  //   $pickup_name = $seller->f_name . substr(str_replace(' ', '', $seller->street_address), 0, 2) . substr(str_replace(' ', '', $seller->zip), 3, 5) .
  //     substr(str_replace(' ', '', $seller->phone), 0, 3);
  //   $billing_customer_name = explode(' ', $warehouse->contact_person_name);
  //   $seller->pickup_location = $pickup_name;
  //   $seller->save();
  //   $order_data = [
  //     "order_id" => $order->order_id . "1",
  //     "order_date" => $order->created_at,
  //     "company_name" => "Joy Rejoy",
  //     'channel_id' => env('SHIPROCKET_CHANNEL_ID'),
  //     'billing_customer_name' => $billing_customer_name[0],
  //     'billing_last_name' => isset($billing_customer_name[1]) ? $billing_customer_name[1] : $billing_customer_name[0],
  //     'billing_address' => $warehouse->address,
  //     'billing_address_2' => $warehouse->address_2,
  //     'billing_city' => $warehouse->city,
  //     'billing_pincode' => $warehouse->pin_code,
  //     'billing_state' => $warehouse->state,
  //     'billing_country' => "India",
  //     'billing_email' => $warehouse->email,
  //     'billing_phone' => $warehouse->phone,
  //     "billing_alternate_phone" => $warehouse->phone,
  //     "shipping_is_billing" => "1",

  //     "order_items" => [
  //       [
  //         "name" => $product->name,
  //         "sku" => $product->sku,
  //         "units" => "1",
  //         // "hsn" => "4223139",
  //         "selling_price" => $product->selling_price,
  //       ]
  //     ],

  //     "payment_method" => "prepaid",
  //     "sub_total" => $order->order_amount + $order->seller_freight_price,
  //     "weight" => $product->weight,
  //     "length" => $product->length,
  //     "breadth" => $product->width,
  //     "height" => $product->height,
  //     "pickup_location" => $pickup_name,
  //     "vendor_details" => [
  //       "email" => $seller->email,
  //       "phone" => $seller->phone,
  //       "name" => $seller->f_name,
  //       "address" => $seller->street_address,
  //       "city" => $seller->city,
  //       "state" => $seller->state,
  //       "country" => "India",
  //       "pin_code" => $seller->zip,
  //       "pickup_location" => $pickup_name
  //     ]
  //   ];


  //   $response = HTTP::withheaders([
  //     'content-type' => 'application/json',
  //     'Authorization' => 'bearer ' . env('SHIPROCKET_API_KEY'),
  //   ])->post('https://apiv2.shiprocket.in/v1/external/shipments/create/forward-shipment', $order_data);



  //   if (isset($response['status_code'])) {
  //     return Helper::sendErrorResponse($response['status_code'], $response['message'], $response['errors']);
  //   }

  //   if (isset($response['status'])) {
  //     if ($response['status'] == 1) {
  //       $order->seller_awb_code = $response['payload']['awb_code'];
  //       $order->shiprocket_order_id = $response['payload']['order_id'];
  //       $order->shiprocket_shipment_id = $response['payload']['shipment_id'];
  //       // $order->pickup_id = $response['pickup_token_number'];
  //       $order->save();

  //       $data = [
  //         'label' => $response['payload']['label_url'], // need to send this to the seller by email
  //         'manifest' => $response['payload']['manifest_url'],
  //         'pickup_scheduled_date' => $response['payload']['pickup_scheduled_date'],
  //       ];


  //       return Helper::sendSuccessResponse(201, 'Order created successfully.', $data);
  //     } elseif ($response['status'] == 0) {
  //       return Helper::sendErrorResponse(405, $response['payload']['error_message'], $response->json());
  //     } else {
  //       if ($response['message'] == 'Token has expired' || str_contains($response['message'], 'Could not decode token')) {
  //         return $this->auto_token();
  //       }
  //       return Helper::sendErrorResponse(409, 'Unable to create order.', $response->json());
  //     }
  //   }
  // }
}
