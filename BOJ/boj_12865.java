// boj 12865 평범한 배낭
// Dynamic Programming
// Knapsack Algorithm


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_12865 {

    private static int N;
    private static int K;
    private static int[] W;
    private static int[] V;
    private static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        W = new int [N];
        V = new int [N];
        // dp[n][w] : 0번부터 n번째 물건까지 검사했을 때, w의 무게 이하에서 얻을 수 있는 최대 가치
        dp = new int[N][K + 1];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer((br.readLine()));
            W[i] = stoi(st.nextToken());
            V[i] = stoi(st.nextToken());
        }
        for (int w = 0; w <= K; ++w) if (W[0] <= w) dp[0][w] = V[0];
        for (int n = 1; n < N; ++n) {
            for (int w = 0; w <= K; ++w) {
                // 현재의 상태(dp[n-1][w-W[n]])에 현재의 물건(W[n])을 추가로 넣을 수 있는지
                if (W[n] <= w) dp[n][w] = Math.max(
                        dp[n - 1][w], dp[n - 1][w - W[n]] + V[n]);
                else dp[n][w] = dp[n-1][w];
            }
        }
        System.out.print(dp[N - 1][K]);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

}
