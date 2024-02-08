package Number;
public class IntegerBreak {
    public static int intBreak(int n){
        if(n <2) return -1;

        if(n %3 == 0){
            int k = n/3;
        }
        return -1;
    }
    public static void main(String[] args) {
        int n = 2;
        int mod = 4% 7;
        int divide = 4/7;
        System.out.println(mod + " " + divide + " " + (int) Math.pow(3,divide)*2);
        // int ans = intBreak(n);
    }
}
