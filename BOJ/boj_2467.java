// boj 2467 용액
// two pointer(sliding window)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_2467 {

    private static int n;
    private static int liquids[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        liquids = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) liquids[i] = Integer.parseInt(st.nextToken());
        int beg = 0, end = n - 1;
        int ansLeft = liquids[beg], ansRight = liquids[end];
        int sum = 2000000000;
        while (beg < end) {
            int left = liquids[beg], right = liquids[end];
            if (Math.abs(left + right) < sum) {
                ansLeft = left;
                ansRight = right;
                sum = Math.abs(left + right);
                continue;
            }
            if (left + right < 0) beg++;
            else end--;
        }
        System.out.print(ansLeft + " " + ansRight);
    }
}
