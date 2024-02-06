package Problems;
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
		int sum = ((n + 1) * (n + 2)) / 2;
        for (int i = 0; i < n; i++)
            sum -= arr[i];
        
		System.out.println("The missing integer is: " + sum);
	}
	public static void main(String[] args) throws IOException {

		int arr[] = {1,3,1};
		int n = 3;
		missingint1(arr, n);
	}

}
