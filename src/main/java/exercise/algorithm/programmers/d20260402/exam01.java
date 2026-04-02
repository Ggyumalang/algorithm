/**
 * 코딩테스트 연습
 * 2017 카카오코드 예선
 * 보행자 천국
 */
package exercise.algorithm.programmers.d20260402;

import java.util.Arrays;

public class exam01 {
    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(m, n, cityMap));

        m = 3;
        n = 6;
        cityMap = new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
        System.out.println(solution(m, n, cityMap));
    }

    static int MOD = 20170805;
    static long result;
    public static int solution(int m, int n, int[][] cityMap) {
        result = 0;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        dfs(0,0, m,n, cityMap, visited, 0);
        return Math.toIntExact(result % MOD);
    }

    static int[][] dirs = {{0,1},{1,0}};
    // 어디서 받아왔는 지 방향을 기록한다.
    private static void dfs(int x, int y, int m, int n, int[][] cityMap, boolean[][] visited, int direction) {
        if(x == m-1 && y == n-1) {
            System.out.println("visited = " + Arrays.deepToString(visited));
            result++;
            return;
        }

        //위에서 오던 차량이므로 아래로만 진입
        if(direction == 1) {
            int nx = x + 1;
            int ny = y;
            if(nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]) {
                return;
            }
            visited[nx][ny] = true;
            if(cityMap[nx][ny] == 1) {
                return;
            }

            if(cityMap[nx][ny] == 2) {
                dfs(nx,ny, m,n, cityMap, visited, 1);
                visited[nx][ny] = false;
                return;
            }
            dfs(nx,ny, m,n, cityMap, visited, 0);
            visited[nx][ny] = false;
        } else if(direction == -1) {
            int nx = x;
            int ny = y + 1;
            if(nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]) {
                return;
            }
            visited[nx][ny] = true;
            if(cityMap[nx][ny] == 1) {
                return;
            }

            if(cityMap[nx][ny] == 2) {
                dfs(nx,ny, m,n, cityMap, visited, -1);
                visited[nx][ny] = false;
                return;
            }
            dfs(nx,ny, m,n, cityMap, visited, 0);
            visited[nx][ny] = false;
        } else {
            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if(nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;

                if(cityMap[nx][ny] == 1) {
                    continue;
                }

                if(cityMap[nx][ny] == 2) {
                    // 위쪽에서 오던 차량
                    if(x == nx - 1 && y == ny) {
                        dfs(nx,ny, m,n, cityMap, visited, 1);
                        visited[nx][ny] = false;
                        continue;
                        // 왼쪽에서 오던 차량
                    } else if(x == nx && y == ny - 1) {
                        dfs(nx,ny, m,n, cityMap, visited, -1);
                        visited[nx][ny] = false;
                        continue;
                    }
                    continue;
                }
                dfs(nx,ny, m,n, cityMap, visited, 0);
                visited[nx][ny] = false;
            }
        }
    }
}
