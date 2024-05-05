package Number;

public class PrimeNumberCheck {

    public static void main(String[] args) {

        int n = 25;

        for(int i = 2; i<=Math.sqrt(n); i++){
            if(n%i == 0){
                System.out.println("Not Prime");
            }else{
                System.out.println("prime");
            }
        }
//        System.out.println(Math.sqrt(n));
    }
}
