// BOJ 1043 거짓말
// union-find 알고리즘

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1043 {
    static int n, m;
    static int[] root;
    static int[] rank;
    static ArrayList<Integer>[] party;
    static boolean[] knowsTruth;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());

        // initialize
        root = new int[n + 1];
        rank = new int[n + 1];
        knowsTruth = new boolean[n + 1];
        party = new ArrayList [m + 1];
        for (int i = 1; i <= n; ++i) {
            root[i] = i;
            rank[i] = 0;
            knowsTruth[i] = false;
        }

        // people who knows the truth
        st = new StringTokenizer(br.readLine());
        int pplNum = stoi(st.nextToken());
        for (int i = 1; i <= pplNum; ++i) {
            int now = stoi(st.nextToken());
            knowsTruth[now] = true;
            rank[now] = n + 1;
        }

        // people in each party : make them into sets
        for (int i = 1; i <= m; ++i) {
            st = new StringTokenizer(br.readLine());
            pplNum = stoi(st.nextToken());
            if (pplNum < 1) continue;

            party[i] = new ArrayList<>();
            int base = stoi(st.nextToken());
            party[i].add(base);
            if (pplNum < 2) continue;

            for (int j = 1; j <= pplNum - 1; ++j) {
                int now = stoi(st.nextToken());
                party[i].add(now);
                union(base, now);
            }
        }

        // if root of x, who is in the party, knows the truth,
        // don't count the party
        int ans = m;
        for (int i = 1; i <= m; ++i) {
            if (party[i] == null) {
                --ans;
                continue;
            }
            for (Integer x : party[i]){
                if (knowsTruth[find(x)]) {
                    --ans;
                    break;
                }
            }
        }
        System.out.println(ans);
    }

    public static int find(int x){
        if (root[x] == -1) return -1;
        if (root[x] == x) return x;
        // path compression : root를 바로 찾을 수 있도록
        return root[x] = find(root[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (rank[a] < rank[b]) root[a] = b;
        else {
            root[b] = a;
            if (rank[a] == rank[b]) ++rank[a];
        }
    }

    private static int stoi(String s){
        return Integer.parseInt(s);
    }
}
