// boj 10942 펠린드롬?
// dp

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10942 {
    static int n, m, s, e;
    static int[] numbers;
    static boolean[][] isPalindrome;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = stoi(st.nextToken());
        numbers = new int [n];
        isPalindrome = new boolean [n][n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            numbers[i] = stoi(st.nextToken());
            isPalindrome[i][i] = true;
            if (i > 0 && numbers[i-1] == numbers[i]) isPalindrome[i-1][i] = true;
        }
        for (int l = 3; l <= n; ++l) {
            for (int i = 0; i <= n - l; ++i) {
                if (numbers[i] == numbers[i + l - 1] && isPalindrome[i + 1][i + l - 2])
                    isPalindrome[i][i + l - 1] = true;
            }
        }
        m = stoi(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            s = stoi(st.nextToken()) - 1;
            e = stoi(st.nextToken()) - 1;
            sb.append(isPalindrome[s][e] ? 1 : 0).append('\n');
        }
        System.out.print(sb);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
