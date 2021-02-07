// boj 14889 스타트와 링크
// Backtracking

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14889 {
    static final int INF = 987654321;
    static int n, ans = INF;
    static int[] members;
    static int[][] s;
    static boolean[] selected;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        s = new int[n+1][n+1];
        members = new int[n/2 + 1];
        selected = new boolean [n+1];
        StringTokenizer st;
        for (int i = 1; i <=n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <=n; ++j) s[i][j] = stoi(st.nextToken());
        }
        go(0, 1);
        System.out.print(ans);
    }
    static int stoi(String s) {return Integer.parseInt(s);}
    static void go(int cnt, int idx) {
        if (idx > n) return;
        if (cnt == (n/2)) {
            int selectedVal = 0, unselectedVal = 0;
            for (int i = 1; i <= n; ++i) {
                for (int j = i + 1; j <= n; ++j) {
                    if (selected[i] != selected[j]) continue;
                    if (selected[i]) selectedVal += (s[i][j] + s[j][i]);
                    else unselectedVal += (s[i][j] + s[j][i]);
                }
            }
            int tmp = selectedVal - unselectedVal;
            if (tmp < 0) tmp = -tmp;
            if (tmp < ans) ans = tmp;
            return;
        }
        selected[idx] = true;
        go(cnt + 1, idx + 1);
        selected[idx] = false;
        go(cnt, idx + 1);
    }
}
