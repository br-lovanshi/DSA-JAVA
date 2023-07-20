<?php
use App\Http\Controllers\ShipRocket\ShiprocketController;
use Fruitcake\Cors\HandleCors;
use Illuminate\Support\Facades\Route;


// shiprocket related api's
        Route::post('shiprocket-token', [ShiprocketController::class, 'token']);
        Route::get('/all-pickup-addresses', [ShiprocketController::class, 'all_pickup_location']);
        Route::post('warehouse-pickup-address/{id}', [ShiprocketController::class, 'warehouse_pickup_address']); // add admin warehouse

        // seller shipment 
        Route::post('/generate-seller-admin-ship', [ShiprocketController::class, 'seller_to_admin_ship']);
        Route::post('/generate-seller-admin-invoice', [ShiprocketController::class, 'seller_invoice']);
        Route::post('/cancel-seller-shipment', [ShiprocketController::class, 'cancel_seller_shipment']);

        // custoemr shipment
        Route::post('/generate-admin-customer-ship', [ShiprocketController::class, 'admin_to_customer_ship']);
        Route::post('/generate-admin-customer-invoice', [ShiprocketController::class, 'customer_invoice']);
        Route::post('/cancel-customer-shipment', [ShiprocketController::class, 'cancel_customer_shipment']);
        Route::get('/manifest-label-invoice-link/{order_id}', [OrderController::class, 'manifest_label_invoice_link']);

        // this is for frontend testing will remove soon
        Route::post('/generate-invoice-test', [DummyController::class, 'dummy_invoice']);
        Route::post('/generate-seller-admin-ship-test', [DummyController::class, 'dummy_seller_to_admin_ship']);

        Route::post('/generate-awb', [ShiprocketController::class, 'awb']);//this is on hold
        Route::post('/generate-invoice', [ShiprocketController::class, 'invoice']);
        Route::post("/seller-shiprocket-order", [ShiprocketController::class, 'seller_order']);
        Route::post('/customer-shiprocket-order', [ShiprocketController::class, 'customer_order']);
        Route::post('/seller-pickup-address', [ShiprocketController::class, 'seller_pickup_address']);
        Route::post('/generate-label', [ShiprocketController::class, 'label']);
        Route::post('/generate-seller-admin-ship', [ShiprocketController::class, 'seller_to_admin_ship']);// this is not included shipment table
        //tracking api
        Route::get('/tracking_by_shipment/{id}', [ShiprocketController::class, 'tracking_shipment']);
        Route::get('/tracking_by_awb/{id}', [ShiprocketController::class, 'tracking_by_awb']);
        Route::post('/shipment-pickup', [ShiprocketController::class, 'schedule_pickup']);
        Route::post('/generate-manifest', [ShiprocketController::class, 'manifest']);
        Route::post('/print-manifest', [ShiprocketController::class, 'print_manifest']);
