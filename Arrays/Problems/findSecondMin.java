package Problems;

public class findSecondMin{

	public static int findSecondMinNumber(int[] nums){
		int firstMin = Integer.MAX_VALUE;
		int secondMin = Integer.MAX_VALUE;
		
		for( int i = 0; i<nums.length; i++){
			if(firstMin > nums[i]){
				secondMin = firstMin;
				firstMin = nums[i];
			}
			else if(secondMin > nums[i]){
				secondMin = nums[i];
			}
		}
		return secondMin;
	}
	public static void main(String args[]){
		int[] nums = {2,5,1,2,6,7};
		int output = findSecondMinNumber(nums);
		System.out.println(output);
	}
}