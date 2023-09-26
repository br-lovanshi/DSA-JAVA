import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JavaStringBufferedReader {
    
    public static void subset(int n,int arr[]){
        
        int i = 0;
        while(i < n){
            System.out.print(arr[i] + " ");
            i++;
        }
        System.out.println();
        
    }
    
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine().trim());

        int [] arr = Arrays.stream(bf.readLine().replaceAll("\\s+$", "").split(" ")).
            mapToInt(Integer::parseInt).toArray();

        // String str = bf.readLine().trim();

        subset(n,arr);
        bf.close();
        }
}
