// boj 14888 : 연산자 끼워넣기
// backtracking

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14888 {
    static int n;
    static int min = 1000000000;
    static int max = -1000000000;
    static int[] operand;
    static int[] operator = new int[4]; // +-*/


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        operand = new int [n];
        for (int i = 0; i < n; ++i) operand[i] = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; ++i) operator[i] = stoi(st.nextToken());
        go(0, operand[0]);
        System.out.print(max+"\n"+min+"\n");
    }

    static int stoi(String s) {return Integer.parseInt(s);}
    static void go(int nowIdx, int nowVal) {
        if (nowIdx >= n - 1) {
            if (min > nowVal) min = nowVal;
            if (max < nowVal) max = nowVal;
            return;
        }
        int next = operand[nowIdx + 1];

        if (operator[0] > 0) {
            operator[0]--;
            go(nowIdx + 1, nowVal + next);
            operator[0]++;
        }

        if (operator[1] > 0) {
            operator[1]--;
            go(nowIdx + 1, nowVal - next);
            operator[1]++;
        }

        if (operator[2] > 0) {
            operator[2]--;
            go(nowIdx + 1, nowVal * next);
            operator[2]++;
        }

        if (operator[3] > 0) {
            operator[3]--;
            int tmp;
            if (nowVal < 0 && next > 0) {
                tmp = -nowVal / next;
                tmp = -tmp;
            } else tmp = nowVal / next;
            go(nowIdx + 1, tmp);
            operator[3]++;
        }
    }
}
