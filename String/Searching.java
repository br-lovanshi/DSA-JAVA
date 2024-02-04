public class Searching {
    public static void main(String[] args) {
        
        String slogen = "This is good";
        String word = "Good";
        int slogenLen = slogen.length();
        int wordLen = word.length();
        
        for(int i = 0;i<=slogenLen-wordLen;i++){
            boolean flag = true;
            for(int j = 0;j<wordLen;j++){
             if(slogen.charAt(i+j)!= word.charAt(j)){

                 flag = false;
                 break;
             }
            }
        }
        System.out.println(slogenLen + " " + wordLen);
     


    }
}