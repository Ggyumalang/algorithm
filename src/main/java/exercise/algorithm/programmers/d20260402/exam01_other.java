package exercise.algorithm.programmers.d20260402;

import java.util.Arrays;

public class exam01_other {
    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(m, n, cityMap));
        System.out.println(solution2(m, n, cityMap));

        m = 3;
        n = 6;
        cityMap = new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
        System.out.println(solution(m, n, cityMap));
        System.out.println(solution2(m, n, cityMap));
    }

    static int MOD = 20170805;
    public static int solution(int m, int n, int[][] cityMap) {
        int[][] right = new int[m][n]; // 현재 칸의 왼쪽에서 오는 경우
        int[][] down = new int[m][n]; // 현재 칸의 위쪽에서 오는 경우

        for (int i = 0; i < m; i++) {
            if(cityMap[i][0] == 1) {
                break;
            }
            down[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            if(cityMap[0][i] == 1) {
                break;
            }
            right[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int rightVal = cityMap[i][j-1];
                int downVal = cityMap[i-1][j];
                if (rightVal == 2) {
                    right[i][j] = right[i][j-1];
                } else if( rightVal == 0){
                    right[i][j] = (right[i][j-1] + down[i][j-1]) % MOD;
                } else {
                    right[i][j] = 0;
                }

                if (downVal == 2) {
                    down[i][j] = down[i-1][j];
                } else if( downVal == 0){
                    down[i][j] = (down[i-1][j] + right[i-1][j]) % MOD;
                } else {
                    down[i][j] = 0;
                }
            }
        }

        return (right[m-1][n-1] + down[m-1][n-1]) % MOD;
    }

    public static int solution2(int m, int n, int[][] cityMap) {
        int[][] right = new int[m+1][n+1];
        int[][] down = new int[m+1][n+1];

        right[1][1] = 1;
        down[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (cityMap[i-1][j-1] == 2) {
                    right[i][j] = right[i][j-1];
                    down[i][j] = down[i-1][j];
                } else if (cityMap[i-1][j-1] == 0) {
                    right[i][j] += (right[i][j-1] + down[i-1][j]) % MOD;
                    down[i][j] += (down[i-1][j] + right[i][j-1]) % MOD;
                }
            }
        }

        System.out.println("right = " + Arrays.deepToString(right));
        System.out.println("down = " + Arrays.deepToString(down));

        return (right[m][n-1] + down[m-1][n]) % MOD;
    }
}
