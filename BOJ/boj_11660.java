// boj 11660 구간합구하기5
// dp

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11660 {
    static int N, M;
    static int x1,y1,x2,y2;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        dp = new int[N+1][N+1];
        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; ++j)
                dp[i][j] = dp[i-1][j] + dp[i][j-1] + stoi(st.nextToken()) - dp[i-1][j-1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            x1 = stoi(st.nextToken()); y1 = stoi(st.nextToken());
            x2 = stoi(st.nextToken()); y2 = stoi(st.nextToken());
            sb.append(dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1]).append('\n');
        }
        System.out.print(sb);
    }

    static int stoi(String s) {return Integer.parseInt(s);};
}
