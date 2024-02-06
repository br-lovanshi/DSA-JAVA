package BasicOperation;

import java.util.Arrays;

public class IterationArray {

    // insert elements in the specific position
    public static void insertEle(int[] arr, int ele, int position){

        int[] newArray = new int[arr.length+1];

        for(int i = 0;i<position;i++){
            newArray[i] = arr[i];
        }

        newArray[position] = ele;

        for(int i = position; i<arr.length;i++){
            newArray[i+1] = arr[i];
        }

        System.out.println("Updated Array " + Arrays.toString(newArray));
    }
 
    public static void main(String[] args) {
        
        int[] array = new int[5];
         
        for(int i = 1;i<5;i++){
            array[i] = i;
        }

        System.out.println("Original Array " + Arrays.toString(array));

        insertEle(array, 512, 2);
    }
}
