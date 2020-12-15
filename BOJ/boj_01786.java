// boj 1786 : 찾기
// KMP 알고리즘

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class boj_01786 {

    static String T;
    static String P;
    static int[] failure;
    static LinkedList<Integer> ans = new LinkedList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = br.readLine();
        P = br.readLine();

        // failure function :  문자열 P의 부분 문자열 중, prefix와 suffix가 동일한 최장길이를 미리 구해놓은 것
        // failure[i] : 문자열 P의 0부터 i까지의 부분 문자열(P[0..i]) 중, prefix와 suffix가 동일한 최장 길이
        // prefix는 무조건 0번째 문자부터 시작, suffix는 무조건 맨 마지막 문자로 끝남.
        // P의 길이가 l이고, failure[i] = f라면, P[0 .. f-1] == P[l-1-f .. l-1] (단, f<=i)
        int Plen = P.length(), j = 0;
        char[] Pchar = P.toCharArray();
        failure = new int [Plen];
        for (int i = 1; i < Plen; ++i) {
            // i는 suffix의 맨 마지막 문자, j는 prefix의 맨 마지막 문자이므로
            // 가장 긴 prefix부터 길이를 줄여나가면서 prefix==suffix를 만족하는 prefix 찾기.
            while (j > 0 && Pchar[i] != Pchar[j]) j = failure[j - 1];

            // 기존의 최장 prefix에 문자 하나(P[j]==P[i]) 더 해주기
            if (Pchar[i] == Pchar[j]) failure[i] = ++j;
        }

        // KMP 함수
        char[] Tchar = T.toCharArray();
        j = 0;
        for (int i = 0; i < T.length(); ++i) {
            while (j > 0 && Tchar[i] != Pchar[j]) j = failure[j - 1];
            if (Tchar[i] == Pchar[j]) {
                // P와 일치하는 T의 부분문자열 찾음
                // i - Plen + 1 : P와 일치하는 T의 부분 문자열이 시작되는 인덱스
                if (j == Plen - 1) {
                    ans.add(i - Plen + 1);
                    // 부분 문자열을 찾았으므로 prefix가 가장 긴 조건부터 while에서 찾을 수 있도록 리셋
                    j = failure[j];
                }
                else j++;
            }
        }

        System.out.println(ans.size());
        for (int a : ans) System.out.print((a+1) + " ");
    }
}
