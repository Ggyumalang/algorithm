/**
 * 코딩 테스트 연습 > 2025 카카오 하반기 2차 > 기차 선로
 */

package exercise.algorithm.programmers.d20260306;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class exam01 {

    // 규칙
    // 모든 선로는 이어져 있어야 합니다.
    // 1. : 왼쪽에서 왔을 때 오른쪽 혹은 오른쪽에서 왔을 때 왼쪽으로 한 칸
    // 2. : 아래에서 왔을 때는 위 혹은 위에서 왔을 때 아래로 한 칸
    // 3. : 네 군데 모두 연결될 수 있어야 함
    // 4. : 왼쪽에서 왔을 때 오른쪽 대각선 위 또는 위에서 왔을 때 왼쪽 대각선 아래로 한 칸
    // 5. : 오른쪽에서 왔을 때 왼쪽 대각선 위 또는 위에서 왔을 때 아래쪽 대각선 아래로 한 칸
    // 6. : 아래로부터 왔을 때 왼쪽 대각선 위 또는 오른쪽에서 왔을 때 아래쪽 대각선 아래로 한 칸
    // 7. : 아래로부터 왔을 때 왼쪽 대각선 위 또는 왼쪽에서 왔을 때 아래쪽 대각선 아래로 한 칸
    public static void main(String[] args) {
        int[][] grid = {{1, 0, -1}, {0, 0, 7}, {0, 0, 2}};
        System.out.println(solution(grid));
    }

    public static int solution(int[][] grid) {
        int answer = 0;

        if(grid[0][1] == 2 || grid[0][1] == -1) {
            return answer;
        }

        //for (int i = 0; i < grid.length; i++) {
        //    for (int j = 0; j < grid[i].length; j++) {
        //        if(grid[i][j] == 0){
        //               dfs(grid, i, j);
        //        } else if (grid[i][j] <= 7) {
        //                dfs(grid, i, j);
        //        }
        //    }
        //}
        dfs(grid, 0, 0);
        return answer;
    }

    private static void dfs(int[][] grid, int row, int col) {



    }
    //
    //private static int[] getNext(int val, int row, int col) {
    //    switch (val) {
    //        case 1:
    //            if (row == col) {
    //
    //            }
    //    }
    //}
}
