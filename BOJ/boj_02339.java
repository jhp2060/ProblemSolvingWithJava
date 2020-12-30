// boj 2339 스도쿠
// 백트래킹

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_02339 {
    static int cnt = 0;
    static boolean found = false;
    static int[][] sudoku = new int [9][9];
    static boolean[][] row = new boolean[9][10];
    static boolean[][] col = new boolean[9][10];
    static boolean[][] square = new boolean[9][10];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] line;
        for (int i = 0; i < 9; ++i) {
            sudoku[i] = new int[9];
            row[i] = new boolean[10];
            col[i] = new boolean[10];
            square[i] = new boolean[10];
        }
        for (int i = 0; i < 9; ++i) {
            line = br.readLine().toCharArray();
            for (int j = 0; j < 9; ++j) {
                int now = line[j] - '0';
                sudoku[i][j] = now;
                if (now > 0) {
                    cnt++;
                    row[i][now] = true;
                    col[j][now] = true;
                    square[(i / 3) * 3 + j / 3][now] = true;
                }
            }
        }
        go(0,0);
    }

    static void go(int x, int y) {
        if (found) return;
        if (cnt >= 81) {
            found = true;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) sb.append(sudoku[i][j]);
                sb.append('\n');
            }
            System.out.print(sb);
            return;
        }
        int nx, ny;
        if (y + 1 > 8) {
            nx = x + 1;
            ny = 0;
        } else {
            nx = x;
            ny = y + 1;
        }
        if (sudoku[x][y] > 0) {
            go(nx, ny);
        } else {
            for (int i = 1; i <= 9; ++i) {
                if (placeable(x,y,i)) {
                    place(x,y,i);
                    go(nx, ny);
                    displace(x,y,i);
                }
            }
        }
    }

    static boolean placeable (int x, int y, int toPlace) {
        return !row[x][toPlace] && !col[y][toPlace] && !square[(x/3)*3+y/3][toPlace];
    }
    static void place(int x, int y, int toPlace) {
        sudoku[x][y] = toPlace;
        cnt++;
        row[x][toPlace] = true;
        col[y][toPlace] = true;
        square[(x/3)*3+y/3][toPlace] = true;
    }
    static void displace(int x, int y, int toDisplace) {
        sudoku[x][y] = 0;
        cnt--;
        row[x][toDisplace] = false;
        col[y][toDisplace] = false;
        square[(x/3)*3+y/3][toDisplace] = false;
    }
    
}


