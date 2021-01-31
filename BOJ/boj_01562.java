// boj 1562: 계단 수
// dp, bitmasking

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_01562 {
    static int n;
    static int[][][] dp;
    static final int MOD = 1000000000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        n = stoi(br.readLine());
        dp = new int [10][n+1][1<<10];
        for (int i = 0; i < 10; ++i)
            for (int j = 0; j <= n; ++j) Arrays.fill(dp[i][j], -1);
        for (int start = 1; start <= 9; ++start){
            ans = (ans + go(start, 1, 1<<start)) % MOD;
        }
        System.out.print(ans);
    }
    static int go(int start, int digit, int bitmask) {
        if (digit == n) {
            if (bitmask == ((1 << 10) -1)) return 1;
            else return 0;
        }
        int ret = dp[start][digit][bitmask];
        if (ret != -1) return ret;
        ret = 0;
        if (start - 1 >= 0)
            ret += go(start - 1, digit + 1, bitmask|(1<<(start - 1)));
        if (start + 1 < 10)
            ret += go(start + 1, digit + 1, bitmask|(1<<(start + 1)));
        ret %= MOD;
        return dp[start][digit][bitmask] = ret;
    }
    static int stoi(String s) {return Integer.parseInt(s);}
}
