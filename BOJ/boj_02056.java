// boj 2056 작업
// 위상정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_02056 {

    private final static int MAX = 10000 + 1;

    private static int n;
    private static int[] ind = new int [MAX];
    private static int[] a = new int [MAX];
    private static int[] d = new int [MAX];
    private static LinkedList<Integer> q = new LinkedList<>();
    private static LinkedList<Integer>[] adj  = new LinkedList [MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; ++i) adj[i] = new LinkedList<Integer>();
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            if (size < 1) continue;
            for (int j = 0; j < size; ++j) {
                adj[Integer.parseInt(st.nextToken())].add(i);
                ind[i]++;
            }
        }
        for (int i = 1; i <=n; ++i)
            if (ind[i] == 0) {
                q.push(i);
                d[i] = a[i];
            }

        while (!q.isEmpty()) {
            int now = q.pop();
            for (int next : adj[now]) {
                d[next] = Math.max(d[next], d[now] + a[next]);
                if (--ind[next] == 0) q.push(next);
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) ans = Math.max(ans, d[i]);

        System.out.println(ans);
    }
}
