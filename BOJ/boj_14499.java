// boj 14499: 주사위 굴리기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14499 {
    static int n, m, x, y, k;
    static int[][] map;
    static int[] dice = new int[7];
    static int[] dy = {0, 1, -1, 0, 0};
    static int[] dx = {0, 0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        x = stoi(st.nextToken());
        y = stoi(st.nextToken());
        k = stoi(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            map[i] = new int [m];
            for (int j = 0; j < m; ++j) map[i][j] = stoi(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        while (k-- > 0) {
            int dir = stoi(st.nextToken());
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            rollTo(dir);
            x = nx;
            y = ny;
            if (map[x][y] == 0) map[x][y] = dice[6];
            else {
                dice[6] = map[x][y];
                map[x][y] = 0;
            }
            sb.append(dice[1]).append('\n');
        }
        System.out.print(sb);
    }
    static int stoi(String s) {return Integer.parseInt(s);}
    static void rollTo (int dir) {
        int tmp;
        if (dir == 1) {
            tmp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = tmp;
        } else if (dir == 2) {
            tmp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        } else if (dir == 3) {
            tmp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;
        } else {
            tmp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;
        }
    }
}
