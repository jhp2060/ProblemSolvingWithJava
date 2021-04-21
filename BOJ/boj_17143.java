// boj 17143 : 낚시왕
// state machine

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17143 {
    public static class Shark {
        int s, d, z;
        Shark(int ss, int dd, int zz) {
            this.s = ss;
            this.d = dd;
            this.z = zz;
        }
    }
    public static int r, c, m, ans = 0;
    public static Shark [][] grid;
    public static int[] dr = {0, -1, 1, 0, 0}; // 1 UP 2 DOWN
    public static int[] dc = {0, 0, 0, 1, -1}; // 3 RIGHT 4 LEFT
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int LEFT = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = stoi(st.nextToken());
        c = stoi(st.nextToken());
        grid = new Shark[r+1][c+1];
        m = stoi(st.nextToken());
        for (int i = 0; i < m; ++i) {
            int rr, cc;
            st = new StringTokenizer(br.readLine());
            rr = stoi(st.nextToken());
            cc = stoi(st.nextToken());
            grid[rr][cc] = new Shark(stoi(st.nextToken()),
                    stoi(st.nextToken()),
                    stoi(st.nextToken()));
        }
        for (int i = 1; i <= c; ++i) {
            catchShark(i);
            sharkMove();
        }
        System.out.print(ans);
    }
    public static int stoi(String s) {return Integer.parseInt(s);}
    public static void sharkMove() {
        Shark[][] tmp = new Shark[r+1][c+1];
        for (int i = 1; i <= r; ++i) {
            for (int j = 1; j <= c; ++j) {
                if (grid[i][j] == null) continue;
                Shark now = grid[i][j];
                // state : where the shark is + where to go
                // with 'n' spaces, we can get '2n - 1' states
                if (now.d <= DOWN) {   // move upward or downward
                    int nr = (r - 1) * 2;
                    int ni = (i + now.s) % nr;
                    int nd = DOWN;
                    if (now.d == UP) ni = ((2 * r - i) + now.s) % nr;
                    if (ni >= r) {
                        nd = UP;
                        if (ni > r) ni = 2 * r - ni;
                    }
                    if (tmp[ni][j] == null || tmp[ni][j].z < now.z)
                        tmp[ni][j] = new Shark(now.s, nd, now.z);
                } else {
                    int nc = (c - 1) * 2;
                    int nj = (j + now.s) % nc;
                    int nd = RIGHT;
                    if (now.d == LEFT) nj = ((2 * c - j) + now.s) % nc;
                    if (nj >= c) {
                        nd = LEFT;
                        if (nj > c) nj = 2 * c - nj;
                    }
                    if (tmp[i][nj] == null || tmp[i][nj].z < now.z)
                        tmp[i][nj] = new Shark(now.s, nd, now.z);
                }
            }
        }
        grid = tmp;
    }
    public static void catchShark (int col) {
        for (int i = 1; i <= r; ++i) {
            if (grid[i][col] != null) {
                ans += grid[i][col].z;
                grid[i][col] = null;
                break;
            }
        }
    }
}
