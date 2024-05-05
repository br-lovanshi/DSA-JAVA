package Problems;

import java.util.Arrays;


public class BinarySearch {

    public static void findKItrative(int[] arr, int n, int k){

        int l = 0;

        int h = n-1;

        while(l<=h){
            // find middle point
            int m = h-l/2;

            if(arr[m]== k) {System.out.println(true); return;}
            if(k > arr[m]){
                l = m+1;
            }

            else{
                h = m-1;
            }
        }

        System.out.println(false);

    }


    public static boolean findKRecursive(int[] arr, int k, int l,int h){

        if(l > h){
            return false;
        }

        // find middle
        int m = h-l/2;

        if(arr[m] == k) {
            return true;
        }

        if(arr[m] < k){
           return findKRecursive(arr, k, m+1, h);
        }
        else{
            return findKRecursive(arr, k, l, m-1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        int n = arr.length;
        int k = 8;

        //binary search
        System.out.println(binarySearch(arr,k));

        // collection function
        int ans = Arrays.binarySearch(arr, k);
//        System.out.println(ans);

        // itrative way
//        findKItrative(arr, n,k);

        // recursive way
        boolean output = findKRecursive(arr, k, 0, n-1);
//        System.out.println(output);
    }

    public static int binarySearch(int nums[], int target){

        int low = 0, high = nums.length-1;

        while(low <= high){

            int mid = low + (high - low)/2;

            if(nums[mid] == target) return mid;
            else if(nums[mid] > target)  high = mid-1;
            else low = mid+1;
        }

        return -1;
    }
    
}


// https://www.geeksforgeeks.org/searching-algorithms/