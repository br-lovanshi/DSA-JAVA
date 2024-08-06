package Number;

import java.util.LinkedHashSet;
import java.util.Set;

public class HappyNumber {
    public static void main(String[] args) {
        int n = 11;
        boolean isHappy = isHappy(n);
        System.out.println(isHappy);
    }

    public static int sumOfDigitOfSquare(int num) {
        int sum = 0;

        while (num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }

        return sum;
    }

    public static boolean isHappy(int num) {

        Set<Integer> set = new LinkedHashSet<>();
        while (num != 1 && !set.contains(num)) {
            set.add(num);
            num = sumOfDigitOfSquare(num);
        }
        System.out.println(set);
        return num == 1;
    }

}
