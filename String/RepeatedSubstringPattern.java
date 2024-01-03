public class RepeatedSubstringPattern{

    public static boolean findSubString(String str){
        String doubled = str + str;
        String sub = doubled.substring(1,doubled.length()-1);

        return sub.contains(str);
    }
    public static void main(String[] args){

        String str = "ab";
        System.out.println(findSubString(str));
    }
}
