import java.util.Set;
import java.util.HashSet;

class firstUniqChar {
    public static int solution(String s) {
       Set<Character> letters = new HashSet<>();
       Set<Character> hasSet = new HashSet<>();

       for(char i : s.toCharArray()){
           letters.add(i);

           if(hasSet.contains(i)) letters.remove(i);
           hasSet.add(i);

       }
           if(hasSet.isEmpty()) return -1;
           int i = 0;

           for(char c: s.toCharArray()){
               if(letters.contains(c)) return i;
               i++;
           }

           return -1;
    }

    public static void main(String[] args) {
        String str = "Hello World";
        int ans = solution(str);
        System.out.println(ans);
        System.out.println();
    }
}