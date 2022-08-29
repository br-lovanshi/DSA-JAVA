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

class Result {

   
    public static void subset(int n , int k ,List<Integer> list) {
    // Write your code here
    Collections.sort(list);
    int c = 0;
    int sum = 0;
    for(int i = 0;i<n;i++){
        
        sum += list.get(i);
     
        if(sum <= k ){
            c++;
        }
    }
       System.out.println(c);

    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // int n = Integer.parseInt(bufferedReader.readLine().trim());
        List<Integer> len = Stream.of(bufferedReader.readLine().replaceAll("\\s+$","")
            .split(" ")).map(Integer::parseInt) .collect(toList());
           
           
        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.subset(len.get(0),len.get(1),arr);

        bufferedReader.close();
    }
}
