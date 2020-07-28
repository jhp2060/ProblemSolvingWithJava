import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15650 {
    private static int n, m;
    private static boolean[] visited;
    private static int[] ans;

    public static void init() {
        visited = new boolean[n + 1];
        ans = new int[n+1];
        for (int i = 0; i < n + 1; ++i) visited[i] = false;
        for (int i = 0; i < n + 1; ++i) ans[i] = -1 ;
    }

    public static void dfs(int now, int cnt) {
        if (cnt >= m) {
            for (int i = 0; i < m; ++i) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = now + 1; i <= n; ++i) {
            if (visited[now]) return;
            visited[now] = true;
            ans[cnt] = i;
            dfs(i, cnt + 1);
            visited[now] = false;

        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());

        init();

        dfs(0, 0);
    }

    public static int stoi(String s){
        return Integer.parseInt(s);
    }
}
