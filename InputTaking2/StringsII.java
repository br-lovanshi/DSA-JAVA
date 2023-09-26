//https://oj.masaischool.com/contest/2569/problem/10

import java.io.*;

public class StringsII {

	public static void subset(int n, String st) {

		System.out.println(st);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bufferedReader.readLine().trim());
		for (int i = 0; i < tc; i++) {
			int n = Integer.parseInt(bufferedReader.readLine().trim());
			try {
				String st = bufferedReader.readLine().trim();
				

				subset(n, st);

			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}

		bufferedReader.close();
	}

}
