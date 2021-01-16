// boj 15993 1, 2, 3 더하기 8
// dynamic programming

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_15993 {
    static int t, n;
    static int[] even, odd;
    static StringBuilder sb = new StringBuilder();
    static final int MOD = 1000000009;
    static final int MAX = 100000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = stoi(br.readLine());
        even = new int[MAX + 1];
        odd = new int[MAX + 1];
        even[1] = 0; odd[1] = 1;  // . || 1
        even[2] = 1; odd[2] = 1;  // 2 || 1+1
        even[3] = 2; odd[3] = 2;  // 1+2 2+1 || 1+1+1 3
        while (t-- > 0) {
            n = stoi(br.readLine());
            sb.append(getOdd(n)).append(' ').append(getEven(n)).append('\n');
        }
        System.out.print(sb);
    }
    static int stoi(String s) {return Integer.parseInt(s);}
    static int getEven(int x) {
        if (x < 0) return 0;
        if (even[x] != 0) return even[x];
        return even[x] = ((getOdd(x - 1) + getOdd(x - 2)) % MOD + getOdd(x - 3)) % MOD;
    }
    static int getOdd(int x) {
        if (x < 0) return 0;
        if (odd[x] != 0) return odd[x];
        return odd[x] = ((getEven(x - 1) + getEven(x - 2)) % MOD + getEven(x - 3)) % MOD;
    }
}
