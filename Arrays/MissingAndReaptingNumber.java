
// https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
import java.util.Map;

import java.util.HashMap;

public class MissingAndReaptingNumber {

    public static void missingAndReaptingEle(int[] arr, int n) {
        // find the missing number 
        int sumOfN = (n + 1) * (n + 2) / 2; // sum of n+1
        int sumOfArray = 0;
        for (int i = 0; i < n; i++) {
            sumOfArray += arr[i]; // sum of array ele
        }

        int missingNumber = sumOfN - sumOfArray;

        // find the reapting number 
        Map<Integer, Integer> map = new HashMap<>();

        for(int i :arr){
        map.put(i,map.getOrDefault(i,0)+1);
        }

        int maxValue = Integer.MIN_VALUE;
        int maxKey = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (maxValue < entry.getValue()) {
                maxKey = entry.getKey();
            }
        }
        System.out.println("Reapted ele " + maxKey + " Missing ele " +missingNumber );

    }

    public static void main(String[] args) {
        int arr[] = { 3, 1, 3 };
        int n = 3;
        missingAndReaptingEle(arr, n);
    }
}
/*
 * Given an unsorted array of size n. Array elements are in the range of 1 to n.
 * One number from set {1, 2, …n} is missing
 * and one number occurs twice in the array. Find these two numbers.
 * 
 * Examples:
 * 
 * Input: arr[] = {3, 1, 3}
 * Output: Missing = 2, Repeating = 3
 * Explanation: In the array, 2 is missing and 3 occurs twice
 * 
 * Input: arr[] = {4, 3, 6, 2, 1, 1}
 * Output: Missing = 5, Repeating = 1
 */