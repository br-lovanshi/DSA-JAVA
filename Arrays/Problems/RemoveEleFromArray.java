package Problems;

import java.util.Arrays;

public class RemoveEleFromArray {
    public static int[] removeEle(int[]arr,int ele){
        boolean isPresent = false;
        int presentedValue = 0;
        for(int i : arr){
            if(i == ele) presentedValue++;
        }
        int[] updatedArr = new int[arr.length-presentedValue];

        if(presentedValue >0){
            int indx = 0;
            for(int element : arr){
                if(element != ele){
                    updatedArr[indx++] = element;
                }else continue;
            }
        }
        return updatedArr;
    }
    public static void main(String[] args) {
        int [] arr = {2,3,4,5,6,7,4};
        int[] output = removeEle(arr,4);

        System.out.println(Arrays.toString(output));
    }
}
