//Enter code here



//https://oj.masaischool.com/contest/2569/problem/15


//Enter code here
import java.io.*;
import java.util.*;
import java.util.stream.*;
// import java.lang.*;
import static java.util.stream.Collectors.toList;


public class Main {
    
	public static void subset(int n ,int val,List<Integer> list){
	    
	    for(int  i = 0;i<list.size();i++){
	        System.out.print(list.get(i) + val + " ");
	    }
	   System.out.println();
    	}
    	
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));
   	 int tc = Integer.parseInt(bufferedReader.readLine().trim());
   	 for(int i = 0;i<tc;i++){
    // 	int n = Integer./parseInt(bufferedReader.readLine().trim());
   	    try {
   	         	List<Integer> len = Stream.of(bufferedReader.readLine().trim().split(" "))
                          	.map(Integer::parseInt)
                          	.collect(Collectors.toList());  
         	List<Integer> A = Stream.of(bufferedReader.readLine().trim().split(" "))
                          	.map(Integer::parseInt)
                          	.collect(Collectors.toList());  
           	 int n = len.get(0);
           	 int val = len.get(1);
        //   System.out.println(len.get(0));
         	subset(n,val,A);   
           	 
        	} catch (IOException ex) {
            	throw new RuntimeException(ex);
        	}
   	 	} 
   	 	
    	bufferedReader.close();
	}

}



