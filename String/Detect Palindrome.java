//Enter code here
// https://oj.masaischool.com/contest/5061/problem/5
import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;


public class Main {
    
	public static void subset(int n ) {
	    
	  String asn = Integer.toString(n);
      String ans = "";
     
	 for(int i = asn.length()-1;i>=0;i--){ 
	     //  System.out.println(asn.charAt(i));
	     ans += asn.charAt(i);
	 }
	 
	 int a = Integer.parseInt(ans);
	 
  	  System.out.println(a==n ? "Yes" : "No" );
   	  
    
    
	}
    	
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));

    	int q = Integer.parseInt(bufferedReader.readLine().trim());
   	   
   	 	subset(q);
    	bufferedReader.close();
	}

}



