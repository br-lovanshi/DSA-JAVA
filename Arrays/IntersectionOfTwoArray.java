import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class IntersectionOfTwoArray {

    public static int[] intersection(int[] nums1, int[] nums2){
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resultSet = new HashSet<>();

        // add nums1 elements in the set1
        for(int i : nums1){
            set1.add(i);
        }
        // check intersection elements
        for(int i : nums2){
            if(set1.contains(i)) resultSet.add(i);
        }

        // convert set to the int array
        int[] ansArray = new int[resultSet.size()];
        int ind = 0;
        for(int i : resultSet){
            ansArray[ind] = i;
            ind++;
        }

        return ansArray;
    }
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5,6,3,2};
        int[] nums2 = {3,4,5};

        int[] answer = intersection(nums1,nums2);
        System.out.println(Arrays.toString(answer));
    }
}
