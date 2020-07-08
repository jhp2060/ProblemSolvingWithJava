// boj 1629
// divide and conquer

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1629 {

    private static long a;
    private static long b;
    private static long c;
    private static long ans;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = getNextInt(st);
        b = getNextInt(st);
        c = getNextInt(st);

        ans = go(a,b,c);
        System.out.print(ans);
    }

    public static long go(long a, long b, long c){
        if (b == 1) return a % c;

        long result = go(a, b/2, c);
        result = (result * result) % c;

        if (b % 2 == 1) result = result * a % c;

        return result % c;
    };

    public static long getNextInt(StringTokenizer st) {
        return (long) stoi(st.nextToken());
    }

    public static int stoi(String s){
        return Integer.parseInt(s);
    }
}
