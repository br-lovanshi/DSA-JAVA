package BasicOperation;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayOp {
    public static void main(String[] args) {

        // input taking for array
        Scanner sc = new Scanner(System.in);
        int []arrIn = new int[4];
//        inputTacking(arrIn, sc);

        Integer[] arr = {1,3,4,5,6};
//        System.out.println(arr[1]);
//        passByValue(arr);
//        System.out.println(arr[1]);

        StringBuilder str = new StringBuilder();
        str.append("Brajesh");
//        System.out.println(str);
//        passByValue(str);
//        System.out.println(str);
        char[] charArr = new char[13];
        charArr[0] = 'b';
        charArr[2] = 'c';
//        System.out.println(Arrays.toString(charArr));

        // 2D array
        int[][] matrix = new int[2][3];
        int number = 1;
        for(int i = 0; i<2; i++){
            for(int j = 0; j<3; j++){
                matrix[i][j] = number++;
            }
        }
        for(int i = 0; i<matrix.length; i++){
//        System.out.println(Arrays.toString(matrix[i]));
        }

        int[][] mat = {
                {1,3,4},
                {2,5,7},
                {6,8,9}
        };

        for(int i = 0; i<mat.length; i++){
            for(int j = 0; j<mat[0].length; j++){
//                System.out.println(mat[j][i]);
            }
        }

        // each array inside 2D array can have diff size
        int[][] mat1 = new int[2][];
        int[][] mat2 = {
                {1,2,3},
                {1,2,3,4},
                {1,5}
        };

        for(int i =0; i<mat2.length; i++){
            for(int j = 0; j<mat2[i].length; j++){
//                System.out.print(mat2[i][j] + " ");
            }
//            System.out.println();
        }

        //Input tacking in 2D array
        int[][] matInput = new int[3][2];
        inputTacking(matInput, sc);

        sc.close();

    }

    public static void passByValue(Integer[] nums){
        nums[1] = 42;
    }

    public static void passByValue(StringBuilder str){
        str.replace(0,7,"Vicky");
        System.out.println(str);
    }

    public static void inputTacking(int[] arrIn, Scanner sc){
        for(int i = 0; i<arrIn.length; i++){
            arrIn[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(arrIn));

    }
    public static void inputTacking(int[][] mat, Scanner sc){
        for(int i = 0; i<mat.length; i++){
            for(int j = 0; j<mat[i].length; j++){
                mat[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i<mat.length; i++){
            System.out.println("[ "+ Arrays.toString(mat[i]) +" ]");
        }
    }
}
