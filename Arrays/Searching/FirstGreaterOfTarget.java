/*Given a sorted array find the first greater ele of target if not found return -1
find ceil element of target

note: you need to find wether array is sorted in desc or asc.
*/

public class FirstGreaterOfTarget {

	public static void main(String[] args){
		int[] arr = {9,8,7,4,2,2,2,1,1,-1,-10,-19};
		int target = 1;
		
		boolean isAscSorted = arr[0] < arr[arr.length-1];
		
		int output = binarySearch(arr, target, isAscSorted);
		System.out.println(output);
	}
	
	public static int binarySearch(int[] nums, int target, boolean isAscSorted){
		int low = 0, high = nums.length-1;
		int output = -1;
		
		while(low <= high){
			int m = low + (high-low)/2;
			
			if(isAscSorted){
				if(target < nums[m]){
					output = m;
					high = m-1;
				}else if(target > nums[m]){
					low = m+1;
				}else{
					high = m-1;
				}
			}else {
				if(target < nums[m]){
					output = m;
					low = m+1;
				}
				else if(target > nums[m]){
					high = m-1;
				}else {
					low = m+1;
				}
			}
		}

		return output;
	}
}