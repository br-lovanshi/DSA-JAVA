import java.util.Arrays;

public class WordCanWeFoundString {
    public static void countCharacter(String chars, String[] words){
        int output = 0;
        int[] count = new int[26];
        for(int i = 0;i <chars.length();i++){
            int index = chars.charAt(i)-'a';
            count[index]++;
        }

        for(String word : words){
            if(canForm(word, count))output += word.length();
        }
        System.out.println(output);
    }
    public static boolean canForm(String word, int[] count){
        
        int[] index = new int[26];
        for(int i = 0; i < word.length();i++){
            int indx = word.charAt(i)-'a';
            index[indx]++;
            if(index[indx] > count[indx]) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        
        String str = "cat";
        String[] words = {"cat","bat","ct","hello"};
        countCharacter(str,words);
    }
}

/*
https://leetcode.com/submissions/detail/1114957709/
 * 
*/