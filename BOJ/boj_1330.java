// boj 1330
// 사칙연산

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1330 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = stoi(st.nextToken());
        int b = stoi(st.nextToken());

        if (a > b) System.out.print(">");
        else if (a < b) System.out.print("<");
        else System.out.print("==");
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}

