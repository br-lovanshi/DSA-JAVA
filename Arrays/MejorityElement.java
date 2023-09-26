
import java.util.Map;
import java.util.HashMap;

public class MejorityElement{

    public static void mejorityElementFind(int[] nums){
        
        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0;i<nums.length;i++){
            
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i]+1));
            }
            else map.put(nums[i],1);
        }

        System.out.print(map.containsKey(1));
    }
    
    public static void main(String[] args) {
        
        int[] arr = {1,2,3,5,2,2,2,6,7};
        mejorityElementFind(arr);

    }
}