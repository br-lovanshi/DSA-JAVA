import java.util.Scanner;

public class CheckPalindrom{

    public static boolean isPalindrom(int num){
        String numString = Integer.toString(num);
        String reverseString = "";
        for(int i = numString.length() - 1; i >= 0; i--){
            reverseString += numString.charAt(i);
        }
        if(reverseString.equals(numString))return true;
        return false;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number");
        int num = scanner.nextInt();
        boolean palindrom = isPalindrom(num);
        if(palindrom){
            System.out.println(num + " " + "is palindrom number");
        }else{
            System.out.println(num + " is not palindrom number");
        }
    }
}