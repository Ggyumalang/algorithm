/**
 * leetCode > Number of Islands
 */
package exercise.algorithm.programmers.d20260330;

import java.util.LinkedList;
import java.util.Queue;

public class exam01 {

    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','0'}, {'0','0','0','0','0'}};
        System.out.println(numIslands(grid));

        grid = new char[][] {{'1','1','0','0','0'}, {'1','1','0','0','0'}, {'0','0','1','0','0'}, {'0','0','0','1','1'}};
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int answer = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(!visited[i][j] && grid[i][j] == '1'){
                    bfs(visited,i,j, grid);
                    answer++;
                }
            }
        }

        return answer;
    }

    static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    private static void bfs(boolean[][] visited, int x, int y, char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];

                if(nx < 0 || ny < 0 || nx > visited.length - 1 || ny > visited[0].length - 1 || visited[nx][ny] || grid[nx][ny] == '0'){
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new int[]{nx,ny});
            }
        }
    }
}
