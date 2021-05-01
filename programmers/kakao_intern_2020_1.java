public class kakao_intern_2020_1 {
    // * = 10, # = 11
    static int[] ys = {3, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3};
    static int[] xs = {1, 0, 1, 2, 0, 1, 2, 0 ,1, 2, 0, 2};
    static int left = 10, right = 11;

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        System.out.print(solution(numbers, hand));
    }
    public static String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        for (int n : numbers) {
            if (n == 0 || n % 3 == 2) {
                int leftDist = Math.abs(ys[n] - ys[left]) + Math.abs(xs[n] - xs[left]);
                int rightDist = Math.abs(ys[n] - ys[right]) + Math.abs(xs[n] - xs[right]);
                if (leftDist > rightDist || leftDist == rightDist && hand.equals("right")) {
                    right = n;
                    answer.append("R");
                } else {
                    left = n;
                    answer.append("L");
                }
            } else if (n % 3 == 1) {
                answer.append("L");
                left = n;
            } else {
                answer.append("R");
                right = n;
            }
        }
        return answer.toString();
    }
}
