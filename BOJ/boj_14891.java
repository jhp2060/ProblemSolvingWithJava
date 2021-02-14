// boj 14891 톱니바퀴

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14891 {
    static int[][] gear = new int[5][8];
    static final int LEFT = 6, RIGHT = 2;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int ans = 0;
        for (int i = 1 ; i <= 4; i++) {
            char[] s = br.readLine().toCharArray();
            gear[i] = new int[8];
            for (int j = 0; j < 8; ++j) gear[i][j] = s[j] - '0';
        }
        int k = stoi(br.readLine());
        while(k-- > 0) {
            st = new StringTokenizer(br.readLine());
            go(stoi(st.nextToken()), stoi(st.nextToken()));
        }

        if (gear[1][0] == 1) ans += 1;
        if (gear[2][0] == 1) ans += 2;
        if (gear[3][0] == 1) ans += 4;
        if (gear[4][0] == 1) ans += 8;
        System.out.print(ans);
    }
    static int stoi(String s) {return Integer.parseInt(s);}
    static void move(int gearNum, int dir) {
        if (dir == 1) { // clockwise
            int tmp = gear[gearNum][7];
            for (int i = 7; i > 0; --i) gear[gearNum][i] = gear[gearNum][i-1];
            gear[gearNum][0] = tmp;
        } else if (dir == -1){ // anti-clockwise
            int tmp = gear[gearNum][0];
            for (int i = 0; i < 7; ++i) gear[gearNum][i] = gear[gearNum][i+1];
            gear[gearNum][7] = tmp;
        }
    }
    static void go(int gearNum, int dir) {
        int[] dirs = new int[5];
        dirs[gearNum] = dir;
        int i = gearNum;
        while (--i >= 1) {
            if (gear[i][RIGHT] != gear[i+1][LEFT]) dirs[i] = -dirs[i+1];
            else break;
        }
        i = gearNum;
        while (++i <= 4) {
            if (gear[i-1][RIGHT] != gear[i][LEFT]) dirs[i] = -dirs[i-1];
            else break;
        }
        for(i = 1; i <=4; ++i) move(i, dirs[i]);
    }
}
