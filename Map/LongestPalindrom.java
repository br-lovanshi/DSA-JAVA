import java.util.Map;
import java.util.HashMap;

public class LongestPalindrom {
    
    public static void main(String[] args) {
        String str = "acccdb";
        int oddCount  = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(Character c :str.toCharArray()){

            map.put(c, map.getOrDefault(c,0)+1);
            if(map.get(c) %2 == 1) oddCount++;
            if(map.get(c) % 2 == 0) oddCount--;
        }
        if(oddCount >1) System.out.println(str.length() - oddCount+1);
        else
        System.out.println(str.length());
    }
}
