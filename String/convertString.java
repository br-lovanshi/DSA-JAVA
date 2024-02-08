package String;

import java.util.Arrays;

public class convertString{

    public static void main(String[] args) {
        String str = "this is brajesh lovanshi";

        String[] arr = str.split(" ");

       StringBuilder sb = new StringBuilder();

        for(int i = 0; i<arr.length; i++) {

            String st = arr[i];

            char ch = Character.toUpperCase(st.charAt(0));
            
            String word = ch + st.substring(1);

            sb.append(word);

            if(i < arr.length - 1) {
                sb.append(" ");
            }
        }
        
        System.out.println(sb.toString());
    }
}