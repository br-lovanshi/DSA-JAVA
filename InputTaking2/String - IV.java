//Enter code here


//https://oj.masaischool.com/contest/2569/problem/12


//Enter code here
import java.io.*;
import java.util.*;
import java.util.stream.*;
// import java.lang.*;
import static java.util.stream.Collectors.toList;


public class Main {
    
	public static void subset(int n ,String st){
	    
	   System.out.println(st);
    	}
    	
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));
   	 int tc = Integer.parseInt(bufferedReader.readLine().trim());
   	 for(int i = 0;i<tc;i++){
    // 	int n = Integer./parseInt(bufferedReader.readLine().trim());
   	    try {
   	        String st =  bufferedReader.readLine().trim();
        //  	List<Integer> A = Stream.of(bufferedReader.readLine().trim().split(" "))
        //                   	.map(Integer::parseInt)
        //                   	.collect(Collectors.toList());  
           	 
         	subset(tc,st);   
           	 
        	} catch (IOException ex) {
            	throw new RuntimeException(ex);
        	}
   	 	} 
   	 	
    	bufferedReader.close();
	}

}



