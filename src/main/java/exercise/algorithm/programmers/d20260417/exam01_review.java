/**
 * 택배 배달과 수거하기_복습
 */

package exercise.algorithm.programmers.d20260417;

public class exam01_review {
    public static void main(String[] args) {
        int cap = 4;
        int n = 5;
        int[] deliveries = {1,0,3,1,2};
        int[] pickups = {0,3,0,4,0};
        System.out.println(solution(cap, n, deliveries, pickups));

        cap = 2;
        n = 7;
        deliveries = new int[]{1, 0, 2, 0, 1, 0, 2};
        pickups = new int[]{0, 2, 0, 1, 0, 2, 0};
        System.out.println(solution(cap, n, deliveries, pickups));
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int answer = 0;
        int accDeliver = 0;
        int accPick = 0;


        // 먼 집부터 하나씩 차근차근 진행
        for (int i = n - 1 ; i >= 0 ; i--) {
            accDeliver += deliveries[i];
            accPick += pickups[i];

            while(accDeliver > 0 || accPick > 0) {
                accDeliver -= cap;
                accPick -= cap;
                // 양수라면 반복
                answer += (i + 1) * 2;
            }
        }

        return answer;
    }
}
