package problems;

import java.util.LinkedHashMap;
import java.util.Map;

public class findFirstNonDuplicate {
    public static void main(String[] args) {
        char firstUniqueChar = findFirstNonDuplicateChar("java");
        System.out.println(firstUniqueChar);
    }

    public static char findFirstNonDuplicateChar(String str) {

        Map<Character, Integer> map = new LinkedHashMap<>();

        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }

        return '_';
    }
}

/*
* 🔹 1️⃣ Find First Non-Repeating Character in a String
📝 Problem: Given a string, find the first character that does not repeat.
🔹 Input: "java"
🔹 Output: 'j'
* */