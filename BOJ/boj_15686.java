// boj 15686 치킨 배달
// backtracking

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_15686 {
    static int n, m, ans = 987654321;
    static int houseCnt = 0, chickenCnt = 0;
    static int[][] houses, chickens;
    static int[][] diff;
    static LinkedList<Integer> selected = new LinkedList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        houses = new int [2 * n][2];
        chickens = new int [13][2];
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; ++j) {
                int now = stoi(st.nextToken());
                if (now == 1) houses[houseCnt++] = new int[] {i,j};
                else if (now == 2) chickens[chickenCnt++] = new int[] {i,j};
            }
        }
        diff = new int[houseCnt][chickenCnt];
        for (int i = 0; i < houseCnt; ++i) {
            for (int j = 0; j < chickenCnt; ++j)
                diff[i][j] = Math.abs(houses[i][0] - chickens[j][0])
                        + Math.abs(houses[i][1] - chickens[j][1]);
        }
        go(0);
        System.out.print(ans);
    }
    public static int stoi(String s) {return Integer.parseInt(s);}
    public static void go (int chickenIdx) {
        if (selected.size() == m) {
            int tmp = calculate();
            if (tmp < ans) ans = tmp;
            return;
        }
        for (int i = chickenIdx; i < chickenCnt; ++i) {
            selected.push(i);
            go(i+ 1);
            selected.pop();
        }
    }
    public static int calculate() {
        int ret = 0;
        for (int i = 0 ; i < houseCnt; ++i) {
            int tmp = 987654321;
            for (int j : selected)
                if (diff[i][j] < tmp) tmp = diff[i][j];
            ret += tmp;
        }
        return ret;
    }
}
