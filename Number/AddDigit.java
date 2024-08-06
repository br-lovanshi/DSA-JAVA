package Number;

//https://leetcode.com/problems/add-digits/
public class AddDigit {
    public static void main(String[] args) {

        int n = 38;
        while (n > 9) {
           n = digitSum(n);
        }
        System.out.println(n);
    }

    public static int digitSum(int n){
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            n /= 10;
        }
        return sum;
    }
}
