/**
 * DP > 정수 삼각형
 */

package exercise.algorithm.programmers.d20260226;

import java.util.Arrays;

public class exam1 {

    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(triangle));
    }

    public static int solution(int[][] triangle) {
        int length = triangle.length;
        int[][] dp = new int[length][length];
        dp[0][0] = triangle[0][0];

        for (int row = 1; row < length; row++) {
            for (int col = 0; col < triangle[row].length; col++) {
                // 맨 좌측
                if(col == 0) {
                    dp[row][col] = dp[row - 1][col] + triangle[row][col];
                    continue;
                }
                // 중간
                dp[row][col] = Math.max(dp[row - 1][col - 1] + triangle[row][col], dp[row - 1][col] + triangle[row][col]);
                // 맨 우측
                if(col == triangle[row].length - 1) {
                    dp[row][col] = dp[row - 1][col - 1] + triangle[row][col];
                }
            }
        }

        System.out.println(Arrays.deepToString(dp));

        return Arrays.stream(dp[length - 1]).max().getAsInt();
    }
}
