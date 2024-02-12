package Problems;

// We will use two pointer approach to reverse the char array

import java.util.Arrays;

public class ReverseCharArray {
    public static void reverse(char[] arr){
        int left = 0;
        int right = arr.length-1;

        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
    public static void main(String[] args) {
        String str = "Brajesh Lovanshi";
        char[] charArray = str.toCharArray();
        System.out.println(Arrays.toString(charArray));
        reverse(charArray);
        System.out.println(Arrays.toString(charArray));
    }
}
