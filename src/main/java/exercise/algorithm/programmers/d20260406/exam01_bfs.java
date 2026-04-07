/**
 * 무인도 여행
 *
 */
package exercise.algorithm.programmers.d20260406;

import java.util.*;

public class exam01_bfs {
    public static void main(String[] args) {
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        System.out.println(Arrays.toString(solution(maps)));

        maps = new String[]{"XXX","XXX","XXX", "XXX"};
        System.out.println(Arrays.toString(solution(maps)));
    }

    public static int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if(!visited[i][j] && maps[i].charAt(j) != 'X') {
                    visited[i][j] = true;
                    answer.add(bfs(i,j,maps, visited));
                }
            }
        }
        return answer.isEmpty() ? new int[]{-1} : answer.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    static int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    private static int bfs(int x, int y, String[] maps, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        int currentSum = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            x = cur[0];
            y = cur[1];

            currentSum += maps[x].charAt(y) - '0';

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

                queue.add(new int[]{nx,ny});
            }
        }

        return currentSum;
    }
}
