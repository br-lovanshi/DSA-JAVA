package Problems;

import java.util.Arrays;

public class FindThreeLargestElement {
    

    // for sorted array or sort array first
    // time o(n long n)
    public static void findThreeLargerEle(int[] arr){
        int one = Integer.MIN_VALUE,
         two = Integer.MIN_VALUE,
         three = Integer.MIN_VALUE;
         
         Arrays.sort(arr);
         
         int n = arr.length;
         one = arr[n-1];
         two = arr[n-2];
         three = arr[n-3];
         System.out.println(one + " " + two + " " + three);
    }

    // Time o(n)
    public static void findThreeLargerEle1(int[] arr){
        int first = Integer.MIN_VALUE,
         second = Integer.MIN_VALUE,
         third = Integer.MIN_VALUE;

         for(int i = 0; i<arr.length; i++){

            if(arr[i] > first){
                third = second;
                second = first;
                first = arr[i];
            }
            else if(arr[i] > second && arr[i] < first){
                third = second;
                second = arr[i];
            }else{
                third = arr[i];
            }
         }

         System.out.println(first +  " " + second + " " + third);
    }
    
    public static void main(String[] args) {
        int[] arr = {13,5,33,6,2,6};

        // findThreeLargerEle(arr);
        findThreeLargerEle1(arr);
    }
}
