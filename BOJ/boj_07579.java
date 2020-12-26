// boj 7579 앱
// knapsack
// dp

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_07579 {
    // 최대로 발생할 수 있는 총 비용 (n <= 100, c[i] <= 100)
    static final int MAX = 100 * 100 + 1;

    static int n, m;

    // b(yte)[i] : i번째 앱을 껐을 때 얻는 메모리 바이트 수
    // c(ost)[i] : i번째 앱을 껐을 때 발생하는 비용
    static int[] b, c;

    // dp[i][j] : i번째 앱까지 종료하거나 켰을 때, 비용이 j 이하일 경우에 얻을 수 있는 최대 메모리
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        b = new int[n];
        c = new int[n];
        dp = new int[n][MAX + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) b[i] = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) c[i] = stoi(st.nextToken());
        for (int cost = c[0]; cost <= MAX; ++cost) dp[0][cost] = b[0];
        for (int app = 1; app < n; ++app) {
            for (int cost = 0; cost <= MAX; ++cost) {
                if (cost >= c[app]) dp[app][cost] = Math.max(
                     dp[app-1][cost], dp[app-1][cost - c[app]] + b[app]);
                else dp[app][cost] = dp[app-1][cost];
            }
        }
        for (int cost = 0; cost <= MAX; ++cost)
            if (dp[n -1][cost] >= m) {
                System.out.print(cost);
                break;
            }
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
