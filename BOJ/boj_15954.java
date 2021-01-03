// boj 15954 인형들

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15954 {
    static int n, k;
    static double ans = 999999999;
    static int[] ppl;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        ppl = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) ppl[i] = stoi(st.nextToken());
        for (int size = k; size <= n; ++size) {
            for (int i = 0; i <= n - size; ++i) {
                double tmp = sigma(Arrays.copyOfRange(ppl, i, i + size));
                ans = Math.min(ans, tmp);
            }
        }
        System.out.print(ans);
    }
    static int stoi(String s) {return Integer.parseInt(s);}
    static double sigma(int[] subArray) {
//        int size = subArray.length;
//        double eOfNsq = 0, eSqOfN = 0;
//        for (int now : subArray) {
//            eOfNsq += (double) now * now;
//            eSqOfN += (double) now;
//        }
//        eOfNsq /= (double) size;
//        eSqOfN /= (double) size;
//        eSqOfN = eSqOfN * eSqOfN;
//        return Math.sqrt(eOfNsq - eSqOfN);
        double size = subArray.length;
        double avg = 0, var = 0;
        for (int now : subArray) avg += now;
        avg /= size;
        for (int now : subArray) var += (now - avg) * (now - avg);
        var /= size;
        return Math.sqrt(var);
    }
}
