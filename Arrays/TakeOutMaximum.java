import java.io.*;
import java.util.*;

public class TakeOutMaximum {
    
	public static void subset(int n ,int k,int[] arr){
	    
	    int sum = 0;
	    
	    for(int i = 0;i<k;i++){
	        sum += arr[i];
	    }
	    
	    int max = sum;
	    
	    for(int i = k;i<n;i++){
	        
	        sum -= arr[i-k];
	        sum += arr[i];
	        
	        if(max <sum){
	            max = sum;
	        }
	    }
	    System.out.println(max);
	}
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));
 
    	
    	
   	    try {
         	int [] A = Arrays.stream(bufferedReader.readLine().trim().split(" "))
                           	.mapToInt(Integer::parseInt).toArray();
                           	
        
            
            int [] B = Arrays.stream(bufferedReader.readLine().trim().split(" "))
                            .mapToInt(Integer::parseInt).toArray();
                     
         	subset(A[0],A[1],B);   
           	 
        	} catch (IOException ex) {
            	throw new RuntimeException(ex);
        	}
 
   	 
    	bufferedReader.close();
	}
}



