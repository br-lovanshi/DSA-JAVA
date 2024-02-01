package Number;

import java.util.Arrays;

public class CandyDistribution {
        public static int[]  distributCandy(int candies, int n){
            int[] output = new int[n];
            int candyCount = 1;
            int indx = 0;
            while(candies >0){
                output[indx%n] += Math.min(candies, candyCount);
                candies -= candyCount;
                candyCount++;
                indx++;
            }
            return output;
        }
    public static void main(String[] args) {
        int candies = 7;
        int num = 4;
        int output[] = distributCandy(candies, num);
        System.out.println(Arrays.toString(output));
    }
}
// https://leetcode.com/problems/distribute-candies-to-people/submissions/
