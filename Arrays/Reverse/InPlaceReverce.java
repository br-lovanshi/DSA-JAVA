package Reverse;

import java.util.Arrays;
import java.util.Stack;

public class InPlaceReverce {
    
    public static void inPlaceReerse(int[] arr){
        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        System.out.println("in place reverse : " +Arrays.toString(arr));
    }

    public static void reverseArray(int[] arr){

        int[] reversedArray = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            reversedArray[i] = arr[arr.length - i - 1];
        }

        System.out.println("On place reverse : " +Arrays.toString(reversedArray));

    }

    public static void stackReverse(int[] arr){
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i< arr.length;i++){
            stack.push(arr[i]);
        }

        int[] reversedArray = new int[arr.length];

        for(int i = 0; i<arr.length; i++){

            reversedArray[i] = stack.pop();
            
        }

        System.out.println("Stack reverse : " + Arrays.toString(reversedArray));

    }

    public static void main(String[] args) {
        
        int[] arr  = {4,2,1,7,5,9,3};
        reverseArray(arr);

        int[] arr1 = {1,2,3,4,5};
        inPlaceReerse(arr1);

        int[] arr2 = {1,2,3,4,5};
        stackReverse(arr2);
    }
}
