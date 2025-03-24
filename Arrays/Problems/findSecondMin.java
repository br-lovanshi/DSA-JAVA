package Problems;

import java.security.PublicKey;

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

	public static int secondMin(int[] nums){
		int firstMin = Integer.MAX_VALUE;
		int secondMin = Integer.MAX_VALUE;

		for(int i = 0; i<nums.length; i++){
			if(nums[i] > firstMin && nums[i] < secondMin){
				secondMin = nums[i];
			}
			if(nums[i] < firstMin){
				firstMin = nums[i];
				secondMin = firstMin;
			}
		}

		return secondMin;
	}
}

//https://www.naukri.com/code360/problems/ninja-and-the-second-order-elements_6581960?utm_source=striver&utm_medium=website&utm_campaign=codestudio_a_zcourse&leftPanelTabValue=SUBMISSION