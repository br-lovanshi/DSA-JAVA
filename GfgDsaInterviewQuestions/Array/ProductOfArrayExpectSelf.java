package GfgDsaInterviewQuestions.Array;

import java.util.Arrays;

public class ProductOfArrayExpectSelf {
    

    public static void productExpectSelf(int[] nums){

        int[] productArray = new int[nums.length];
        int product = 1;
        for(int i = 0; i<nums.length; i++){
            product = product * nums[i];
        }

        for(int i = 0; i<productArray.length; i++){
            productArray[i] = product/nums[i];
        }

        System.out.println(Arrays.toString(productArray));
    }

    // o(n) without division
    public static void productExpectSelfWithoutDivision(int[] nums){

        int[] productBefore = new int[nums.length];
        int[] productAfter = new int[nums.length];

        productBefore[0] = 1;
        for(int i = 1; i< nums.length; i++){
            productBefore[i] = productBefore[i-1] * nums[i-1];
        }

        productAfter[nums.length-1] = 1;
        for(int i = nums.length-2; i>=0; i--){
            productAfter[i] = productAfter[i+1] * nums[i+1];
        }

        int[] products = new int[nums.length];
        for(int i = 0; i<nums.length; i++){
            products[i] = productBefore[i] * productAfter[i];
        }

        System.out.println(Arrays.toString(products));

    }

    public static void main(String[] args) {
        
        int[] array = {1,2,3,4,5};

        // productExpectSelf(array);
        productExpectSelfWithoutDivision(array);
    }
}


/*
 * https://www.geeksforgeeks.org/a-product-array-puzzle/
 * https://www.geeksforgeeks.org/top-100-data-structure-and-algorithms-dsa-interview-questions-topic-wise/
 */