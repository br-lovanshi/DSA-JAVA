public class MatrixIteration {

    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        int num = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = num++;
            }
        }
/**
 1 2 3 4
 5 6 7 8
 9 10 11 12
 */
        System.out.println("Matrix retrieval");
        print(matrix);
        System.out.println("Matrix col vise retrieval");
        printCol(matrix);
        System.out.println("Matrix reversed retrieval");
        reverseRowVised(matrix);
        System.out.println("Print Cross indices");
        printCross(matrix);
        System.out.println("Z pattern");
        zPattern(matrix);
    }

    public static void print(int[][] mat) {
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                System.out.print(mat[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void printCol(int[][] mat) {
        for (int col = 0; col < mat[0].length; col++) {
            for (int row = 0; row < mat.length; row++) {
                System.out.print(mat[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void reverseRowVised(int[][] mat) {
        for (int row = mat.length - 1; row >= 0; row--) {
            for (int col = mat[0].length - 1; col >= 0; col--) {
                System.out.print(mat[row][col] + " ");
            }

            System.out.println();
        }
    }

    public static void printCross(int[][] mat) {
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                if (row == col) {
                    System.out.print(mat[row][col] + " ");
                }
            }
        }
        System.out.println();
    }

    public static void zPattern(int[][] mat) {
        for (int row = 0; row < 1; row++) {
            for (int col = 0; col < mat[0].length; col++)
                System.out.print(mat[row][col] + " ");
        }

        for (int col = mat[0].length - 2, row = 1; col >= 0 && row < mat.length - 1; col--, row++) {
            System.out.print(mat[row][col] + " ");
        }

        for (int row = mat.length - 1; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++)
                System.out.print(mat[row][col] + " ");
        }
    }
}
