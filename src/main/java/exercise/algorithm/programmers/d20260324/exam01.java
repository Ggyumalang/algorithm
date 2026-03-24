/**
 * 충돌위험 찾기
 */

package exercise.algorithm.programmers.d20260324;

import java.util.*;

public class exam01 {
    public static void main(String[] args) {
        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {2, 4}};
        System.out.println(solution(points, routes));

        points = new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        routes = new int[][]{{4, 2}, {1, 3}, {4, 2}, {4,3}};

        System.out.println(solution(points, routes));

        points = new int[][]{{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5,2}};
        routes = new int[][]{{2, 3, 4, 5}, {1, 3, 4, 5}};

        System.out.println(solution(points, routes));
    }

    public static int solution(int[][] points, int[][] routes) {
        List<List<int[]>> allPositions = new ArrayList<>();
        int maxTime = 1;

        for (int[] route : routes) {
            List<int[]> position = new ArrayList<>();

            // 처음 출발지 기록 (t = 0)
            int startIdx = route[0] - 1;
            int r = points[startIdx][0];
            int c = points[startIdx][1];
            position.add(new int[]{r, c});

            for (int j = 1; j < route.length; j++) {
                int targetIdx = route[j] - 1;
                int targetR = points[targetIdx][0];
                int targetC = points[targetIdx][1];

                //r 좌표 먼저 이동
                while (r != targetR) {
                    if (r < targetR) r++;
                    else r--;
                    position.add(new int[]{r, c}); // 지나갈 때마다 추가
                }

                //c 좌표 이동
                while (c != targetC) {
                    if (c < targetC) c++;
                    else c--;
                    position.add(new int[]{r, c}); // 지나갈 때마다 추가
                }
            }
            allPositions.add(position);
            maxTime = Math.max(maxTime, position.size());
        }

        int answer = 0;

        // 2. 시간을 0초부터 최대 시간까지 흐르게 하여 충돌 검사
        for (int t = 0; t < maxTime; t++) {
            // 현재 시간에 각 좌표별 로봇이 몇 대 있는 지 카운트 하기 위함.
            Map<String,Integer> timeMap = new HashMap<>();

            for (List<int[]> position : allPositions) {
                // 해당 로봇이 시간 t에 아직 목적지에 도착 못했다면 (경로 이동 중이므로..)
                if( t < position.size()) {
                    int[] pos = position.get(t);
                    String key = pos[0] + "," + pos[1]; // 좌표를 문자열 키로 생성
                    timeMap.put(key, timeMap.getOrDefault(key, 0) + 1);
                }
            }

            for (int cnt : timeMap.values()) {
                if(cnt > 1) {
                    answer ++;
                }
            }
        }

        return answer;
    }
}
