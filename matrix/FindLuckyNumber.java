import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/lucky-numbers-in-a-matrix/
public class FindLuckyNumber {
    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        int num = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = num++;
            }
        }

        findLuckyNumber(matrix);
/**
 [[1, 2, 3]
 [4, 5, 6]
 [7, 8, 9]]

 Approach:-
 1. Find min from all the row and store int arr
 2. Find max from all the col and store in arr1
 3. Iterate the matrix and check if ele is exists in arr and arr1 if yes store in list and return list
 */
    }

    public static void findLuckyNumber(int[][] mat) {

        int[] rowMin = new int[mat.length];
        Arrays.fill(rowMin, Integer.MAX_VALUE);
        int[] colMax = new int[mat[0].length];
        Arrays.fill(colMax, Integer.MIN_VALUE);

        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                rowMin[row] = Math.min(mat[row][col], rowMin[row]);
                colMax[col] = Math.max(mat[row][col], colMax[col]);
            }
        }
        System.out.println(Arrays.toString(rowMin));
        System.out.println(Arrays.toString(colMax));
        List<Integer> list = new ArrayList<>();
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                if (mat[row][col] == rowMin[row] && mat[row][col] == colMax[col]) {
                    list.add(mat[row][col]);
                }
            }
        }

        System.out.println(list);
    }
}
