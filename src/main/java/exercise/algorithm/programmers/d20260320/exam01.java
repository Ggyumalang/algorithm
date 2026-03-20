/**
 * PCCP 기출문제 > 퍼즐 게임 챌린지
 */
package exercise.algorithm.programmers.d20260320;

import java.util.Arrays;

public class exam01 {
    public static void main(String[] args) {
        int[] diffs = {1,5,3};
        int[] times = {2,4,7};
        long limit = 30;
        System.out.println(solution(diffs, times, limit));

        diffs = new int[]{1,4,4,2};
        times = new int[]{6,3,8,2};
        limit = 59;
        System.out.println(solution(diffs, times, limit));

        diffs = new int[]{1, 328, 467, 209, 54};
        times = new int[]{2, 7, 1, 4, 3};
        limit = 1723;
        System.out.println(solution(diffs, times, limit));

        diffs = new int[]{1, 99999, 100000, 99995};
        times = new int[]{9999, 9001, 9999, 9001};
        limit = Long.parseLong("3456789012");
        System.out.println(solution(diffs, times, limit));
    }

    public static int solution(int[] diffs, int[] times, long limit) {
        if(diffs.length != times.length || diffs.length == 0) {
            return 0;
        }

        int min = 1;
        int max = Arrays.stream(diffs).max().getAsInt();

        int answer = max;

        while (min <= max) {
            int mid = min + (max - min) / 2; // (max + min) / 2 와 같지만.. 오버플로우 방지를 위해..
            if(calc(diffs, times, mid, limit)) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return answer;
    }

    private static boolean calc(int[] diffs, int[] times, int level, long limit) {
        long totalTime = 0;
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];
            int time = times[i];

            if(level < diff) {
                //level 이 더 낮은 경우
                totalTime = totalTime + ((long) (times[i - 1] + time) * (diff - level)) + time;
            } else {
                //level 이 높거나 같은 경우 그냥 time 바로 더함
                totalTime += time;
            }
            if(totalTime > limit) {
                return false;
            }
        }
        return totalTime <= limit;
    }
}
