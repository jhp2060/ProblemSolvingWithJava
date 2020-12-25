// boj 15654 : N과 M (5)
// backtracking

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15654 {
    static int N, M;
    static int[] numbers, stack;
    static boolean[] used;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        numbers = new int [N];
        stack = new int [M];
        used = new boolean [N];
        for (int i = 0; i < N; ++i) numbers[i] = stoi(st.nextToken());
        Arrays.sort(numbers);
        go(0);
        System.out.print(sb);
    }
    static int stoi(String s) {return Integer.parseInt(s);}
    static void go(int num) {
        if (num >= M) {
            for (int i = 0; i < M; ++i) sb.append(stack[i]).append(' ');
            sb.append('\n');
            return;
        }
        for (int i = 0; i < N; ++i) {
            if (used[i]) continue;
            used[i] = true; stack[num] = numbers[i];
            go(num + 1);
            used[i] = false;
        }
    }
}
