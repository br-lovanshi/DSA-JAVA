package Problems;

import java.util.ArrayList;
import java.util.List;

public class RotateKtime {
 
    // rotate k time o(n);
    public static void rotateArrayKtime(int nums[], int k){

        List<Integer> list = new ArrayList<Integer>();

        for(int indx = nums.length -k; indx < nums.length; indx++){
            list.add(nums[indx]);
        }
        
        for(int indx = 0; indx < nums.length - k; indx++){
            list.add(nums[indx]);
        }

        System.out.println(list);

    }

    public static void main(String[] args) {
        
        int[] arr = {3,1,3,6,36,7,4,2};
        int k = 2;

        rotateArrayKtime(arr, k);
    }
}
