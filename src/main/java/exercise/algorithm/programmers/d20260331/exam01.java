/**
 * 카펫
 */
package exercise.algorithm.programmers.d20260331;

import java.util.Arrays;

public class exam01 {
    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;
        System.out.println("결과 = " + Arrays.toString(solution(brown, yellow)));

        brown = 8;
        yellow = 1;
        System.out.println("결과 = " + Arrays.toString(solution(brown, yellow)));

        brown = 24;
        yellow = 24;
        System.out.println("결과 = " + Arrays.toString(solution(brown, yellow)));
    }

    // 카펫의 가로길이는 세로 길이와 같거나, 세로 길이보다 길다.
    public static int[] solution(int brown, int yellow) {
        // 최솟값..
        int row;
        int col;

        //yellow 의 약수들을 구한다
        for (int i = 1; i <= Math.sqrt(yellow); i++) {
            if(yellow % i == 0) {
                row = yellow / i;
                col = i;

                if(calc(row, col) == brown) {
                    return new int[]{row + 2 ,col + 2};
                }
            }
        }

        return new int[0];
    }

    private static int calc(int row, int col) {
        return (row+2) * 2 + (col * 2);
    }
}
