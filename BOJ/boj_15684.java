// boj 15684 사다리조작
// backtracking

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15684 {
    static int n, m, h;
    static int ans = 4;
    static boolean found = false;
    static boolean[][] isConnected;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        h = stoi(st.nextToken());
        isConnected = new boolean[n+1][h+1];
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            isConnected[b][a] = true;
        }
        for (int i = 0; i <= 3; ++i) {
            go(0, 1, 1, i);
            if (found) break;
        }
        if (ans > 3) System.out.print(-1);
        else System.out.print(ans);
    }
    public static int stoi(String s) {return Integer.parseInt(s);}
    public static void go (int cnt, int si, int sj, int size){
        if (cnt > 3) return;
        if (cnt == size) {
            if (check() && cnt < ans) {
                found = true;
                ans = cnt;
            }
            return;
        }
        for (int i = si; i < n; ++i) {
            if (i != si) sj = 1;
            for (int j = sj; j <= h; ++j) {
                if (isConnected[i][j] || isConnected[i - 1][j] || isConnected[i + 1][j])
                    continue;
                isConnected[i][j] = true;
                go(cnt + 1, i, j, size);
                isConnected[i][j] = false;
            }
        }
    }
    public static boolean check() {
        for (int start = 1; start <= n; ++start) {
            int now = start;
            for (int i = 1; i <= h; ++i) {
                if (isConnected[now][i]) now = now + 1;
                else if (isConnected[now - 1][i]) now = now - 1;
            }
            if (now != start) return false;
        }
        return true;
    }
}

