 package Problems;

public class PosNegIntegerCount {

    public static void main(String[] args) {
        
        int[] nums = {-10,-1,0,0,0,0,0,0,9};
        int negCount = negCount(nums);
        int posCount = posCount(nums);
        System.out.println("Negative count: " + negCount + "\n Positive count: " + posCount);
    }

    public static int posCount(int[] nums){
        int low = 0, high = nums.length-1;
        int ans = 0;

        if(nums[0]>0) return nums.length;
        if(nums[nums.length-1]<=0) return 0;

        while(low <= high){
            int m = low + (high-low) / 2;

            if(nums[m] >0 ){
                ans = m;
                high = m-1;
            }else low = m+1;

        }

        return nums.length-ans;
    }

    public static int negCount(int[] nums){
        int low = 0, high = nums.length-1;
        int ans = 0;

        if(nums[nums.length-1]<0) return nums.length;
        if(nums[0]>=0) return 0;

        while(low <= high){
            int m = low + (high-low) / 2;

            if(nums[m] < 0){
                ans = m;
                low = m + 1;
            }else high = m-1;
        }

        return ans+1;
    }
}

/*Given a sorted array count all the positive and negative integer form this array which is greater then and lesser then 0*/
