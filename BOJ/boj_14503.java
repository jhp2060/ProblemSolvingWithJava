// boj 14503 로봇청소기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14503 {
    static int n, m, r, c, d, ans = 0;
    static int[][] grid;
    static boolean[][] visited;
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        grid = new int[n][m];
        visited = new boolean[n][m];
        st = new StringTokenizer(br.readLine());
        r = stoi(st.nextToken());
        c = stoi(st.nextToken());
        d = stoi(st.nextToken());
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) grid[i][j] = stoi(st.nextToken());
        }
        go(r, c, d);
        System.out.print(ans);
    }
    static int stoi(String s) {return Integer.parseInt(s);}
    static boolean ableToMove(int y, int x) {
        if (y < 0 || y >= n || x < 0 || x >= m) return false;
        return grid[y][x] == 0;
    }
    static void go(int y, int x, int dir) {
        if(!visited[y][x])
            ans++;
        visited[y][x] = true;
        int ny, nx, cnt = 0;
        int nDir = (dir + 4 - 1) % 4;
        while (cnt++ <= 4) {
            ny = y + dy[nDir];
            nx = x + dx[nDir];
            if (ableToMove(ny, nx) && !visited[ny][nx]) { // a
                go(ny, nx, nDir);
                return;
            }
            nDir = (nDir + 4 - 1) % 4;  // b
        }
        nDir = (dir + 2) % 4;
        ny = y + dy[nDir];
        nx = x + dx[nDir];
        if (ableToMove(ny, nx)) go(ny, nx, dir); // c
        // d
    }
}
