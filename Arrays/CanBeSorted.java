
import java.io.*;
import java.util.*;


public class CanBeSorted {
    
	public static void subset(int n ,int[] a){
	    int front = -1, back = -1;
  
    // find the starting index of the
    // reversed subarray
    for (int i = 1; i < n; i++)
    {
        if (a[i] < a[i - 1]) 
        {
            front = i - 1;
            break;
        }
    }
  
    // find the ending index of the
    // reversed subarray
    for (int i = n - 2; i >= 0; i--) 
    {
        if (a[i] > a[i + 1]) 
        {
            back = i + 1;
            break;
        }
    }
  
    // if no reversed subarray is present
    if (front == -1 && back == -1) 
    {
        for (int i = 0; i < n; i++)
            System.out.println(a[i] + " ");
        return;
    }
  
    // swap the reversed subarray
    while (front <= back)
    {
  
        // swaps the front and back element
        // using c++ STL
        int temp = a[front];
        a[front] = a[back];
        a[back] = temp;
  
        // move the pointers one step
        // ahead and one step back
        front++;
        back--;
    }
     boolean isSort = false;
	    for(int i = 0;i<n-1;i++){
	        if(a[i]>a[i+1]){
	            isSort = true;
	        }
	    }
	    if(isSort)
	    System.out.println("NO");
	    else
	    System.out.println("YES");
    // for (int i = 0; i < n; i++)
    //     System.out.print(a[i] + " ");
	   // System.out.println(n+ " "+ arr);
	  
	        
	}
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));
 
    	
    	
   	    try {
         
        int n = Integer.parseInt(bufferedReader.readLine().trim());
            
            int [] B = Arrays.stream(bufferedReader.readLine().trim().split(" "))
                            .mapToInt(Integer::parseInt).toArray();
                     
         	subset(n,B);   
           	 
        	} catch (IOException ex) {
            	throw new RuntimeException(ex);
        	}
 
   	 
    	bufferedReader.close();
	}
}



