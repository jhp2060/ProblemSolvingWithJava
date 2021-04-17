// boj 16236 아기상어

import javax.swing.plaf.synth.SynthTableHeaderUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_16236 {
    /**
    0: 빈 칸
    1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
    9: 아기 상어의 위치
     **/
    static int n, ans = 0;
    static int sharkSize = 2, sy, sx, haveEaten = 0;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int[][] grid;

    public static class Node {
        int y, x, d;
        Node (int yy, int xx, int dd) {
            y = yy;
            x = xx;
            d = dd;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        grid = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j)  {
                grid[i][j] = stoi(st.nextToken());
                if (grid[i][j] == 9) {
                    grid[i][j] = 0;
                    sy = i;
                    sx = j;
                }
            }
        }

        // bfs
        while (true) { // till to call mommy shark
            LinkedList<Node> q = new LinkedList<>();
            boolean[][] visited = new boolean[n][n];
            q.add(new Node(sy, sx, 0));
            visited[sy][sx] = true;
            int time = 0;
            boolean foundToEat = false;
            while (!q.isEmpty()) { // till to find which fish to eat
                Node now = q.peekFirst();
                Node toEat = now;
                while (!q.isEmpty() && now.d == time) { // till find the fish to eat at the same time
                    q.pop();
                    int y = now.y;
                    int x = now.x;
                    if (grid[y][x] >= 1 && grid[y][x] < sharkSize) { // able to eat
                        if (!foundToEat || toEat.y > y || (toEat.y == y && toEat.x > x)) {
                            // find the minimum distance
                            toEat = now;
                            foundToEat = true;
                        }
                    } else { // go thru
                        for (int i = 0; i < 4; ++i) {
                            int ny = y + dy[i];
                            int nx = x + dx[i];
                            if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                            if (visited[ny][nx]) continue;
                            if (grid[ny][nx] >= 0 && grid[ny][nx] <= sharkSize) {
                                visited[ny][nx] = true;
                                q.add(new Node(ny, nx, time + 1));
                            }
                        }
                    }
                    now = q.peekFirst();
                }
                // eat the fish
                if (foundToEat) {
                    if (++haveEaten == sharkSize) {
                        sharkSize++;
                        haveEaten = 0;
                    }
                    sy = toEat.y;
                    sx = toEat.x;
                    grid[sy][sx] = 0;
                    ans += toEat.d;

//                    StringBuilder sb = new StringBuilder();
//                    sb.append("\n");
//                    for (int i = 0; i < n; ++i) {
//                        for (int j = 0; j < n; ++j) sb.append(grid[i][j] + " ");
//                        sb.append('\n');
//                    }
//                    System.out.println(sb);
                    break;
                }
                else time++;  // cannot find at this time, go further
            }
            if (!foundToEat) break; // call the mommy shark
        }
        System.out.print(ans);
    }
    public static int stoi(String s) {return Integer.parseInt(s);}
}
