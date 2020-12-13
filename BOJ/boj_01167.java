// boj 1167
// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_01167 {
    private static int max = 0;
    private static int start = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());

        @SuppressWarnings("unchecked")
        List<Link>[] lists = new ArrayList[v + 1];

        for(int i = 1; i <= v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            lists[n] = new ArrayList<Link>();

            while(true) {
                int m = Integer.parseInt(st.nextToken());

                if(m == -1) break;

                lists[n].add(new Link(m, Integer.parseInt(st.nextToken())));
            }
        }

        boolean[] visited = new boolean[v + 1];
        dfs(lists, visited, 1, 0);

        visited = new boolean[v + 1];
        dfs(lists, visited, start, 0);

        System.out.println(max);
    }

    private static void dfs(List<Link>[] lists, boolean[] visited, int i, int length) {
        visited[i] = true;

        if(length > max) {
            max = length;
            start = i;
        }

        for(Link next : lists[i]) {
            int m = next.getM();
            int distance = next.getDistance();

            if(!visited[m]) {
                dfs(lists, visited, m, length + distance);
                visited[m] = false;
            }
        }
    }

    private static class Link {
        private int m;
        private int distance;

        public Link(int m, int distance) {
            this.m = m;
            this.distance = distance;
        }

        public int getM() {
            return m;
        }

        public int getDistance() {
            return distance;
        }
    }

    public static int stoi(String s){
        return Integer.parseInt(s);
    }
}


