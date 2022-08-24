
import java.io.*;
import java.util.*;
import java.util.stream.*;
// import java.lang.*;
import static java.util.stream.Collectors.toList;


public class Main {
    

    	public static boolean subset(char[] st1,char[] st2){
    	       //str = str.replaceAll("\\s", "");
    	    
    	    int n1 = st1.length;
    	    int n2  = st2.length;
    	    if(n1 != n2){
    	        return false;
    	    }
    	    
    	    Arrays.sort(st1);
    	    Arrays.sort(st2);
    	    
    	    for(int i = 0;i<n1;i++ ){
    	        if(st1[i] != st2[i]){
    	            return false;
    	        }
    	    }
    	    return true;
    	   // System.out.println(n1);
    	    
    	    
    	    
    	}
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bf = new BufferedReader(new   InputStreamReader(System.in));
//   System.out.print(bf.readLine().trim());
   String st1 = bf.readLine().trim();
   String st2 = bf.readLine().trim();
st2 = st2.replaceAll("\\s", "");
st1 = st1.replaceAll("\\s", "");
   char[] arr1 = st1.toCharArray();
   char[] arr2  = st2.toCharArray();
   
 if ( subset(arr1,arr2)){
     System.out.println("True");
 }
   else{
       System.out.println("False");
   }
   	 	
    	bf.close();
	}

}



 