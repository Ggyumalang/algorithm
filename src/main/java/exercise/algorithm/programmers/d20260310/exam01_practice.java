/**
 * 2025 카카오 하반기 1차 > 노란불 신호등
 */

package exercise.algorithm.programmers.d20260310;

import java.util.Arrays;

public class exam01_practice {

    public static void main(String[] args) {
        int[][] signals = {{2, 1, 2}, {5, 1, 1}};
        System.out.println(solution(signals));

        signals = new int[][]{{2, 3, 2}, {3, 1, 3}, {2, 1, 1}};
        System.out.println(solution(signals));

        signals = new int[][]{{3, 3, 3}, {5, 4, 2}, {2, 1, 2}};
        System.out.println(solution(signals));

        signals = new int[][]{{1, 1, 4}, {2, 1, 3}, {3, 1, 2}, {4, 1, 1}};
        System.out.println(solution(signals));

        signals = new int[][]{{1, 1, 18}, {18, 1, 1}};
        System.out.println(solution(signals));
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

    private static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public static int solution(int[][] signals) {

        int length = signals.length;
        int[] sums = new int[length];
        int max = 1;
        for (int i = 0; i < length; i++) {
            sums[i] = Arrays.stream(signals[i]).sum();
            max = lcm(max, sums[i]);
        }

        System.out.println("max = " + max);

        for (int i = 1; i <= max; i++) {
            boolean isYellow = true;
            for (int j = 0; j < length; j++) {
                int g = signals[j][0];
                int y = signals[j][0] + signals[j][1];
                int sum = sums[j];

                int remain = i % sum;

                if (g >= remain || remain > y) {
                    isYellow = false; // 하나라도 노란 불에 속하지 않는다면 실패이므로.
                    break;
                }
            }
            if (isYellow) {
                return i;
            }
        }

        return -1;
    }

}
