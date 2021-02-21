// boj 14890 경사로

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14890 {
    static int n, l;
    static int[][] grid;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = 0;
        n = stoi(st.nextToken());
        l = stoi(st.nextToken());
        grid = new int[n][n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < n; ++j) grid[i][j] = stoi(st.nextToken());
        }

        for (int row = 0; row < n; ++row) {
            int cnt = 1;    // 경사로를 그 위에 놓을 수 있는 칸의 수
            boolean canPass = true;
            for (int col = 0; col < n - 1; ++col) {
                int delta = grid[row][col+1] - grid[row][col];
                if (delta == 0) cnt++;  // 경사로를 안 만들기 때문에 경사로를 놓을 수 있는 칸만 누적.
                else if (delta == 1 && cnt >= l) cnt = 1;       // 상향 경사로. 현재 칸 포함 l개의 칸이 필요.
                else if (delta == -1 && cnt >= 0) cnt = 1 - l;  // 하향 경사로. 현재 칸에는 경사로를 안 놓아도 됨
                else {
                    canPass = false;
                    break;
                }
            }
            if (canPass && cnt >= 0) ans+= 1; // 경사로가 밖으로 안빠져나가기(cnt < 0)만 하면 ok.
        }

        for (int col = 0; col < n; ++col) {
            int cnt = 1;
            boolean canPass = true;
            for (int row = 0; row < n - 1; ++row) {
                int delta = grid[row+1][col] - grid[row][col];
                if (delta == 0) cnt++;
                else if (delta == 1 && cnt >= l) cnt = 1;
                else if (delta == -1 && cnt >= 0) cnt = 1 - l;
                else {
                    canPass = false;
                    break;
                }
            }
            if (canPass && cnt >= 0) ans+= 1;
        }

        System.out.print(ans);
    }
    static int stoi(String s) {return Integer.parseInt(s);}
}
