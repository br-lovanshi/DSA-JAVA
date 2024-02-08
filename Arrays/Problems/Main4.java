package Problems;
// https://oj.masaischool.com/contest/5098/problem/6


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result4 {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
     
     

public static int giveSum(int n){
    int sum = 0;
    while(n > 0 ){
      sum += n % 10;
      n /= 10;
    }
    return sum;
    
}
    public static void subset(int n , List<Integer> list) {
        
        
    // Write your code here
   List<Integer> l1 = new ArrayList<>();
   List<Integer> l2 = new ArrayList<>();
  
           
            for(int i  = 0;i<n;i++){
                
                if( giveSum(list.get(i)) %4 == 0){
                    l1.add(list.get(i));
                } else{
                    l2.add(list.get(i));
                }
            }
      
        Collections.sort(l1);
        Collections.sort(l2);
    //      int[] arr1 = l1.stream().mapToInt(i -> i).toArray();
    //      int[] arr2 = l2.stream().mapToInt(i -> i).toArray();
         
    //   arr1 =  sortArray(arr1);
    //      arr2 = sortArray(arr2);
         
        for(int i: l1){
            System.out.print(i + " " );
        }
        for(int i : l2 ){
            System.out.print(i + " ");
        }
    System.out.println();


    }

}

public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        
        // int str = Integer.parseInt(bufferedReader.readLine().trim().replaceAll("\\s",""));
        List<Integer> str = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.subset(n,str);

        bufferedReader.close();
    }
}
