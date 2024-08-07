package Algorithms.BinarySearch.Problems;

public class FindTargetFromInfiniteSortedArr {
    
    public static void main(String[] args) {
        
        int[] arr = {1,4,5,6,9,8,7};
		int target = 6;

        int low = 0;

        int high = 1;

        while(arr[high] < arr[target]){
            low = high;
            high = 2 * high;
        }
        int output = 0;


        System.out.println(output);
    }

    public static int binarySearch(int[] nums, int low, int high, int target){

        while(low <= high){
            int mid = low + (high -low)/2;

            if(nums[mid] == target)return mid;
            else if(nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }
}

/*Given ans infinite asc sorted array find the target k if not exist return -1.*/