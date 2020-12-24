// boj 13549 숨바꼭질 3
// 0-1 bfs

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_13549 {

    static int N, K;
    static final int MAX = 100000 * 2;
    static LinkedList<Pair> q = new LinkedList<>();
    static boolean[] visited = new boolean[MAX + 1];

    static class Pair {
        int loc;
        int sec;
        Pair (int l, int s) {
            this.loc = l;
            this.sec = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        q.add(new Pair(N, 0));
        while (!q.isEmpty()) {
            Pair now = q.pop();
            if (visited[now.loc]) continue;
            if (now.loc == K) {
                System.out.print(now.sec);
                return;
            }
            visited[now.loc] = true;
            if (now.loc * 2 <= MAX) q.push(new Pair(now.loc * 2, now.sec));
            if (now.loc - 1 >= 0) q.add(new Pair(now.loc - 1, now.sec + 1));
            if (now.loc + 1 <= MAX) q.add(new Pair(now.loc + 1, now.sec + 1));
        }
    }

    static int stoi(String s) { return Integer.parseInt(s);};
}
