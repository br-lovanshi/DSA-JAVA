public class SingleArray {
    
}
//Enter code here

//https://oj.masaischool.com/contest/3467/problem/03
import java.io.*;
import java.util.*;
import java.util.stream.*;
// import java.lang.*;
import static java.util.stream.Collectors.toList;


public class Main {
    
	public static void subset(int n ,List<Integer> list) {
	    
	 
   	//   System.out.println(n);
   	for(int i = 0;i<n;i++){
   	System.out.print(list.get(i) + 1  + " ");
    	}
	}
    	
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));
   	//  int tc = Integer.parseInt(bufferedReader.readLine().trim());
   	//  for(int i = 0;i<tc;i++){
    	int q = Integer.parseInt(bufferedReader.readLine().trim());
   	    try {
         	List<Integer> A = Stream.of(bufferedReader.readLine().trim().split(" "))
                           	.map(Integer::parseInt)
                           	.collect(Collectors.toList());  
           	 
         	subset(q,A);   
           	 
        	} catch (IOException ex) {
            	throw new RuntimeException(ex);
        	}
   	//  	} 
   	 	
    	bufferedReader.close();
	}

}



