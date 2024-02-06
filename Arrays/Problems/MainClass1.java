package Problems;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;


public class MainClass1 {
    
	public static void subset(int n ,int[] arr) {
   	//   System.out.println(n+" " + list);
   	
   	Map<Integer,Integer> map = new HashMap<>();
   int sum1 = 0,sum2 = 0;
   
   for(int i= 0;i<arr.length;i++){
       if(!map.containsKey(arr[i])){
           sum1 += arr[i];
            map.put(arr[i],1);
       }
       sum2 += arr[i];
      
   }
   int ans = (2*(sum1 ) - ( sum2));
   System.out.println(ans);
   
   
// map.put(n,arr);
// System.out.println(map);
    	}
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));
   	 int tc = Integer.parseInt(bufferedReader.readLine().trim());
   	 
   	 for(int i = 0;i<tc;i++ ){
    	int q = Integer.parseInt(bufferedReader.readLine().trim());
   	    try {
         	int [] A = Arrays.stream(bufferedReader.readLine().trim().split(" "))
                           	.mapToInt(Integer::parseInt).toArray();
                           	// .collect(Collectors.toList());  
        //   	  Arrays.stream(bufferedReader.readLine().trim().split( " ")).
        //   mapToInt(Integer::parseInt).toArray();
         	subset(q,A);   
           	 
        	} catch (IOException ex) {
            	throw new RuntimeException(ex);
        	}
   	 }
   	 
    	bufferedReader.close();
	}
}



