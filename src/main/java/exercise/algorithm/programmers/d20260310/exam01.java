/**
 * 2025 카카오 하반기 1차 > 노란불 신호등
 */

package exercise.algorithm.programmers.d20260310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exam01 {

    public static void main(String[] args) {
        int[][] signals = {{2, 1, 2}, {5, 1, 1}};
        System.out.println(solution(signals));
        System.out.println(solution2(signals));
        System.out.println(solution3(signals));

        signals = new int[][]{{2, 3, 2}, {3, 1, 3}, {2, 1, 1}};
        System.out.println(solution(signals));
        System.out.println(solution2(signals));
        System.out.println(solution3(signals));

        signals = new int[][]{{3, 3, 3}, {5, 4, 2}, {2, 1, 2}};
        System.out.println(solution(signals));
        System.out.println(solution2(signals));
        System.out.println(solution3(signals));

        signals = new int[][]{{1, 1, 4}, {2, 1, 3}, {3, 1, 2}, {4, 1, 1}};
        System.out.println(solution(signals));
        System.out.println(solution2(signals));
        System.out.println(solution3(signals));

        signals = new int[][]{{1, 1, 18}, {18, 1, 1}};
        System.out.println(solution(signals));
        System.out.println(solution2(signals));
        System.out.println(solution3(signals));
    }

    public static int solution(int[][] signals) {
        int answer = 0;
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < signals.length; i++) {
            list.add(new ArrayList<>());
        }
        int[] sums = new int[signals.length];
        for (int i = 0; i < signals.length; i++) {
            list.get(i).add(signals[i][0]+signals[i][1]);
            sums[i] = Arrays.stream(signals[i]).sum();
        }

        for (int i = 0; i < signals.length; i++) {
            int num = list.get(i).get(0);
            for (int k = 0; k < 50; k++) {
                num += sums[i];
                list.get(i).add(num);
                for (int j = 1; j < signals[i][1]; j++) {
                    list.get(i).add(num - j);
                }
            }
        }

//        for (Integer val : list.get(0)) {
//            for (int i = 1; i < list.size(); i++) {
//                if(list.get(i).)
//            }
//        }

        return answer;
    }

    public static int solution2(int[][] signals) {
        int length = signals.length;
        int[] sums = new int[length];
        int[] visited = new int[2000];
        for (int i = 0; i < length; i++) {
            visited[signals[i][0]+signals[i][1]]++;
            sums[i] = Arrays.stream(signals[i]).sum();
        }

        for (int i = 0; i < length; i++) {
            int num = signals[i][0] + signals[i][1];
            for (int k = 0; k < 50; k++) {
                num += sums[i];
                visited[num]++;
                for (int j = 1; j < signals[i][1]; j++) {
                    visited[num - j]++;
                }
            }
        }

        for (int i = 1; i < visited.length; i++) {
            if(visited[i] == length){
                return i;
            }
        }

        return -1;
    }

    // 최대 공약수 구하기
    private static long gcd(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    // 최소 공배수 구하기
    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    private static int solution3(int[][] signals) {
        long totalLcm = 1;
        int n = signals.length;
        int[] cycles = new int[n];

        // 1. 각 신호등의 주기 계산 및 전체 주기(LCM) 구하기
        for (int i = 0; i < n; i++) {
            cycles[i] = signals[i][0] + signals[i][1] + signals[i][2];
            totalLcm = lcm(totalLcm, cycles[i]);
        }

        // 2. 1초부터 최소공배수까지 시뮬레이션
        for (long t = 1; t <= totalLcm; t++) {
            boolean allYellow = true;

            for (int i = 0; i < n; i++) {
                int g = signals[i][0];
                int y = signals[i][1];
                int cycle = cycles[i];

                long rem = (t - 1) % cycle;

                // 노란불 구간 (G <= 나머지 < G + Y) 인지 체크
                if (!(rem >= g && rem < g + y)) {
                    allYellow = false;
                    break; // 하나라도 노란불이 아니면 다음 시간(t)으로 넘어감
                }
            }

            // 3. 모든 신호등이 노란불이면 현재 시간 반환
            if (allYellow) {
                return (int) t;
            }
        }

        // 4. 모든 경우를 탐색해도 없다면 -1 반환
        return -1;
    }
}
