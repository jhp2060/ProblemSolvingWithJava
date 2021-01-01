// boj 2252 줄세우기
// 위상정렬

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_02252 {
    static int n, m;
    static int[] inDegree;
    static LinkedList<Integer> [] graph;
    static LinkedList<Integer> q = new LinkedList<Integer>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        inDegree = new int [n+1];
        graph = new LinkedList [n+1];
        for (int i = 1; i <= n; ++i) graph[i] = new LinkedList<Integer>();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            graph[a].push(b);
            inDegree[b]++;
        }
        for (int i = 1; i <= n; ++i) if (inDegree[i] == 0) q.add(i);
        while (!q.isEmpty()) {
            int now = q.pop();
            sb.append(now).append(' ');
            for (int next : graph[now]) {
                inDegree[next]--;
                if (inDegree[next] == 0) q.add(next);
            }
        }
        System.out.print(sb);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
