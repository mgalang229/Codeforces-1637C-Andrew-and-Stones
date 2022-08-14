import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*

we don't care about the first 
and last elements (a[1] and a[N])

0 4 4 3 3 3 0
1 2 4 4 3 3 1
2 2 2 4 4 3 2
3 0 2 4 4 4 2
4 0 0 4 4 4 3
6 0 0 0 4 4 5
8 0 0 0 0 4 7
10 0 0 0 0 0 9
= 10

0 4 3 3 3 3 0
1 2 4 3 3 3 0
2 0 4 3 3 3 1
3 0 2 4 3 3 1
4 0 0 4 3 3 2
5 0 0 2 4 3 2
6 0 0 0 4 4 2
8 0 0 0 0 4 4
10 0 0 0 0 0 6
= 10

it's possible as long as there is 
one even number from 2 to N-1

0 5 5 4 5 5 0
0 5 6 2 6 5 0
0 6 6 0 6 6 0
3 0 6 0 6 6 3
6 0 0 0 6 6 6
9 0 0 0 0 6 9
12 0 0 0 0 0 12
= 14

it's different if all numbers from 
index 2 to N-1 are all odd:
0 5 5 5 5 5 0
0 5 5 3 6 5 1
0 5 5 4 4 5 2
0 5 6 4 2 6 2
0 6 6 4 0 6 3
3 0 6 4 0 6 6
5 0 6 0 0 6 8
8 0 6 0 0 0 11
11 0 0 0 0 0 14
= 1 + 2 + 3 + 3 + 3 + 3
= 15

it's also possible as long as there more 
than one odd numbers which are > 1

0 5 1 1 1 1 0
1 3 2 1 1 1 0
1 4 0 1 1 1 1
2 0 0 2 1 1 1
3 0 0 0 2 1 1
4 0 0 0 0 2 1
5 0 0 0 0 0 2
= 1 + 1 + 2 + 1 + 1 + 1
= 7

 */

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int n = fs.nextInt();
			int[] a = fs.readArray(n);
			int evens = 0;
			int odds = 0;
			int oddsGreat = 0;
			for (int i = 1; i < n - 1; i++) {
				if (a[i] % 2 == 0) {
					evens++;
				} else if (a[i] % 2 == 1) {
					odds++;
					if (a[i] > 1) {
						oddsGreat++;
					}
				}
			}
			//fix corner cases
			if (evens == 0 && oddsGreat == 0) {
				out.println(-1);
				continue;
			}
			if (evens == 0 && oddsGreat == 1 && odds == 1) {
				out.println(-1);
				continue;
			}
			long ans = 0;
			if (evens > 0) {
				for (int i = 1; i < n - 1; i++) {
					ans += (a[i] + 1) / 2;
				}
			} else {
				ans++;
				ans += (a[1] - 1) / 2;
				for (int i = 2; i < n - 1; i++) {
					ans += (a[i] + 1) / 2;
				}
			}
			out.println(ans);
		}
		out.close();
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
