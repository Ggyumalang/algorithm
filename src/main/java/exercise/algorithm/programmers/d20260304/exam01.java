/**
 * 코딩 테스트 연습 > 2025 프로그래머스 코드챌린지 2차 예선 > 완전 범죄
 */

package exercise.algorithm.programmers.d20260304;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exam01 {

    public static void main(String[] args) {
        int[][] info = {{1,2}, {2,3}, {2,1}};
        int n = 4;
        int m = 4;
        System.out.println(solution(info, n, m));

        info = new int[][]{{1,2}, {2,3}, {2,1}};
        n = 1;
        m = 7;
        System.out.println(solution(info, n, m));

        info = new int[][]{{3,3}, {3,3}};
        n = 7;
        m = 1;
        System.out.println(solution(info, n, m));

        info = new int[][]{{3,3}, {3,3}};
        n = 6;
        m = 1;
        System.out.println(solution(info, n, m));
    }

    public static int solution(int[][] info, int n, int m) {
        int answer = 0;
        // 1번째 수(A 가 흔적이 많이 남는) 기준 내림차순하고, a[1],b[1] 오름차순
        Arrays.sort(info, (a, b) -> {
            if(b[0] == a[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        List<Integer> removedIdx = new ArrayList<>();
        int M = m;

        for (int i = 0; i < info.length; i++) {
            if( M == 0 ) {
                break;
            }
            if(info[i][1] < M) {
                //answer+=info[i][0];
                removedIdx.add(i);
                M -= info[i][1];
            }
        }
        System.out.println("removedIdx = " + removedIdx);
        for (int i = 0; i < info.length; i++) {
            if(!removedIdx.contains(i)) {
                answer += info[i][0];
            }
        }

        return answer >= n ? -1 : answer;
    }
}
