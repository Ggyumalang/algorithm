/*
** 최소로 도착하는 법 찾기?
 */
package exercise.algorithm.programmers.d20260410;


public class exam01_dp {
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2,2}};
        System.out.println(solution(m, n, puddles));
    }

    static int MOD = 1000000007;
    public static int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;
        for(int[] puddle : puddles) {
            dp[puddle[0]][puddle[1]] = -1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n ; j++) {
                if(i == 1 && j == 1) {
                    continue;
                }
                if(dp[i][j] != -1) {
                    if(dp[i-1][j] == -1 && dp[i][j-1] != -1 ) {
                        dp[i][j] = dp[i][j-1];
                    } else if(dp[i][j-1] == -1 && dp[i-1][j] != -1 ) {
                        dp[i][j] = dp[i-1][j];
                    } else if(dp[i][j-1] == -1 && dp[i-1][j] == -1 ) {
                        continue;
                    } else {
                        dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
                    }
                }
            }
        }

        return dp[m][n];
    }
}
