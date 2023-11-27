package Number;

import java.util.ArrayList;
import java.util.List;

/**
 * AllPrimeNumber
 */
public class AllPrimeNumber {
    public static boolean isPrime(int num) {
        if (num < 2)
            return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        List<Integer> primeList = new ArrayList<Integer>();
        for(int i = 0; i<=100;i++){           
            if(isPrime(i)){
                primeList.add(i);             
            }
        }
        int prime = primeList.size();
        int nonPrime = Math.abs(100-primeList.size());
        System.out.println(prime + " is prime and " + nonPrime + " is non prime ");
    }
}