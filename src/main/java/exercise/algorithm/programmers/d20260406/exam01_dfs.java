/**
 * 무인도 여행
 *
 */
package exercise.algorithm.programmers.d20260406;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exam01_dfs {
    public static void main(String[] args) {
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        System.out.println(Arrays.toString(solution(maps)));
    }

    static int sum;
    public static int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if(!visited[i][j] && maps[i].charAt(j) != 'X') {
                    System.out.println("visited = " + Arrays.deepToString(visited));
                    visited[i][j] = true;
                    sum = maps[i].charAt(j) - '0';
                    dfs(i,j,maps, visited);
                    answer.add(sum);
                }
            }
        }
        return answer.isEmpty() ? new int[]{-1} : answer.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    static int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    private static void dfs(int x, int y, String[] maps, boolean[][] visited) {
        System.out.println("x = " + x);
        System.out.println("y = " + y);

        for(int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if(nx < 0 || ny < 0 || nx > maps.length - 1 || ny > maps[0].length() - 1 || visited[nx][ny]) {
                continue;
            }

            visited[nx][ny] = true;
            if(maps[nx].charAt(ny) == 'X') {
                continue;
            }

            sum += maps[nx].charAt(ny) - '0';
            dfs(nx,ny,maps, visited);
            sum = Math.max(sum, 0);
        }
    }
}
