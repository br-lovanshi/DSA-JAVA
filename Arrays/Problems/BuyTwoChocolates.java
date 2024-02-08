package Problems;
import java.util.Arrays;

public class BuyTwoChocolates {
    public static int buyCholocate(int[] prices, int money){
        // sort the array 
        Arrays.sort(prices);

        // sum of two min price 
        int sum = prices[0] + prices[1];

        if(sum <= money)
        return money - sum;

        return money;
        
    }
    public static void main(String[] args) {
        
        int[] cholocatePrice = {2,4,5,2,6,2};
        int myMoney = 4;
        System.out.println(buyCholocate(cholocatePrice,myMoney));
    }
}
// https://leetcode.com/problems/buy-two-chocolates/description/?envType=daily-question&envId=2023-12-20
