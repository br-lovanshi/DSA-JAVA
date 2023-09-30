//Enter code here

//https://oj.masaischool.com/contest/5061/problem/1
import java.io.*;
import java.util.*;
import java.util.stream.*;
// import java.lang.*;
import static java.util.stream.Collectors.toList;

public class MissingInteger {
	// o(nlogn) time
	public static void missingint(int arr[], int n) {

		Arrays.sort(arr);
		int count = 1;
		for (int i = 0; i <n; i++) {
			if (arr[i] == count) {
				count++;
			} else {
				// count = arr[i];
				break;
			}
		}

		System.out.println(count);
	}
	// o(n)
	public static void missingint1(int arr[], int n){
		n = n+1;
		int sumOfN = n*(n+1)/2;
		int sum = 0;
		for(int i =0;i<n-1;i++){
			sum += arr[i];
		}
		System.out.println(sumOfN - sum);
	}
	public static void main(String[] args) throws IOException {

		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// int[] array = Arrays.stream(bf.readLine().replaceAll(" ", "").split(" ")).
		// mapToInt(Integer::parseInt).toArray();

		int arr[] = { 1, 2, 3, 4,6,7,8};
		int n = 7;
		missingint1(arr, n);

		// bf.close();
	}

}
