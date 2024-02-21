package Problems;

public class FindNonDuplicateNumber {
    public static int findNonDplicate(int[]arr){
        int result = 0;
        for(int index = 0; index< arr.length; index++){
            result = result ^ arr[index];
        }
        return result;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,1,4,2,4};
        System.out.println(findNonDplicate(arr));
    }
}

/**
 * Find the non duplicate number inside array without using hasmap.
 * We will use xor to find unique number
 */