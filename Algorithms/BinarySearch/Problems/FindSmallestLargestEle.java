package Algorithms.BinarySearch.Problems;

public class FindSmallestLargestEle {
    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,7,8,9,24,50};
        int target = 2;
        int output = binarySearchCeil(arr,target);
        System.out.println(arr[output]);
    }
    //find the ele in the array which is lesser then the target ele
    public static int binarySearchCeil(int[] nums, int target){
        int low = 0, high = nums.length-1;

        while(low < high){
            int mid = high + low/2;

            if(target > nums[mid]){

                low = mid+1;

            }

            else high = mid-1;
        }

        return low;
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

//find the ele in the array which is lesser then the target ele