// boj 15282 1로 만들기 2
// dp + BFS

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_12582 {

    private static class Pair {
        int num;
        int cnt;
        Pair (int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    private static int n;
    private static final int MAX = 1000000 + 10;
    private static final int INF = 999999999;

    // dp[i] : i에 도달하는데 걸리는 최소의 횟수. INF 인 경우, 해당 수에 아직 도달하지 못함
    public static int[] dp = new int [MAX];
    public static int[] from = new int [MAX];
    public static LinkedList<Pair> q = new LinkedList<>();

    private static void init() {
        for (int i = 1; i <= n; i++) dp[i] = INF;
        for (int i = 1; i <= n; i++) from[i] = -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        init();
        q.push(new Pair(n, 0));
        while (!q.isEmpty()) {
            int num = q.peek().num;
            int cnt = q.peek().cnt;
            q.poll();
            if (num == 1) {
                dp[1] = cnt;
                break;
            }
            if (num % 3 == 0 && dp[num / 3] > cnt) {
                q.add(new Pair(num / 3, cnt + 1));
                dp[num/3] = cnt + 1;
                from[num/3] = num;
            }
            if (num % 2 == 0 && dp[num / 2] > cnt) {
                q.add(new Pair(num / 2, cnt + 1));
                dp[num/2] = cnt + 1;
                from[num/2] = num;
            }
            if (num > 1 && dp[num - 1] > cnt) {
                q.add(new Pair(num - 1, cnt + 1));
                dp[num - 1] = cnt + 1;
                from[num - 1] = num;
            }
        }
        System.out.println(dp[1]);
        int now = 1;
        Stack<Integer> s = new Stack<>();
        s.push(now);
        while (now < n) {
            now = from[now];
            s.push(now);
        }
        while (!s.empty()) {
            System.out.print(s.pop() + " ");
        }

    }

}
