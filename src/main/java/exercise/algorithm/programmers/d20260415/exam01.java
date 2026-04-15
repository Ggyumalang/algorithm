/**
 * 리코쳇 로봇
 */
package exercise.algorithm.programmers.d20260415;

public class exam01 {
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
                    dfs(i, j, board, 0, visited, 0);
                }
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static void dfs(int x, int y, String[] board, int cnt, boolean[][] visited, int direction) {
        // 이미 넘어섰다면 스탑
        visited[x][y] = true;

        if (cnt >= answer) {
            return;
        }

        // 도착했다면 answer 을 update
        if (board[x].charAt(y) == 'G') {
            answer = cnt;
            return;
        }

        int start;
        int end;
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

            // 왼쪽, 오른쪽으로 쭉 이동해서 만났을때는 위나 아래로 간다.
            if (x != nx) {
                dfs(nx, ny, board, cnt + 1, visited, DIRECT_UPDOWN);
            } else {
                dfs(nx, ny, board, cnt + 1, visited, DIRECT_LEFTRIGHT);
            }
            visited[nx][ny] = false;
        }
    }
}
