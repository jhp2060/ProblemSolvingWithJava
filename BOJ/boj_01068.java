// boj 1068 : 트리
// dfs, 트리

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_01068 {
    private static final int MAX = 50 + 1;
    private static int n;
    private static int root;
    private static int toDelete;
    private static LinkedList<Integer>[] graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        graph = new LinkedList [n];

        for (int i = 0; i < n; ++i) graph[i] = new LinkedList<Integer>();
        st = new StringTokenizer(br.readLine());
        for (int child = 0; child < n; ++child) {
            int parent = stoi(st.nextToken());
            if (parent < 0) {
                root = child;
                continue;
            }
            graph[parent].add(child);
        }
        toDelete = stoi(br.readLine());

        System.out.print(dfs(root));
    }

    private static int dfs(int start) {
        if (start == toDelete) return 0;
        if (graph[start].isEmpty()) return 1;
        int ret = 0;
        for (int child : graph[start]) {
            if (graph[start].size() == 1 && child == toDelete) ret++;
            else ret += dfs(child);
        }
        return ret;
    }

    private static int stoi(String s) {return Integer.parseInt(s);}

}
