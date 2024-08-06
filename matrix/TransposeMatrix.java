import java.util.Arrays;

public class TransposeMatrix {

public static int[][] transpose(int[][]mat){
    int[][]arr = new int[mat[0].length][mat.length];

    for(int i = 0;i<mat.length;i++){
        for(int j = mat[0].length-1; j>=0;j--){
            arr[j][i] = mat[i][j];
        }
    }

    return arr;
}
    public static void main(String[] args){
        int[][] matrix = new int[3][3];

        for(int i = 1;i<=3;i++){
            for(int j = 1;j<=3;j++){
                matrix[i][j] = j;
            }
        }

        System.out.println(Arrays.toString(transpose(matrix)));
    }
}