/**
 * 미로 탈출
 *
 */
package exercise.algorithm.programmers.d20260401;

import java.util.LinkedList;
import java.util.Queue;

public class exam01 {
    public static void main(String[] args) {
        String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        System.out.println(solution(maps));

        maps = new String[]{"SOOOL", "XXOXO", "XXOXE"};
        System.out.println(solution(maps));
    }

    static int answer;
    static boolean isExited;

    public static int solution(String[] maps) {
        answer = 0;
        isExited = false;
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                // Start 지점 찾기!
                if (maps[i].charAt(j) == 'S') {
                    bfs(i, j, maps, false);
                }
            }
        }
        if(!isExited) {
            return -1;
        }
        return answer == 0 ? -1 : answer;
    }

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static void bfs(int i, int j, String[] maps, boolean isPulled) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j, 0});
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        visited[i][j] = true;


        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int idx = cur[2];
            char c = maps[x].charAt(y);

            if (c == 'L' && !isPulled) {
                answer += idx;
                bfs(x, y, maps, true);
                break;
            }

            if (c == 'E' && isPulled) {
                answer += idx;
                isExited = true;
                return;
            }

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || ny < 0 || nx > maps.length - 1 || ny > maps[0].length() - 1 || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                if (maps[nx].charAt(ny) != 'X') {
                    queue.add(new int[]{nx, ny, idx + 1});
                }
            }
        }

    }
}
