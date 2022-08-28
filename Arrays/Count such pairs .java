public static void countSuchPairs(int n,int k, int[] arr)
{
//     //write your code here
  
  int i = 0;
  int j = 1;
  
  int c = 0;
  
  while(i<n-1 && j<n){
      
      // int sum = arr[i]+arr[j];
      
      if(arr[i]+arr[j] == k && i!= j){
     
          i++;
          j++;
               c++;
          
      }
      else if( arr[i]+arr[j] < k ){
          j++;
      }
      else{
          i++;
      }
      
  }
  System.out.println(c);
  
}
// {
//     int sum = 0;
//     int c =0;
//     for(int i = 0;i<n-1;i++ ){
      
//         for(int j = i+1;j<n;j++ ){
//             if(arr[i] + arr[j] == k ){
//                 c++;
//             }
//         }
      
//     }
//     System.out.println(c);
// }