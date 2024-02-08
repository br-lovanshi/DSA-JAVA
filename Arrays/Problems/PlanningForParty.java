package Problems;

import java.io.*;
import java.util.*;

 class MinimumExpense1 {
    
	public static void subset(int n1 ,int[] arr1, int n2 ,int[] arr2) {
   	//   System.out.println(n1+" " + arr1);
   	//   System.out.println(n2+" " + arr2);
   	  
   	LinkedHashSet<Integer> set = new LinkedHashSet<>();
   	
   	for(int i = 0;i<n1;i++ ){
   	    set.add(arr1[i]);
   	}
   	
   	LinkedHashSet <Integer> set2 = new LinkedHashSet<>();
   	
   	 for(int i = 0;i<n2;i++){
   	     set2.add(arr2[i]);
   	 }   
    System.out.println(set.equals(set2) ? "Yes" : "No");
    	}
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));
   	//  int tc = Integer.parseInt(bufferedReader.readLine().trim());
   	 
   	//  for(int i = 0;i<tc;i++ ){
    	int n1 = Integer.parseInt(bufferedReader.readLine().trim());
   	    try {
         	int [] A = Arrays.stream(bufferedReader.readLine().trim().split(" "))
                           	.mapToInt(Integer::parseInt).toArray();
                           	
            int n2 = Integer.parseInt(bufferedReader.readLine().trim());
            
            int [] B = Arrays.stream(bufferedReader.readLine().trim().split(" "))
                            .mapToInt(Integer::parseInt).toArray();
                           	// .collect(Collectors.toList());  
        //   	  Arrays.stream(bufferedReader.readLine().trim().split( " ")).
        //   mapToInt(Integer::parseInt).toArray();
         	subset(n1,A,n2,B);   
           	 
        	} catch (IOException ex) {
            	throw new RuntimeException(ex);
        	}
   	//  }
   	 
    	bufferedReader.close();
	}
}



