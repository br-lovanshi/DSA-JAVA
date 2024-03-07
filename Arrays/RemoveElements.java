public class RemoveElements {
    
    // inplace remove duplicate elements
    public static int removeEle(int nums[], int val){

        int k = 0;

        for(int num : nums){
            if(num != val) nums[k++] = num;
        }
        return k;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,3,4,5,3,4,2,3};
        int ele = 3;

        System.out.println(removeEle(nums, ele));
    }
}

//https://leetcode.com/problems/remove-element/description/
