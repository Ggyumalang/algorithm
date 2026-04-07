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
        int answer = 0;
        Arrays.sort(money);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);

        for (int i = 0; i < money.length; i++) {
            int num = money[i];
            for (int j = n; j >= num; j--) {
                dp[j] = (dp[j] + dp[j - num]) % MOD;
            }
            System.out.println("dp = " + Arrays.toString(dp));
        }


        return answer;
    }
}
