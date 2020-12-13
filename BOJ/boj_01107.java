// boj 1107 : 리모컨
// 브루트 포스

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_01107 {

    private static int N;
    private static int M;
    private static int start = 100;
    private static boolean[] isBroken = new boolean[10];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(br.readLine());
        if (M > 0) st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) isBroken[stoi(st.nextToken())] = true;

        // case 1: +-만으로 start에서 N 도달
        int ans = Math.abs(N - start);
        for (int now = 0; now <= 1000000; ++now) {
            // case 2: 번호를 눌러 now에 도달 후, +-를 사용해 N에 도달
            int tmp = switchChannel(now);
            if (tmp > 0) {
                ans = Math.min(ans, tmp + Math.abs(now - N));
            }
        }
        System.out.print(ans);
    }

    private static int stoi(String s) { return Integer.parseInt(s);}

    private static int switchChannel(int channel) {
        char[] nums = Integer.toString(channel).toCharArray();
        for (char num : nums) if (isBroken[num - '0']) return -1;
        return nums.length;
    }
}
