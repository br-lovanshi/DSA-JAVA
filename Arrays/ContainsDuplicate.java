import java.util.HashSet;

public class ContainsDuplicate{

   public static boolean containsDuplicate(int[] nums) {
         HashSet<Integer> hset = new HashSet<Integer>();
         for(int i : nums){
            hset.add(i);
         }
        return nums.length > hset.size();
    }
    public static void main(String[] args) {
        
        int[] nums = {1,3,5,2,6,2,7};
        System.out.println(containsDuplicate(nums));
    }
}