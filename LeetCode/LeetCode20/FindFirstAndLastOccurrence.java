package LeetCode.LeetCode20;

import java.util.Arrays;

public class FindFirstAndLastOccurrence {

    public static void main(String[] args) {

        int[] arr = {1,3,3,4,5,6,6,6,6,6,6,6,6,6,9,8,8,9,10,11,12};
        int target =3;

        int[] output = new int[2];

        output[0] = binarySearch(arr,target, true);

        output[1] = binarySearchFindRight(arr,target);

        System.out.println(Arrays.toString(output));

        // expected [4,12]
    }

    public  static int binarySearch(int[] nums, int target, boolean findLeftSide){

        int low = 0, high = nums.length-1;
        int ans = -1;
        while(low < high){
            int mid = low + (high-low)/2;


                if (nums[mid] == target) {
                    ans = mid;
                    if(findLeftSide) {
                         high = mid-1;
                    }else{
                         low = mid + 1;
                    }
                }

            if(nums[mid] < target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }

        return ans;
    }

    public  static int binarySearchFindRight(int[] nums, int target){

        int low = 0, high = nums.length-1;
        int ans = -1;
        while(low < high){
            int mid = low + (high-low)/2;

            if (nums[mid] == target) {
                ans = mid;
                low = mid + 1;

            }
            if(nums[mid] < target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }

        return ans;
    }
}

/*Given sorted array find first and last occurrence of target*/