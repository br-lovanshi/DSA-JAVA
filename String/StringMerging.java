
public class StringMerging {

    public static void mergeString(String str1, String str2){

        // find max len 
        int len = str1.length();

        String mergedString = "";
        for(int i = 0;i<str1.length() || i<str2.length();i++){
            if(i<str1.length()){
                mergedString += str1.charAt(i);
            }
            if(i<str2.length()){
                mergedString += str2.charAt(i);
            }
        }
        System.out.println(mergedString);

    }
    public static void main(String[] args){

        String str1 = "abcd";
        String str2 = "twxyz";
        mergeString(str1,str2);
    }
}
