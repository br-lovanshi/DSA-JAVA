import java.util.HashMap;
import java.util.Map;

public class MoreThentwenteeFIve {
    
    public static int findMostFrequent(int[]nums){
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);

        }

        int devidedValue = nums.length/4;
        int output = 0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){

            if(entry.getValue()>devidedValue){
                output = Math.max(devidedValue, output);
            }
        }
        return output;
    }
    public static void main(String[] args) {
        
        int []arr = {2,3,4,5,2,2,5,3,2,5};
        System.out.println(findMostFrequent(arr));
    }
}
// https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/description/