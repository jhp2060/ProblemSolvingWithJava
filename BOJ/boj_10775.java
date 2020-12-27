// boj 10775 공항
// disjoint set, union-find

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10775 {
    static int g, p, ans = 0;
    // parent[i] : i번째 게이트(자식노드)까지 도킹 가능할 때, 실제로 도킹 가능한 게이트 중 번호가 가장 높은 게이트(루트노드).
    static int[] parent;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = stoi(br.readLine());
        p = stoi(br.readLine());
        parent = new int [g + 1];
        for (int i = 1; i <= g; ++i) parent[i] = i;
        while (p-- > 0) {
            int now = stoi(br.readLine());
            // 실제로 도킹할 게이트(루트노드) 찾기
            now = find(now);
            if (now <= 0) break;    // 더이상 도킹할 곳이 없음
            ++ans;
            parent[now] = now - 1;
        }
        System.out.print(ans);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
    static int find(int child) {
        if (child == parent[child]) return child;
        return parent[child] = find(parent[child]);
    }


}
