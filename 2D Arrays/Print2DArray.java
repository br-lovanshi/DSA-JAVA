public class Print2DArray {
    
    public static void main(String[] args) {
        
        int[][] mat = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };

        int row = mat.length;
        int col = mat[0].length;

        for(int i = 0;i<row;i++){
            for(int j = 0;j<col;j++){
                System.out.println(mat[i][j] + " ");
            }
            // System.out.println();
        }
    }
}
