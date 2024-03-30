package Problems;

import java.util.Arrays;

public class RotateKByRight {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int k = 7;
        k = k%arr.length;
        System.out.println(k);
        rotate(arr,0,arr.length-1);
        rotate(arr,0, k-1);
        rotate(arr,k,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void rotate(int[] arr, int start, int end){
        while(start<end){

            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
