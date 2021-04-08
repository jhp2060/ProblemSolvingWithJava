// boj 15683 드래곤 커브
// 구현

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15685 {
    static int n;
    static boolean[][] isDotted = new boolean[100+1][100+1]; // isDotted[y][x]
    static int[] dy = {0,-1,0,1};
    static int[] dx = {1,0,-1,0};
    // direction change
    // 0 1 2 3 -> 1 2 3 0

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            draw(stoi(st.nextToken()), stoi(st.nextToken()),
                    stoi(st.nextToken()), stoi(st.nextToken()));
        }
        System.out.print(count());
    }
    public static int stoi(String s) {return Integer.parseInt(s);}
    public static void draw(int x, int y, int d, int g) {
        int ny = y + dy[d], nx = x + dx[d];
        int[] dirs = new int [1];
        isDotted[y][x] = true;
        isDotted[ny][nx] = true;
        dirs[0] = d;
        for (int i = 1; i <= g; ++i) {
            int[] tmpDirs = new int [dirs.length * 2];
            for (int j = 0; j < dirs.length; ++j) {
                int nd = (dirs[j] + 1) % 4;
                ny += dy[nd];
                nx += dx[nd];
                isDotted[ny][nx] = true;
                tmpDirs[j + dirs.length] = dirs[j];
                tmpDirs[dirs.length - j - 1] = nd;
            }
            dirs = tmpDirs;
        }
    }
    public static int count() {
        int ret = 0;
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 100; ++j) {
                if (!isDotted[i][j]) continue;
                if (isDotted[i+1][j] && isDotted[i][j+1] && isDotted[i+1][j+1])
                    ret++;
            }
        }
        return ret;
    }

}
