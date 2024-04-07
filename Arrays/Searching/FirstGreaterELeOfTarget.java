package Searching;

public class FirstGreaterELeOfTarget {
    public static void main(String[] args) {

        int[] arr = {3,4,5,6,7,7,7,7,8,9,24,50};
        int target = 7;
        int output = binarySearch(arr,target);
        System.out.println(output);
    }

    public static int binarySearch(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;

        // Handle empty array or target greater than all elements
        if (high < 0 || arr[high] < target) {
            return -1;
        }
        int output = -1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] > target) {
                output = arr[mid];
                high = mid-1;
            } else {

                low = mid+1;
            }
        }

        // If no element is greater than or equal to target, return the last element
        return output;
    }
}
/*
 *
 * Find first greater ele of target in the array if not exist return -1
 *
 * */