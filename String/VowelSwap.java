public class VowelSwap {

    public static String swapVowel(String str){
        
        char[] word = str.toCharArray();
        String vowel = "AEIOUaeiou";

       int left = 0, right = word.length-1;

        while(left < right){

            while(left < right && vowel.indexOf(word[left]) == -1){
                left++;
            }
            while(left < right && vowel.indexOf(word[right]) == -1){
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
        
        String str = "holle";

        System.out.println(swapVowel(str));
    }
}