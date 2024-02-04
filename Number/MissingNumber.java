package Number;
/**
 * MissingNumber
 */
public class MissingNumber {
    public static int findMissingNumber(int[] nums){
        int naturalSum = nums.length*(nums.length+1)/2;
    
        int numsSum = 0;
        for(int i = 0; i<nums.length;i++){
            numsSum += nums[i];
        }
        return Math.abs(naturalSum - numsSum);
    }
    public static void main(String[] args) {
        
        int[] num = {1,0,3};
        int missingNum = findMissingNumber(num);
        System.out.println(missingNum);
    }
}