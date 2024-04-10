package Algorithms.BinarySearch.Problems;

public class FindSmallestLargestEle {
    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,7,8,9,24,50};
        int target = 100;
        int output = liner(arr,target);
        System.out.println(output);
    }
    public static int liner(int[] nums, int k){
        for(int num : nums){
            if(k <= num){
                return num;
            }

        }
        return -1;
    }
    public static int binarySearch(int[] nums, int target){
        int low = 0;
        int high = nums.length-1;

        while(low <= high){
            int m = (low+high)/2;

            if(nums[m] > target){
                if(nums[m-1] <target) return nums[m];
            }
            if(nums[m] < target){
                low = m+1;
            }
        }
        return -1;
    }
}

