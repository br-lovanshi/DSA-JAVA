package Number;

public class PrimeNumber {
    public static boolean isPrime(int num){

        if(num <2) return false;

        for(int i = 2;i<=Math.sqrt(num);i++){
            if(num%i == 0) return false;
        }

        return true;
    }
    public static void main(String[] args) {
        
        int num = 13;
        boolean isPrime = isPrime(num);
        System.out.println(isPrime);
    }
}
