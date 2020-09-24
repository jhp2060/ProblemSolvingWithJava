// boj 16953 A -> B
// BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_16953 {

    private static class Pair{
        public long num;
        public long cnt;
        Pair(long num, long cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }

    private static LinkedList<Pair> q = new LinkedList<>();
    private static long start;
    private static long end;
    private static long ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Long.parseLong(st.nextToken());
        end = Long.parseLong(st.nextToken());

        q.push(new Pair(start, 0L));

        long now = -1;
        long cnt = 0;
        while(!q.isEmpty()) {
            now = q.peek().num;
            cnt = q.peek().cnt;
            q.poll();

            if (now == end) {
                ans = cnt + 1L;
                break;
            }

            if (now * 2L <= end) q.add(new Pair(now * 2L, cnt + 1L));
            if (now * 10L + 1L <= end) q.add(new Pair(now*10L + 1L, cnt + 1L));
        }
        System.out.println(ans);
    }
}
