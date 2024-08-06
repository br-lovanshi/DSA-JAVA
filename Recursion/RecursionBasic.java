package Recursion;

public class RecursionBasic {

    private static void printArray(int[] nums, int i) {

        if (i > nums.length-1) {
            return;
        }

        System.out.print(nums[i] + " ");
        i++;
        printArray(nums, i);
    }

    public static void main(String[] args) {
        int nums[] = {5, 4, 2, 5, 1};
        printArray(nums, 0);
    }
}
