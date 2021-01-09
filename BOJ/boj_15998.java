// boj 15998 카카오머니
// gcd, 유클리드 호제법

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15998 {
    static int n;
    static long m = 0;
    static long[] a, b;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        a = new long[n + 1];
        b = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            a[i] = stol(st.nextToken());
            b[i] = stol(st.nextToken());
            m = gcd(m, b[i] - b[i - 1] - a[i]);
        }
        for (int i = 1; i <= n; ++i) {
            if (b[i] - b[i - 1] == a[i]) continue;
            if (a[i] > 0                    // 입금만 했는데도 잔액이 맞지 않는 경우
                    || (m > 0 && m <= b[i]) // 최소출금 금액보다 더 많이 뽑은 경우
                    || -a[i] < b[i - 1]     // 이전 잔액보다 적게 출금했는데도 잔액이 맞지 않는 경우
            ) {
                System.out.print(-1);
                return;
            }
        }
        System.out.print(m > 0 ? m : 1);
    }

    static long stol(String s) { return Long.parseLong(s);}
    static long gcd(long a, long b) { // a >= b로 가정. 만약 a < b라고 해도, 첫 a%b를 하면 몫이 0, 나머지가 a가 되므로 a b가 swap됨
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return Math.abs(a);
    }
}
