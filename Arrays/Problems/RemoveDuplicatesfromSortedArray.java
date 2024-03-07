package Problems;

public class RemoveDuplicatesfromSortedArray {
    public static int removeDuplicateInPlace(int[] nums){
        int indx = 1;
        for(int i = 1; i<nums.length; i++){
            if(nums[i-1] != nums[i]){
                nums[indx++] = nums[i];
            }
        }

        return indx;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,3,3,4,5,6,6};
        int index = removeDuplicateInPlace(nums);

        for(int i = 0; i<index; i++){
            System.out.print(nums[i] +  " ");
        }
    }
}

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/