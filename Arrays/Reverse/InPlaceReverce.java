package Reverse;

import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;
import java.util.stream.Collectors;

public class InPlaceReverce {

    public static void inPlaceReerse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        System.out.println("in place reverse : " + Arrays.toString(arr));
    }

    public static void reverseArray(int[] arr) {

        int[] reversedArray = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            reversedArray[i] = arr[arr.length - i - 1];
        }

        System.out.println("On place reverse : " + Arrays.toString(reversedArray));

    }

    public static void stackReverse(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
        }

        int[] reversedArray = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            reversedArray[i] = stack.pop();

        }

        System.out.println("Stack reverse : " + Arrays.toString(reversedArray));

    }

    public static void main(String[] args) {

        int[] arr = {4, 2, 1, 7, 5, 9, 3};
//        reverseArray(arr);
//        reverseArray1(arr);


        int[] arr1 = {1, 2, 3, 4, 5};
//        inPlaceReverse(arr1);

        int[] arr2 = {1, 2, 3, 4, 5};
        stackReverse1(arr2);
    }

    private static void stackReverse1(int[] arr2) {
        //1,2,3,4,5
//        5,4,3,2,1
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i<arr2.length; i++){
            stack.push(arr2[i]);
        }
//        stack.stream().forEach(System.out::print);
        for(int i = 0; i<arr2.length; i++){
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    private static void reverseArray1(int[] arr) {
        int[] reversedArr = new int[arr.length];
        int indx = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
            reversedArr[indx++] = arr[i];
        }
        System.out.println();
        System.out.println(Arrays.toString(reversedArr));
    }

    private static void inPlaceReverse(int[] arr){
        int start = 0;
        int end = arr.length-1;

        while(start < end){

            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }

        Arrays.stream(arr).forEach(System.out::println);
    }
}
