package Problems;

import java.util.Map;
import java.util.HashMap;

public class MejorityElement {
// using hasmap we can find the elements and their occurence
    public static void mejorityElementFind(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        // add nums ele in the map
        // for (int i = 0; i < nums.length; i++) {

        //     if (map.containsKey(nums[i])) {
        //         map.put(nums[i], map.get(nums[i] + 1));
        //     } else
        //         map.put(nums[i], 1);
        // }
        for(int i :nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        int maxValue = nums.length/2;
        int maxkey = 0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(maxValue < entry.getValue()){
                maxValue = entry.getValue();
                maxkey = entry.getKey();
            }
        }
        if(maxValue != nums.length/2)
        System.out.println(maxkey);
        else
        System.out.println("No mejority element found");
                    
    }

    public static void main(String[] args) {

        int[] arr = { 1, 2, 5, 2,2,2,2,2, 6, 7 };
        mejorityElementFind(arr);

    }
}