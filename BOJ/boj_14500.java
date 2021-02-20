// boj 14500 테트로미노
// 브루트포스

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14500 {
    static int n, m, ans = 0;
    static int[][] grid;
    static final int[][][] BLOCKS = {
            { {0,0}, {0,1}, {1,0}, {1,1} }, // ㅁ
            { {0,0}, {0,1}, {0,2}, {0,3} }, // ㅡ
            { {0,0}, {1,0}, {2,0}, {3,0} },
            { {0,0}, {0,1}, {0,2}, {1,0} }, // ㄴ
            { {0,2}, {1,0}, {1,1}, {1,2} },
            { {0,0}, {1,0}, {1,1}, {1,2} },
            { {0,0}, {0,1}, {0,2}, {1,2} },
            { {0,0}, {1,0}, {2,0}, {2,1} },
            { {0,0}, {0,1}, {1,1}, {2,1} },
            { {0,0}, {0,1}, {1,0}, {2,0} },
            { {0,1}, {1,1}, {2,0}, {2,1} },
            { {0,0}, {1,0}, {1,1}, {2,1} }, // ㄱㄴ
            { {0,1}, {1,0}, {1,1}, {2,0} },
            { {0,1}, {0,2}, {1,0}, {1,1} },
            { {0,0}, {0,1}, {1,1}, {1,2} },
            { {0,0}, {0,1}, {0,2}, {1,1} }, // ㅗ
            { {0,1}, {1,0}, {1,1}, {1,2} },
            { {0,1}, {1,0}, {1,1}, {2,1} },
            { {0,0}, {1,0}, {1,1}, {2,0} }
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        grid = new int[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) grid[i][j] = stoi(st.nextToken());
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) go(i,j);
        }
        System.out.println(ans);
    }
    static int stoi(String s) {return Integer.parseInt(s);}
    static void go(int y, int x) {
        for (int[][] block: BLOCKS) {
            int tmp = 0;
            boolean ableToMake = true;
            for (int[] b : block) {
                int ny = b[0] + y;
                int nx = b[1] + x;
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                    ableToMake = false;
                    break;
                }
                tmp += grid[ny][nx];
            }
            if (!ableToMake) continue;
            if (tmp > ans) ans = tmp;
        }
    }
}
