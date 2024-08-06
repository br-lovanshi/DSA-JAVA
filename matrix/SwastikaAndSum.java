// https://oj.masaischool.com/contest/5098/problem/2
import java.io.*;
import java.util.*;
import java.lang.*;

public class SwastikaAndSum {

	public static void subset(int n,int m,int[][] arr ){

	   int row = ((n+1)/2)-1;
	   int col = ((m+1)/2)-1;
	   int rowS = 0;
	   int colS = 0;
	   for(int i = 0;i<row;i++){
	       rowS += arr[i][0];

	   }

	   for(int i = 0;i<m;i++ ){
	       rowS += arr[row][i];
	   }

	   for(int i = row +1; i<n;i++ ){
	       rowS += arr[i][m-1];
	   }

	   for(int i = m-1;i>=col;i--){
	       colS += arr[0][i];
	   }
	   for(int i = 1;i<n;i++ ){
	       colS+= arr[i][col];
	   }
	   for(int i  = 0 ;i<col;i++){
	       colS += arr[n-1][i];
	   }
	  int ans   =  Math.abs(rowS-colS);
	   System.out.println(ans );

	}


	public static void main(String[] args) throws IOException {

    	BufferedReader bufferedReader = new BufferedReader(new   InputStreamReader(System.in));

    	int [] tc = Arrays.stream(bufferedReader.readLine().trim().split(" ")).
    	mapToInt(Integer::parseInt).toArray();

    	int [][] arr = new int[tc[0]][tc[1]];
    	for(int i = 0;i<tc[0];i++ ){

   	    try {


         	int [] A = Arrays.stream(bufferedReader.readLine().trim().split(" "))
                           	.mapToInt(Integer::parseInt).toArray();
        //  	int [] str = bufferedReader.readLine().trim().split(" ");
         arr[i] = A;



        	} catch (IOException ex) {
            	throw new RuntimeException(ex);
        	}
    	}
    	subset(tc[0],tc[1],arr);
    // 		System.out.println(Arrays.toString(arr));

    	bufferedReader.close();
	}
}



