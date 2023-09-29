//Enter code here

//https://oj.masaischool.com/contest/5061/problem/1
import java.io.*;
import java.util.*;
import java.util.stream.*;
// import java.lang.*;
import static java.util.stream.Collectors.toList;


public class MissingInteger {
    
	public static void subset(int arr[] ) {
	    int n = arr.length;
	    
	   // int tsum = n(n +2 )/2;
	   int tsum = ((n+1) *(n+2))/2;
	    int sum = 0;
	 for(int i = 0;i<n;i++ ){
	     tsum -= arr[i];
	 }
	 
  	    System.out.println(tsum);
   	 
   	    
  	 //   System.out.println(sum - tsum);
   
	}
    	
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));
   	//  int tc = Integer.parseInt(bufferedReader.readLine().trim());
   	//  for(int i = 0;i<tc;i++){
    // 	int q = Integer.parseInt(bufferedReader.readLine().trim());
   	    try {
        //  	List<Integer> A = Stream.of(bufferedReader.readLine().trim().split(" "))
        //                   	.map(Integer::parseInt)
        //                   	.collect(Collectors.toList());  
           
           int arr[] = Arrays.stream(bufferedReader.readLine().trim().split( " ")).
           mapToInt(Integer::parseInt).toArray();
        //   System.out.println/(Arrays.toString(arr));
         	subset(arr);   
           	 
        	} catch (IOException ex) {
            	throw new RuntimeException(ex);
        	}
   	//  	} 
   	 	
    	bufferedReader.close();
	}

}



