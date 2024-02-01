
public class Anagram {
    // sort the array 
    public static void bubbleSort(char[] arr){
        int len = arr.length;

        for(int i = 0;i<len-1;i++){
            for(int j = 0;j<len - i-1;j++){
                if(arr[j] > arr[j+1]){
                    char swap = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = swap;
                }
            }
        }


    }
    // checking for anagram
    public static boolean isAnagram(String str1,String str2){
        if(str1.length() != str2.length()) return false;
        
        char[] charArray1 = new char[str1.length()];
        char[] charArray2 = new char[str2.length()];
        
        for(int i = 0;i<str1.length() || i< str2.length();i++){
            charArray1[i] = str1.charAt(i);
            charArray2[i] = str2.charAt(i);
        }

        bubbleSort(charArray1);
        bubbleSort(charArray2);

        String string1 = "";
        String string2 = "";

        for(int i = 0;i<charArray1.length;i++){
            string1 += charArray1[i];
            string2 += charArray2[i];
        }

       return string1.equals(string2);
    }

    public static void main(String[] args) {
        
        String str1 = "anagram";
        String str2 = "nagaram";
        System.out.println(isAnagram(str1,str2));
    }
}
