package Problems;

public class FindMaxSumSubArray {
// print all the subarray
    public static void print(int[] arr){

        for(int i = 1;i <arr.length; i++){

            for(int j = i; j<arr.length; j++){
                for(int indx = i; indx<=j; indx++){

                    System.out.print(arr[indx] + " ");
                }
                System.out.println();
            }
        }
    }
    public static int maxSum(int[]arr){
        int maxSum = arr[0];
        int currSum = arr[0];
        for(int index = 1; index<arr.length; index++){
            if(arr[index]>currSum + arr[index]){
                currSum = arr[index];
            }else{
                currSum += arr[index];
            }

            if(currSum > maxSum){
                maxSum = currSum;
            }
        }
        return maxSum;
    }
    public static void main(String[] args) {
        int[] arr = {-1,2,4,-6,8};
        print(arr);
        System.out.println(maxSum(arr));
    }
}
/**
 * We are given an array and need to find the subarray of max sum
 * We will use kadane's algorithms to find max sum of subarray
 */