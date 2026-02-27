package exercise.algorithm.programmers.d20260227;

import java.util.*;

public class exam1 {

    public static void main(String[] args) {
        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        System.out.println(solution(sizes));
    }

    public static int solution(int[][] sizes) {
        int length = sizes.length;
        int[] wArr = new int[length];
        int[] hArr = new int[length];
        Queue<Integer> answerQueue = new PriorityQueue<>(Comparator.naturalOrder());

        for (int i = 0; i < length; i++) {
            wArr[i] = sizes[i][0];
            hArr[i] = sizes[i][1];
        }

        Arrays.sort(wArr);
        Arrays.sort(hArr);

        for (int i = length-1; i >= 0; i--) {
            for (int j = length-1; j >= 0; j--) {
                if(validate(wArr[i], hArr[j], sizes)){
                    answerQueue.offer(wArr[i] * hArr[j]);
                } else {
                    break;
                }
            }
        }

        System.out.println("answerQueue = " + answerQueue);
        return answerQueue.poll().intValue();
    }

    private static boolean validate(int w, int h, int[][] sizes) {
        for (int[] size : sizes) {
            if(size[0] <= w && size[1] <= h) {
                continue;
            } else if (size[1] <= w && size[0] <= h) {
                continue;
            }
            return false;
        }

        return true;
    }
}
