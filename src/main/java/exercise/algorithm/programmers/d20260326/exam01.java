/**
 * 요격 시스템
 * 1. 풀이 => 가장 많이 겹치는 구간들을 꽂아본다.
 */
package exercise.algorithm.programmers.d20260326;

import java.util.Arrays;
import java.util.Comparator;

public class exam01 {
    public static void main(String[] args) {
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        System.out.println(solution(targets));
    }

    public static int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));

        int lastIntercept = targets[0][1];
        int answer = 1;
        for (int i = 1; i < targets.length; i++) {
            int start = targets[i][0];

            if(start >= lastIntercept) {
                answer++;
                lastIntercept = targets[i][1];
            }
        }
        return answer;
    }
}
