import java.io.*;
import java.util.*;
// https://oj.masaischool.com/contest/5098/problem/10

public class SumOfBoundary {

    public static void subset(int[][] arr) {

        int n = arr.length;

        int left = 0;
        int start = 0;
        int colum = n - 1;
        int right = n - 1;
        int ans = 0;
        int mid = ((n + 1) / 2) - 1;

        for (int i = left; i <= right; i++) {
            ans += arr[start][i];
        }
        start++;
        for (int i = start; i <= colum; i++) {
            ans += arr[i][right];
        }
        right--;
        for (int i = right; i >= left; i--) {
            ans += arr[colum][i];

        }
        colum--;
        for (int i = colum; i >= start; i--) {
            ans += arr[i][left];

        }
        left++;

        for (int i = left, j = left; i <= colum && j <= right; i++, j++) {
            ans += arr[i][j];
        }

        for (int i = colum, j = left; i >= start && j <= right; i--, j++) {
            if (i == mid && j == mid) {
                continue;
            } else {
                ans += arr[i][j];
            }

        }
        //int min =  Math.floor(n/2);
        System.out.println(ans);

    }


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(bufferedReader.readLine().trim());
        int[][] arr = new int[tc][tc];
        for (int i = 0; i < tc; i++) {

            try {


                int[] A = Arrays.stream(bufferedReader.readLine().trim().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                //  	int [] str = bufferedReader.readLine().trim().split(" ");
                arr[i] = A;


            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        subset(arr);
        // 		System.<out.println(Arrays.toString(arr));

        bufferedReader.close();
    }
}