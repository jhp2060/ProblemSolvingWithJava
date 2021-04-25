// boj 17142: 연구소3
// backtracking, bfs

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_17142 {
    static int n, m;
    static int[][] grid;
    static int[][] viruses;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static final int SPACE = 0, WALL = 1, VIRUS = 2, ACTIVE = 3;
    static final int INF = 987654321;
    static int ans = INF, spaces = 0;
    /*
    requirements
    1. pick 'm' spaces to set virus on
    2. spread virus and get the time to spread it to every spaces -> bfs
    3. get the minimum time
     */
    /*
    hidden cases?
    1. able to pass through de-activated viruses -> chk
    2. only a virus exists -> chk
    3. the grid filled with only viruses -> chk
    4.
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        LinkedList<int[]> tmpViruses = new LinkedList<>();
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        grid = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                grid[i][j] = stoi(st.nextToken());
                if (grid[i][j] == VIRUS) tmpViruses.add(new int[] {i,j});
                else if (grid[i][j] == SPACE) spaces++;
            }
        }
        if (spaces == 0) {
            System.out.print(0);
            return;
        }
        viruses = tmpViruses.toArray(new int[tmpViruses.size()][2]);
        go(0,0);
        if (ans == INF) ans = -1;
        System.out.print(ans);
    }
    public static int stoi(String s) {return Integer.parseInt(s.trim());}
    public static void go (int start, int cnt) {
        if (cnt >= m) {
            int tmp = spreadVirus();
            if (tmp != -1 && ans > tmp) ans = tmp;
            return;
        }
        if (start >= viruses.length) return;
        int[] v = viruses[start];

        // don't pick
        go(start + 1, cnt);

        // pick
        grid[v[0]][v[1]] = ACTIVE;
        go(start + 1, cnt + 1);
        grid[v[0]][v[1]] = VIRUS;
    }
    public static int spreadVirus() {
        LinkedList<int[]> q = new LinkedList<int[]> ();
        int cnt = 0;
        int spacesCnt = spaces;
        int[] now = {0, 0, -1};
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == ACTIVE) q.add(new int[]{i, j, 0});
                if (cnt == m) break;
            }
        }
        int[][] tmpGrid = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; ++i)
            tmpGrid[i] = Arrays.copyOf(grid[i], n);
        while (!q.isEmpty()) {
            now = q.pop();
            for (int i = 0; i < 4; ++i) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                if (tmpGrid[ny][nx] == SPACE) {
                    tmpGrid[ny][nx] = VIRUS;
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx, now[2] + 1});
                    spacesCnt--;
                } else if (tmpGrid[ny][nx] == VIRUS && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx, now[2] + 1});
                }
                if (spacesCnt == 0) return now[2] + 1;
            }
        }
        return -1;
    }
}
