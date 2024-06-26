package Algorithms.BinarySearch.Problems;
public class PositiveIntegerCount {
    
    public static void main(String[] args) {
        
        int[] nums = {-10,-4,-1,-1,-1,0,0,4,6,8,9};
        int output = binarySearchCeil(nums,9);
        System.out.println(output);
    }

    public static int binarySearchCeil(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = 0;


        while(low < high){
            int mid = low + (high-low)/2;

            if(nums[mid] > target) high = mid-1;
            else low = mid+1;

        }

        return low;
    }
        public static int binarySearch(int[] nums){
        int low = 0, high = nums.length-1;
        int ans = 0;

        if(nums[0]>0) return nums.length;
        if(nums[nums.length-1]<=0) return 0;

        while(low <= high){
            int m = low + (high-low) / 2;

            if(nums[m] > 0){
                ans = m;
                high = m-1;
            }else low = m+1;
        }

        return ans;
    }
}


/*Given a sorted array count all the positive integer form this array which is greater then 0*/