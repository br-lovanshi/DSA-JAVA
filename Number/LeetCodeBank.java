package Number;

public class LeetCodeBank {
    public static int totalMoney(int n){
        int totalMoney= 0;
        int currentMoney = 1;
        for(int day = 1; day <= n; day++){

            totalMoney += currentMoney;
            currentMoney++;

            if(day %7 == 0){
                currentMoney = day/7+1;
            }
        }
        return totalMoney;
    }
    public static void main(String[] args) {
       
        int days = 20;        
        System.out.println(totalMoney(days));
    }
}


/*
 * https://leetcode.com/problems/calculate-money-in-leetcode-bank/solutions/?envType=daily-question&envId=2023-12-06
 * 
*/