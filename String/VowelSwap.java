public class VowelSwap {

    public static String swapVowel(String str){
        
        char[] word = str.toCharArray();
        String vowel = "AEIOUaeiou";

       int left = 0, right = str.length()-1;

        while(left < right){

            while(left < right && vowel.indexOf(left) == -1){
                left++;
            }
            while(left < right && vowel.indexOf(right) == -1){
                right--;
            }

            // swap
            char temp = word[left];
            word[left] = word[right];
            word[right] = temp;

            left++;
            right--;
        }
        String ans = new String(word);
        return ans;

    }
    public static void main(String[] args) {
        
        String str = "hello";

        System.out.println(swapVowel(str));
    }
}