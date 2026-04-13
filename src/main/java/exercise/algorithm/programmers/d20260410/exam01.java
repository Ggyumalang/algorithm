package exercise.algorithm.programmers.d20260410;

import java.util.LinkedList;
import java.util.Queue;

public class exam01 {
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2,2}};
        System.out.println(solution(m, n, puddles));
    }

    static int answer;
    static int MOD = 1000000007;
    static int[][] dirs = {{0,1}, {1,0}};
    public static int solution(int m, int n, int[][] puddles) {
        answer = 0;
        bfs(m,n, puddles);
        return answer;
    }

    private static void bfs(int m, int n, int[][] puddles) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m+1][n+1];
        queue.add(new int[]{1,1});
        visited[1][1] = true;
        for(int[] puddle : puddles) {
            visited[puddle[0]][puddle[1]] = true;
        }

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            System.out.println("x = " + x);
            System.out.println("y = " + y);

            if(x == m && y == n) {
                answer = (answer + 1) % MOD;
                continue;
            }

            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if(nx < 0 || ny < 0 || nx > m || ny > n || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new int[]{nx,ny});
            }
        }
    }
}
