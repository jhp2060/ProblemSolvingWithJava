// boj 16235 나무 재테크
// 구현

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_16235 {
    static int n, m, k, ans = 0;
    static int[][] a;
    static int[][] nutrient;
    static LinkedList<Integer>[][] ground;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());
        ground = new LinkedList[n + 1][n + 1];
        a = new int[n + 1][n + 1];
        nutrient = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; ++j) {
                a[i][j] = stoi(st.nextToken());
                nutrient[i][j] = 5;
                ground[i][j] = new LinkedList<>();
            }
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            int z = stoi(st.nextToken());
            ground[x][y].push(z);
            ans++;
        }
        for (int year = 1; year <= k ; ++year) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n ;++j) {
                    LinkedList<Integer> trees = ground[i][j];
                    // spring
                    for (int k = 0; k < trees.size(); ++k) {
                        int t = trees.get(k);
                        if (nutrient[i][j] - t >= 0) {
                            nutrient[i][j] -= t;    // get nutrient as much as its age
                            ground[i][j].set(k, t + 1);    // get 1 more age
                        } else {
                            // summer
                            while(k < ground[i][j].size()) {
                                nutrient[i][j] += (ground[i][j].remove(k) / 2);
                                ans--;
                            }
                            break;
                        }
                    }
                }
            }
            // autumn
            int [][] drdc = {
                    {-1,-1}, {-1,0}, {-1, 1},
                    {0, -1},         {0,  1},
                    {1, -1}, {1, 0}, {1,  1}
            };
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n ;++j) {
                    // winter
                    nutrient[i][j] += a[i][j];      // supply nutrients as much as 'a'
                    LinkedList<Integer> trees = ground[i][j];
                    for (int t: trees) {
                        if (t % 5 == 0)             // propagate trees
                            for (int[] dd : drdc) plantNewTree(i + dd[0], j + dd[1]);
                    }
                }
            }
        }
        System.out.println(ans);
    }
    public static int stoi(String s) {return Integer.parseInt(s);}
    public static void plantNewTree(int r, int c) {
        if (r < 1 || r > n || c < 1 || c > n) return;
        ground[r][c].push(1);
        ans++;
    }
}
