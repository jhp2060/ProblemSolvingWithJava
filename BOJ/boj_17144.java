// boj 17144 : 미세먼지 안녕!
// 구현


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17144 {
    public static int r, c, t, dusts = 0;
    public static int[] cleaner = new int[2];
    public static int[][] a;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cleanerIdx = 0;

        r = stoi(st.nextToken());
        c = stoi(st.nextToken());
        t = stoi(st.nextToken());
        a = new int[r + 2][c + 2];
        for (int i = 1; i <= r; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= c; ++j) {
                a[i][j] = stoi(st.nextToken());
                if (a[i][j] == -1) cleaner[cleanerIdx++] = i;
                else dusts += a[i][j];
            }
        }
        for (int i = 0; i <= r + 1; ++i) a[i][0] = a[i][c + 1] = -1;
        for (int i = 0; i <= c + 1; ++i) a[0][i] = a[r + 1][i] = -1;
        while (t-- > 0) {
            disperseDust();
            circulateAir();
        }
        System.out.print(dusts);
    }
    public static int stoi(String s) {return Integer.parseInt(s);}
    public static void disperseDust() {
        int[][] tmpA = new int[r + 1][c + 1];
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        // disperse the dust
        for (int i = 1; i <= r; ++i) {
            for (int j = 1; j <= c; ++j) {
                if (a[i][j] <= 0) continue;
                int toDisperse = (a[i][j] / 5);
                int cnt = 0;
                for (int d = 0; d < 4; ++d) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (a[nr][nc] == -1) continue;
                    tmpA[nr][nc] += toDisperse;
                    a[i][j] -= toDisperse;
                }
            }
        }

        // gather the dust from other spaces
        for (int i = 1; i <= r; ++i)
            for (int j = 1; j <= c; ++j) a[i][j] += tmpA[i][j];
    }
    public static void circulateAir() {
        int sr, prev, now;
        // R U L D
        sr = cleaner[0];
        prev = a[sr][2];
        a[sr][2] = 0;
        for (int i = 3; i <= c; ++i) { // go right
            now = a[sr][i];
            a[sr][i] = prev;
            prev = now;
        }

        for (int i = sr - 1; i >= 1; --i) { // go up
            now = a[i][c];
            a[i][c] = prev;
            prev = now;
        }

        for (int i = c - 1; i >= 1; --i) { // go left
            now = a[1][i];
            a[1][i] = prev;
            prev = now;
        }

        for (int i = 2; i < sr; ++i) { // go down
            now = a[i][1];
            a[i][1] = prev;
            prev = now;
        }
        dusts -= prev;

        // R D L U
        sr = cleaner[1];
        prev = a[sr][2];
        a[sr][2] = 0;
        for (int i = 3; i <= c; ++i) { // go right
            now = a[sr][i];
            a[sr][i] = prev;
            prev = now;
        }

        for (int i = sr + 1; i <= r; ++i) { // go down
            now = a[i][c];
            a[i][c] = prev;
            prev = now;
        }

        for (int i = c - 1; i >= 1; --i) { // go left
            now = a[r][i];
            a[r][i] = prev;
            prev = now;
        }

        for (int i = r - 1; i > sr; --i) { // go up
            now = a[i][1];
            a[i][1] = prev;
            prev = now;
        }
        dusts -= prev;
    }
}
