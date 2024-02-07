package Searching;

import java.util.Arrays;

public class FindMin {
    public static void findMaxEle(int [] arr){
        
        int max = arr[0];
        for(int i = 1; i<arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        
        System.out.println("Max: " + max);
     }

     public static void preDefindedMethod(int[] arr){
        
        int max = Integer.MIN_VALUE;
        for(int i = 0; i<arr.length; i++){
        max = Math.max(max,arr[i]);
        }

        System.out.println("Max: " + max);

     }

     public static void sortedArray(int[] arr){

        Arrays.sort(arr);

        System.out.println("Max: " + arr[arr.length-1]);
        
     }

    public static void main(String[] args) {
        int[] arr = {2,1,4,5,6,77,5,4};
        findMaxEle(arr);
        sortedArray(arr);
        preDefindedMethod(arr);
    }
}
