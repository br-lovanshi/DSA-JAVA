import java.util.Arrays;

public class AddToArrayFromInteger {
    
    public static void solution(int[] num,int k){

        // conver to string
        String number = "";
        for(int i : num){
            number += i;
        }

        // convert to int
        int number1 = Integer.parseInt(number);
        number1 = number1 + k;
        String str[] = Integer.toString(number1).split("");
        System.out.println(Arrays.toString(str));

    }
    public static void main(String[] args) {
        int[] array = {1,2,0,0 };
        int k = 34;
        solution(array, k);
        // int num = 1200 + 34;
        // int ans[] = [1,2,3,4];

    }
}
