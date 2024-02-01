import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateCharacter {
    public static List<Character> findDuplicateCharacter(String str){
        Map<Character, Integer> map = new HashMap<>();
List<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue() >1){
                list.add(entry.getKey());
            }
        }

        return list;
    }
    public static void main(String[] args) {
        String str = "Strings" ;
        List<Character> output = findDuplicateCharacter(str);
        System.out.println(output);

    }

}

// Write a program in java to find a duplicate character
// https://onurdesk.com/top-11-program-in-java-to-find/
