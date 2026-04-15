/**
 * 리코쳇 로봇
 */
package exercise.algorithm.programmers.d20260415;

import java.util.LinkedList;
import java.util.Queue;

public class exam01_bfs {
    public static void main(String[] args) {
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        System.out.println(solution(board));
        board = new String[]{".D.R", "....", ".G..", "...D"};
        System.out.println(solution(board));
    }

    static int answer;
    static final int DIRECT_START = 0;
    static final int DIRECT_UPDOWN = 1;
    static final int DIRECT_LEFTRIGHT = 2;
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int solution(String[] board) {
        answer = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[board.length][board[0].length()];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    bfs(i, j, board, 0, visited, 0);
                }
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static void bfs(int x, int y, String[] board, int cnt, boolean[][] visited, int direction) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, direction, cnt});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            x = cur[0];
            y = cur[1];
            direction = cur[2];
            cnt = cur[3];
            int start, end;

            if(board[x].charAt(y) == 'G') {
                answer = cnt;
                return;
            }

            switch (direction) {
                case DIRECT_START:
                    start = 0;
                    end = 4;
                    break;
                case DIRECT_UPDOWN:
                    start = 0;
                    end = 2;
                    break;
                case DIRECT_LEFTRIGHT:
                    start = 2;
                    end = 4;
                    break;
                default:
                    return;
            }

            for (int i = start; i < end; i++) {
                int dirX = dirs[i][0];
                int dirY = dirs[i][1];
                int nx = x;
                int ny = y;

                // 범위를 벗어나거나 D를 만날 때까지 그 방향으로 쭉 간다.
                while (true) {
                    nx = nx + dirX;
                    ny = ny + dirY;

                    if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length() || board[nx].charAt(ny) == 'D') {
                        nx = nx - dirX;
                        ny = ny - dirY;
                        break;
                    }
                }

                if (visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                if (x != nx) {
                    queue.add(new int[]{nx, ny, DIRECT_UPDOWN, cnt + 1});
                } else {
                    queue.add(new int[]{nx, ny, DIRECT_LEFTRIGHT, cnt + 1});
                }

            }
        }
    }
}
