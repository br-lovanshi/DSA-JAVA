// https://oj.masaischool.com/contest/3467/problem/11
import java.io.*;
import java.util.*;
import java.util.stream.*;
// import java.lang.*;
import static java.util.stream.Collectors.toList;


public class Main {
    
    public static boolean checkIt(String sb){
        if(sb.charAt(0) == sb.charAt(sb.length()-1)){
            return true;
        }
        return false;
    }
    public static void subset(String str){
        int c = 0;
     int n = str.length();
        for(int i = 0;i<n;i++ ){
            
            for(int j = 1;j<=n-i;j++){
            //  System.out.println(str.charAt(j));
              if  (checkIt(str.substring(i,i+j))){
                  c++;
              }
                
            }
            
            
        }
    System.out.println(c);
}
    	
    
    
	public static void main(String[] args) throws IOException {
   	 
    	BufferedReader bf = new BufferedReader(new   InputStreamReader(System.in));
    	
    	String st = bf.readLine().trim();
  subset(st);
// System.out.println(st);
   	 	
    	bf.close();
	}

}



