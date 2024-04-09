
public class OrderIgnosticBinarySearch{

	public static void main(String[] args){
		
		int[] arr = {1,2,3,4,5,6,7,8,0};
		int[] desArr = {6,5,4,3,2,1,1,10,1,0,-1};
		
		int target = 0;
		
		int output = orderIgnosticSearch(desArr,target);
		System.out.println(output);
	}

	/*Find the target value in the sorted array but this array could be sorted in desc or asc, return index of element else -1*/
	public static int orderIgnosticSearch(int[] nums, int target){
        int low = 0;
        int high = nums.length-1;
    
        // check array is sorted in asc or desc
        boolean isAscSorted = nums[low] < nums[high];
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            if (isAscSorted) {
                if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] > target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        
        return -1;
    }
    

}