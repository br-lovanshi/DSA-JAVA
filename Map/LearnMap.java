import java.util.HashMap;
import java.util.Map;

public class LearnMap {
    public static void main(String[] args) {
        int[] nums = {1,1,3,2,1,1,1};
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i :nums){
            map.put(i,map.getOrDefault(i, 0)+1);
        }

        System.out.println(map.keySet());

        int max = Integer.MIN_VALUE;
        int majority = 0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()>max){

                max = entry.getValue();
                majority = entry.getKey();
            }
            
        }
        // if(map.get(majority) > nums.length/2)
            System.out.println("Majority element is " + majority);
    }
}
