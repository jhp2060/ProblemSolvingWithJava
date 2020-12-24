// boj 9663 : N-queen
// brute force, backtracking

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_09663 {
    static int N;
    static int ans;
    static final int MAX = 14;
    static boolean[] columnBlocked;
    static boolean[] rightUpBlocked;    // 우상향 대각선. 같은 대각선에 있는 좌표의 x-y값이 항상 같다.
    static boolean[] leftUpBlocked;     // 좌상향 대각선. 같은 대각선에 있는 좌표의 x+y값이 항상 같다.
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        columnBlocked = new boolean [N];
        rightUpBlocked = new boolean [2*N - 1 + MAX];
        leftUpBlocked = new boolean [2*N - 1];
        go(0);
        System.out.print(ans);
    }

    // row를 기준으로 잡으면, 모든 row를 순차적으로 다 돌고 마지막 row로 갔을 때 N개의 킌을 둔 것이 됨.
    // col에 대해서는 다른 row에서 어떤 col에 퀸을 뒀으면 그 col에는 퀸을 둘 수 없음.
    // 마찬가지로 우상향, 좌상향 대각선 상에 있는 좌표 중 하나에 퀸을 두었으면 해당 대각선에 포함되는 좌표에는 퀸을 둘 수 없음.
    static void go(int row) {
        if (row >= N) {
            ans++;
            return;
        }
        for (int col = 0; col < N; ++col) {
            if (columnBlocked[col] || rightUpBlocked[col - row + MAX] || leftUpBlocked[col+row]) continue;
            columnBlocked[col] = rightUpBlocked[col - row + MAX] = leftUpBlocked[col+row] = true;
            go(row + 1);
            columnBlocked[col] = rightUpBlocked[col - row + MAX] = leftUpBlocked[col+row] = false;
        }

    }

}