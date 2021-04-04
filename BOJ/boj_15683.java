// boj 15683 감시
// backtracking

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Vector;

public class boj_15683 {
    static int n,m;
    static int[][] grid;
    static int ans = 987654321;
    static int [][] cams;
    static final int[][][][] cctv = {
            {},
            {
                {{-1,0}},
                {{0,1}},
                {{1,0}},
                {{0,-1}}
            },
            {
                {{-1,0},{1,0}},
                {{0,1},{0,-1}}
            },
            {
                {{-1,0},{0,1}},
                {{0,1},{1,0}},
                {{1,0},{0,-1}},
                {{0,-1},{-1,0}}
            },
            {
                {{0,-1},{-1,0},{0,1}},
                {{-1,0},{0,1},{1,0}},
                {{0,1},{1,0},{0,-1}},
                {{1,0},{0,-1},{-1,0}}
            },
            {
                {{-1,0},{0,1},{1,0},{0,-1}}
            }
    };
    static final int BLANK = 0;
    static final int WALL = 6;
    static final int SHADE = 7;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        grid = new int[n][m];
        Vector<int[]> tmpCams = new Vector<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                grid[i][j] = stoi(st.nextToken());
                if (1 <= grid[i][j] && grid[i][j] <= 5) tmpCams.add(new int[]{i, j});
            }
        }
        cams = tmpCams.toArray(new int[tmpCams.size()][2]);
        go(0);
        System.out.print(ans);
    }
    public static int stoi(String s) {return Integer.parseInt(s);}
    public static void go(int camIdx) {
        if (camIdx == cams.length) {
            int blankCnt = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j)
                    if (grid[i][j] == BLANK) blankCnt++;
            }
            if (blankCnt < ans) ans = blankCnt;
            return;
        }
        int nowCctv = grid[cams[camIdx][0]][cams[camIdx][1]];
        for (int[][] dirs: cctv[nowCctv]) {
            draw(cams[camIdx][0], cams[camIdx][1], SHADE, dirs);
            go(camIdx + 1);
            draw(cams[camIdx][0], cams[camIdx][1], -SHADE, dirs);
        }
    }
    public static void draw(int r, int c, int toDraw, int[][] dirs) {
        for (int[] dir: dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            while (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                if (grid[nr][nc] == WALL) break;
                if (grid[nr][nc] == BLANK || grid[nr][nc] >= SHADE)
                    grid[nr][nc] += toDraw;
                nr += dir[0];
                nc += dir[1];
            }
        }
    }
}
