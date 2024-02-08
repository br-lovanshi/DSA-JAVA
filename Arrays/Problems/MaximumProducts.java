package Problems;
public class MaximumProducts {
    public static int maxProduct(int[] nums, int n) {
        int max1 = Integer.MIN_VALUE;// 4,4, 5
        int max2 = Integer.MIN_VALUE;// 4, 4
        for (int num : nums) {
            if (num >= max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }

        return (max1 - 1) * (max2 - 1);
    }

    public static void main(String[] args) {


        int[] array = { 1, 2, 6, 3, 7, 3 };
 
        System.out.println(maxProduct(array, array.length));
    }
}

// https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/description/?envType=daily-question&envId=2023-12-12