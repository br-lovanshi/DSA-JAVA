
//https://oj.masaischool.com/contest/2569/problem/12

import java.io.*;
// import java.lang.*;

public class StringIV {

	public static void subset(int n, String st) {

		System.out.println(st);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bufferedReader.readLine().trim());
		for (int i = 0; i < tc; i++) {

			String st = bufferedReader.readLine().trim();

			subset(tc, st);

		}

		bufferedReader.close();
	}

}
