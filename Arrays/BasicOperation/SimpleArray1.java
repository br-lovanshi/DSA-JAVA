package BasicOperation;
import java.util.Arrays;

public class SimpleArray1 {
    public static void main(String[] args) {
        
        int[] array = new int[5];
        array[0] = 1;
        array[2] = 4;
        
        boolean[] booleanArray = new boolean[4];
        booleanArray[2] = true;
        for(boolean i : booleanArray){
            i = true;
            if(i){
                System.out.println("Hello Wolrd!");
            }
        }
        
        int num = 10;
        String str = "Hello";
        Character ch = 'c';
        Long l = 39349384938l;
        double d = 4.4;
        System.out.println(l);
        
    }
}
