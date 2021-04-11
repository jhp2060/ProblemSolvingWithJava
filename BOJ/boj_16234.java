// boj 16234 인구이동
// dfs

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_16234 {
    static int n, l, r, ans = 0;
    static int[][] a;
    static boolean[][] visited;
    static LinkedList<int[]> selected;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};
    static boolean hasMoved = false;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        l = stoi(st.nextToken());
        r = stoi(st.nextToken());
        a = new int [n + 2][n + 2];
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j<= n; ++j) a[i][j] = stoi(st.nextToken());
        }
        for (int i = 0; i <= n + 1; ++i) a[0][i] = a[n + 1][i] = -1;
        for (int i = 0; i <= n + 1; ++i) a[i][0] = a[i][n + 1] = -1;
        visited = new boolean [n + 2][n + 2];
        while (true) {
            for (int i = 1; i <= n; ++ i) {
                for (int j = 1; j <= n; ++j) {
                    if(!visited[i][j]) {
                        selected = new LinkedList<>();
                        selected.add(new int[] {i, j});
                        dfs(i, j, i, j);
                    }
                }
            }
            if (!hasMoved) break;
            ans++;
            hasMoved = false;
            init();
        }
        System.out.print(ans);
    }
    public static int stoi(String s) {return Integer.parseInt(s);}
    public static void init() {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) visited[i][j] = false;
        }
    }
    public static int dfs(int y, int x, int sy, int sx) {
        int ret = a[y][x];
        visited[y][x] = true;
        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (a[ny][nx] == -1 || visited[ny][nx]) continue;
            int delta = Math.abs(a[y][x] - a[ny][nx]);
            if (l <= delta && delta <= r) {
                selected.add(new int[]{ny, nx});
                ret += dfs(ny, nx, sy, sx);
            }
        }
        if (selected.size() > 1 && y == sy && x == sx) move(ret);
        return ret;
    }
    public static void move(int total) {
        int ppl = total / selected.size();
        for (int[] yx : selected) a[yx[0]][yx[1]] = ppl;
        hasMoved = true;
    }
}
