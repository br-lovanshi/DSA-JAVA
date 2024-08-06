package Algorithms.BinarySearch.Sorting;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {9,8,7,6,5,3,4,2,1}; //{5,2,1,5,7,0};
        System.out.println(Arrays.toString(nums));

//        2,5,1,5,7,0
//        2,1,5,5,7,0
//        2,1,5,5,0,7
//        1,2,5,0,5,7
//        1,2,0,5,5,7
//        1,0,2,5,5,7
//        0,1,2,5,5,7

        for(int i = 0; i<nums.length; i++){
            boolean swapped = false;
            for(int j = 0; j<nums.length-i-1; j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    swapped = true;
                }
            }
            if(!swapped) break;
        }

        System.out.println(Arrays.toString(nums));
    }
}
