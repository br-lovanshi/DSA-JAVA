package Algorithms.BinarySearch.Problems;
/*Find the target ele in desc sorted array return index else -1*/

public class BinarySearchDesc{

	public static void main(String[] args){
		int[] arr = {9,8,7,4,2,2,2,1,1,-1,-10,-19};
		int target = -19;
		
		int output = binarySearch(arr, target);
		System.out.println(output);
	}
	
	public static int binarySearch(int[] nums, int target){
		int low = 0, high = nums.length-1;
		
		while(low <= high){
			int m = low + (high-low)/2;
			
			if(target == nums[m]) return m;
			else if(target > nums[m]) high = m-1;
			else low = m+1;
		}
		
		return -1;
	}
}