package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,5,3,7,9,4};
        int target = 9;
        int[] output = findTwoSum(nums, target);
        System.out.println(Arrays.toString(output));
    }

    public static int[] findTwoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
//        int index = 0;
//        for(int num : nums){
//            map.put(index++, num);
//        }
//        int[] output = {-1, -1};
//
//        for(int num : nums){
//            if(map.containsValue(target-num)){
//                output[0] = num;
//                output[1] = target-num;
//                break;
//            }
//        }
//        return output;

        for(int i = 0; i<nums.length; i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{i, map.get(target-nums[i])};
            }

            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
// https://leetcode.com/problems/two-sum/