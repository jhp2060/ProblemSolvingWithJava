// boj 2475 : 검증수
// 사칙연산

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2475 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ans = 0;

        for (int i = 0; i <5; i++) {
            int now = stoi(st.nextToken());
            ans += now * now;
        }
        System.out.println(ans % 10);
        
        br.close();
    }

    private static int stoi (String s) {
        return Integer.parseInt(s);
    }
}

