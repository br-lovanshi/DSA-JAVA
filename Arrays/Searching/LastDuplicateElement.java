package Searching;

public class LastDuplicateElement {
    public static void main(String[] args) {
        int[] arr = {3,4,5,6,6,7,8};

        boolean flag = false;

        for(int i = arr.length-1; i>0; i--){
            if(arr[i] == arr[i-1]){
                System.out.println("Index " + i + " Element " + arr[i]);
                flag = true;
                break;

            }
        }

        if(!flag)
            System.out.println("No duplicate ele found.");
        
    }
}

// https://www.geeksforgeeks.org/last-duplicate-element-sorted-array/