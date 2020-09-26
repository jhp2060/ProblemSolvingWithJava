// boj 12849 본대산책
// dp

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class boj_12849 {

    private static int d;
    private static final int mod = 1000000007;
    private static Vector<Integer> graph[] = new Vector[8];

    // dp[i][j] : j분에 i에 있는 경우의 수
    private static int dp[][] = new int[8][100001];

    private static void init() {
        for (int i = 0; i < 8; i++) graph[i] = new Vector<Integer>();
        graph[0].add(1); graph[0].add(3);
        graph[1].add(0); graph[1].add(2); graph[1].add(3);
        graph[2].add(1); graph[2].add(3); graph[2].add(4); graph[2].add(5);
        graph[3].add(0); graph[3].add(1); graph[3].add(2); graph[3].add(5);
        graph[4].add(2); graph[4].add(5); graph[4].add(6);
        graph[5].add(2); graph[5].add(3); graph[5].add(4); graph[5].add(7);
        graph[6].add(4); graph[6].add(7);
        graph[7].add(5); graph[7].add(6);

        ++dp[1][1]; ++dp[3][1];
    }

    public static void main(String[] args) throws Exception {
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        d = Integer.parseInt(br.readLine());
        for (int i = 2; i <= d; i++) {
            for (int now = 0; now < 8; now++)
                for (int adj: graph[now]) {
                    dp[now][i] += dp[adj][i-1];
                    dp[now][i] %= mod;
                }
        }
        System.out.println(dp[0][d]);
    }
}
