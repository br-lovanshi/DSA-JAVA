package Algorithms.BinarySearch.Sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] nums = {5, 2, 1, 5, 7, 0};
        /*5,2,1,5,7,0
         * 0,2,1,5,7,5
         * 0,1,2,5,7,5
         * 0,1,2,5,5,7
         * */

        for (int i = 0; i < nums.length; i++) {
            int minInd = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minInd]) {
                    minInd = j;
                }
            }

            int temp = nums[minInd];
            nums[minInd] = nums[i];
            nums[i] = temp;
        }
        System.out.println(Arrays.toString(nums));
    }
}
