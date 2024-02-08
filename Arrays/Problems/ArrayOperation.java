package Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
// https://www.geeksforgeeks.org/searching-algorithms// visit for all array questions
public class ArrayOperation {

	 public static void findMin(int[] nums) {
		 
		 int minValue = nums[0];
		 for(int i = 1;i<nums.length;i++) {
			 if(minValue >= nums[i])
				 minValue = nums[i];
			 
		 }
		 
		 System.out.println("Min value :- " +minValue);
	 }
	 
	 public static void maxValue(int[] nums) {
		 
		 int maxValue = nums[0];
		 for(int i = 1;i<nums.length;i++) {
			 if(maxValue <= nums[i])
				 maxValue = nums[i];
		 }
		 System.out.println("Max value :- " + maxValue);
	 }
	 
	 public static void reverse(int[] nums) {
		 
		 for(int i = nums.length-1; i>= 0;i--) {
			 
			 System.out.print(nums[i] + " ");
		 }
		 System.out.println();
	 }
	 
	 public static void bubbleSort(int[]nums) {
		 
		 int len = nums.length;
		 
		 for(int i = 0;i<len-1;i++) {
			 
			 boolean isSorted = false;
			 
			 for(int j = 0;j<len-i-1;j++) {
				 
				 if(nums[j] >= nums[j+1]) {
					 
//					 swap
					 int temp = nums[j];
					 nums[j] = nums[j+1];
					 nums[j+1] = temp;
					 isSorted = true;
				 }
			 }
			 
			 if(!isSorted)
				 break;
		 }
		 
		 System.out.println("Soted array " + Arrays.toString(nums));
	 }
	 
	 public static void findEle(int[] nums, int ele) {
		 
		 boolean flag  = false;
		 int ind = 0;
		 
		 for(int i = 0;i<nums.length;i++) {
			 if(nums[i] == ele) {
				 flag = true;
				 ind = i;
			 }
		 }
		 
		 if(flag)
			 System.out.println("Element present at " + ind + " index");
		 else
			 System.out.println("Element not present");
	 }
	 
	 public static void removeDuplicate(int[] nums) 
	 {
		 Set<Integer> set = new HashSet<>();
		 
		 for(int i : nums) {
			 set.add(i);
		 }
		 System.out.println(set);
		 
	 }
	 
	 public static void removeByEle(int [] nums, int ele) {
		 
		 List<Integer> newList = new ArrayList<>();
		 
		 boolean flag = false;
		 
		 for(int i =0;i<nums.length;i++) {
			 
			 if(nums[i] == ele) {
				 flag = true;
				 continue;
			 }
			 
			 newList.add(nums[i]);
			 
		 }
		 
		 if(!flag) {
			 System.out.println("Element not found " + ele);
		 }
		 else
		 System.out.println(newList);
	 }
	 
	public static void main(String[] args) {
	
		int[] array = {4,6,2,6,8,5};
		
		findMin(array);
		maxValue(array);
		reverse(array);
		bubbleSort(array);
		findEle(array,8);
		removeDuplicate(array);
		removeByEle(array,2);
	}
}
