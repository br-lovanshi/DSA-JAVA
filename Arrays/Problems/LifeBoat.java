package Problems;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result5 {


    public static void subset(int n ,int k ,List<Integer> list) {
     
    int c = 0;
    int i = 0;
    int j = n-1;
    Collections.sort(list);
    while(i<j){
        int sum = list.get(i) + list.get(j);
        if(sum <=k){

            c++;
            i++;
            j--;
        }
        else{
             
            j--;
            
        }
        if(i == j){
            c++;
            i++;
        }
    }
    
    System.out.println(c);
    
    }

}

public class LifeBoat {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(bufferedReader.readLine().trim());
for(int t = 0;t<tc;t++){
    
    List<Integer> len = Stream.of(bufferedReader.readLine().replaceAll("\\s+$","").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result5.subset(len.get(0), 1,arr);
}
        bufferedReader.close();
    }
}
