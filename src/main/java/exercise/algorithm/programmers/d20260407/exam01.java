/**
 * 거스름돈
 */
package exercise.algorithm.programmers.d20260407;

import java.util.Arrays;

public class exam01 {
    public static void main(String[] args) {
        int n = 5;
        int[] money = {1, 2, 5};
        System.out.println(solution(n, money));
    }

    static int MOD = 1000000007;
    public static int solution(int n, int[] money) {
        Arrays.sort(money);
        // dp[i] 를 i 원을 거슬러 줄 수 있는 경우의 수
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int k : money) {
            for (int j = 1; j <= n; j++) {
                if (j >= k) {
                    dp[j] = (dp[j] + dp[j - k]) % MOD;
                }
            }
            System.out.println("dp = " + Arrays.toString(dp));
        }


        return dp[n];
    }
}
