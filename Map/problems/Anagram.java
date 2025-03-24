package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("listen", "silent"));
    }
    public static boolean isAnagram(String str1, String str2){
        if(str1.length() != str2.length()) return false;

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<str2.length(); i++){
            map.put(str1.charAt(i), map.getOrDefault(str1.charAt(i),0) + 1);
        }
        for(int i = 0; i<str2.length(); i++){
            if(!map.containsKey(str2.charAt(i))) return false;
        }
        return true;
    }
    // o(n log(n))
    public static boolean isAnagram1(String str1, String str2){
        if(str1.length() != str2.length()) return false;

        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        Arrays.sort(char1);
        Arrays.sort(char2);

        for(int i = 0; i<char1.length; i++){
            if(char1[i] != char2[i]) return false;
        }

        return true;
    }
}
