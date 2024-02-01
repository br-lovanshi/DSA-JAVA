/**
 * LearnString
 */
public class LearnString {

    public static void main(String[] args) {

        String str = "35427";

        String resultString = "";

        for(int i = 0;i<str.length();i++){

            for(int j = i+1; j<= str.length();j++){

               String subString = str.substring(i,j);
                
                if (Integer.parseInt(subString) % 2 == 1) {
                    if (resultString.isEmpty() || subString.length() > resultString.length()) {
                        resultString = subString;
                    }
                }
               
            }
        }

        System.out.println(resultString);
    }
}