/**
 * 단속카메라
 */
package exercise.algorithm.programmers.d20260409;

import java.util.*;

public class exam01 {
    public static void main(String[] args) {
        int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        System.out.println(solution(routes));

        routes = new int[][]{{-20,-15}, {-18,-5}, {-18,-13}, {-5,-3}, {-2,-1}};
        System.out.println(solution(routes));

        routes = new int[][]{{-20,-15}, {-18,-5}, {-18,-13}, {-5,-3}, {-2,-1}};
        System.out.println(solution(routes));

        routes = new int[][]{{-20,-15}, {-20,-2}, {-16,-11}, {-10,-9}, {-2,-1}};
        System.out.println(solution(routes));
    }

    public static int solution(int[][] routes) {
        int answer = 1;
        // 맨 앞부터 진행
        Arrays.sort(routes, (o1, o2) -> {
            if(o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });

        int lastCamPos = routes[0][1];
        for (int i = 1; i < routes.length; i++) {
            if(lastCamPos < routes[i][0]) {
                answer++;
                lastCamPos = routes[i][1];
            }
        }

        return answer;
    }
}
