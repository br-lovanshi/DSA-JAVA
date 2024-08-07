import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArrayProblem {
    public static void main(String[] args)
    {

//        int[] arr = {2,3,4,5,1,4,5,6,7};
//
//        int output = findIndex1(arr);
//        System.out.println(output);

        String stirng1 = "abdfgcfghjklonbf";
        String stirng2 = "abcabbded";
        String stirng3 = "cabbbabcabb";


        int output1 = maxLen(stirng3);
        System.out.println(output1);
    }

    public static int maxLen(String str) {

        int maxLenght = 0;
//        String longSubStr = "";
        int start = 0;
        int end = 0;

        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                String subStr = str.substring(i, j);
                if (isUnique(subStr) && maxLenght < subStr.length()) {
//                    longSubStr = subStr;
                    maxLenght =  subStr.length();
                    start = i;
                    end = j;
                }
            }
        }
        System.out.println(str.substring(start,end));
        return maxLenght;
    }

    public static boolean isUnique(String str) {

        Set<Character> set = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }

        return set.size() == str.length();


    }

    public static int findIndex1(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {

            if (map.get(arr[i]) != null) {
                return map.get(arr[i]);
            } else {
                map.put(arr[i], i);
            }
        }
        return -1;
    }

    public static int findIndex(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j && arr[i] == arr[j]) return i;
            }
        }

        return -1;
    }
}
