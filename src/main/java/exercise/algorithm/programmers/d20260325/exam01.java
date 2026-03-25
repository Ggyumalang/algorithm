/**
 * 석유 시추
 */
package exercise.algorithm.programmers.d20260325;

import java.util.*;

public class exam01 {
    public static void main(String[] args) {
        int [][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        System.out.println("solution(land) = " + solution(land));
        land = new int [][]{{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};
        System.out.println("solution(land) = " + solution(land));

    }

    static int [][] dirs = {{0,1}, {0,-1},{1,0},{-1,0}};
    static Map<Integer, Integer> areaValMap;
    static boolean[][] visited;
    public static int solution(int[][] land) {
        areaValMap = new HashMap<>();
        visited = new boolean[land.length][land[0].length];
        //구역별로 확인하기
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                // 석유 땅인 경우 확인하고 이미 확인된 구역이면 넘어감
                if(land[i][j] == 1 && !visited[i][j]){
                    bfs(land, i, j);
                }
            }
        }
        System.out.println("areaValMap = " + areaValMap);
        return areaValMap.values().stream().max(Integer::compareTo).get();
    }

    private static void bfs(int[][] land, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        Set<Integer> set = new HashSet<>();
        visited[i][j] = true;
        set.add(j);
        int size = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int[] dir: dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if(nx < 0 || ny < 0 || nx > land.length - 1 || ny > land[i].length - 1 || visited[nx][ny]){
                    continue;
                }

                visited[nx][ny] = true;
                if(land[nx][ny] == 1){
                    queue.offer(new int[]{nx, ny});
                    set.add(ny);
                    size++;
                }
            }
        }

        for(int ny : set) {
            areaValMap.put(ny, areaValMap.getOrDefault(ny, 0) + size);
        }
    }
}
