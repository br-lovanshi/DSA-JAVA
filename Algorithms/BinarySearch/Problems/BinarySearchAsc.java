package Problems;
// asce sorted array find the target ele

public class BinarySearchAsc{
	public static void main(String[] args){
		int[] arr = {1,2,3,4,5,6};
		int target  = 1;
		
		int output = binarySearch(arr, target);
		System.out.println(output);
		
	}
	
	public static int binarySearch(int[] nums, int target){
		int low = 0, high = nums.length-1;
		
		while(low <= high){
			// find the middle index
			int m = low + (high - low)/2;
			
			if(target == nums[m]){
				return m;
			}
			
			if(target > nums[m]){
				low = m+1;
			}else{
				high = m-1;
			}
		}
		return -1;
	}
}