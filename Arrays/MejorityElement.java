
import java.util.Map;
import java.util.HashMap;

public class MejorityElement {
// using hasmap we can find the elements and their occurence
    public static void mejorityElementFind(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        // add nums ele in the map
        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i] + 1));
            } else
                map.put(nums[i], 1);
        }
        int max = 0;
        // for(Map.entry(Integer, Integer) entry : map.entrySet()){
            
        }

         
    }

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 5, 2, 2, 22, 6, 7 };
        mejorityElementFind(arr);

    }
}