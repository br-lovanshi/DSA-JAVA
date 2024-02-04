import java.util.Arrays;

public class MaxmimuProductOfPairs {
    public static int maxmumPair(int[] nums){
        Arrays.sort(nums);
        return (nums[nums.length-1]*nums[nums.length-2] )-(nums[0]*nums[1]);
    }
    public static void main(String[] args) {
        int[] array = {2,3,5,7,2,8,1};
        System.out.println(maxmumPair(array));
    }
    
}
// https://leetcode.com/problems/maximum-product-difference-between-two-pairs/description/?envType=daily-question&envId=2023-12-18