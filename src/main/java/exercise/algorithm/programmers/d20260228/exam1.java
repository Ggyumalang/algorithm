/**
 * 완전탐색 > 모의고사
 */

package exercise.algorithm.programmers.d20260228;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exam1 {

    public static void main(String[] args) {
        int[] answers = new int[]{1,2,3,4,5};
        System.out.println("solution(answers) = " + Arrays.toString(solution(answers)));

        answers = new int[]{1,3,2,4,2};
        System.out.println("solution(answers)2 = " + Arrays.toString(solution(answers)));

    }

    public static int[] solution(int[] answers) {
        List<Integer> list = new ArrayList<>();
        int[] su1 = {1, 2, 3, 4, 5};
        int[] su2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] su3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int su1Cnt = 0;
        int su2Cnt = 0;
        int su3Cnt = 0;

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == su1[i%su1.length]) {
                su1Cnt++;
            }

            if(answers[i] == su2[i%su2.length]) {
                su2Cnt++;
            }

            if(answers[i] == su3[i%su3.length]) {
                su3Cnt++;
            }
        }

        int maxVal = Math.max(su1Cnt, Math.max(su2Cnt, su3Cnt));
        if(maxVal == 0) {
            return new int[0];
        }

        if(maxVal == su1Cnt) {
            list.add(1);
        }

        if(maxVal == su2Cnt) {
            list.add(2);
        }

        if(maxVal == su3Cnt) {
            list.add(3);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
