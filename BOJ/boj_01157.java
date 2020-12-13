// boj 1157 단어 공부
// 문자열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_01157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char ans = '?';
        int maxVal = -1;
        int[] letterCount = new int[26];
        for (int i = 0; i < 26; i++) letterCount[i] = 0;
        String word = st.nextToken();
        char[] chars = word.toLowerCase().toCharArray();
        for (char c : chars) letterCount[c - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (maxVal < letterCount[i]) {
                maxVal = letterCount[i];
                ans = (char) (i + 'A');
            }
            else if (maxVal == letterCount[i]) ans = '?';
        }
        System.out.println(ans);
    }
}
