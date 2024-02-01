
import java.io.*;
import java.util.*;

public class NoddyExamination {
    
	public static void subset(int n ,int k,int[] arr) {
	    
	   //System.out.println(n + " " + k + " " + arr);
	   
	   int c = 0;
	   int c1 = 0;
	   
	   for(int i = 0;i<n;i++ ){
	       
	       //c++;
	       if(arr[i] > k ){
	           c1++;
	           
	       }
	       else {
	       c++;
	       }
	       
	       if(c1 > 1){
	          
	    
	            break;
	           
	       }
	   }
	     System.out.println(c); 
   	
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



