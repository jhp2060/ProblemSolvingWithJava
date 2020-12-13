// boj 1018 체스판 다시 칠하기
// 브루트포스, 문자열 처리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_01018 {

    private static final char[][] black88 = {
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
    };
    private static final char[][] white88 = {
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
    };
    private static int n, m;
    private static char[][] input;

    private static int go(int y, int x) {
        int blackAns = 0;
        int whiteAns = 0;

        for (int j = y; j < y+8; j++) {
            for (int i = x; i < x+8; i++) {
                if (black88[j - y][i - x] != input[j][i]) ++blackAns;
                if (white88[j - y][i - x] != input[j][i]) ++whiteAns;
            }
        }
        return Math.min(blackAns, whiteAns);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        input = new char[n][m];
        for(int j = 0; j < n; j++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int i = 0; i < m; i++) input[j][i] = str.charAt(i);
        }

        int ans = 987654321;

        for(int y = 0; y <= n - 8; y++) {
            for (int x = 0; x <= m - 8; x++) {
                ans = Math.min(go(y,x), ans);
            }
        }
        System.out.println(ans);
    }
}
