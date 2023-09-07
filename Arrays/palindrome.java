class Palindrome {
    public boolean isPalindrome(int x) {
        
        String str1 = Integer.toString(x);
        String str2 = "";

        for(int i = str1.length()-1;i>=0;i--){
            str2 += str1.charAt(i);
        }

        return str1.equals(str2);
    }
}