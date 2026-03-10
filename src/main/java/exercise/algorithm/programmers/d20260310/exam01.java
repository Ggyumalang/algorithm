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

        signals = new int[][]{{2, 3, 2}, {3, 1, 3}, {2, 1, 1}};
        System.out.println(solution(signals));

        signals = new int[][]{{3, 3, 3}, {5, 4, 2}, {2, 1, 2}};
        System.out.println(solution(signals));

        signals = new int[][]{{1, 1, 4}, {2, 1, 3}, {3, 1, 2}, {4, 1, 1}};
        System.out.println(solution(signals));
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

        System.out.println("sums = " + Arrays.toString(sums));

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
}
