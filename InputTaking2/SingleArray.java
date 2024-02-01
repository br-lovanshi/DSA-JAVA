
//https://oj.masaischool.com/contest/3467/problem/03
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class SingleArray {
    
	public static void subset(int n ,List<Integer> list) {
	    
	 
   	//   System.out.println(n);
   	for(int i = 0;i<n;i++){
   	System.out.print(list.get(i) + 1  + " ");
    	}
	}
    	
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));
  
    	int size = Integer.parseInt(bufferedReader.readLine().trim());
   	  
		List<Integer> array = Stream.of(bufferedReader.readLine().trim().split(" "))
						.map(Integer::parseInt)
						.collect(Collectors.toList());
			
		subset(size,array);
           	 
    	bufferedReader.close();
	}

}



