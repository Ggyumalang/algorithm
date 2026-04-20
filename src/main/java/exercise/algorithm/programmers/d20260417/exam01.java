/**
 * 택배 배달과 수거하기
 */

package exercise.algorithm.programmers.d20260417;

public class exam01 {
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
        long answer = 0;
        int deliverAcc = 0;
        int pickupAcc = 0;

        for (int i = n-1; i >= 0; i--) {
            deliverAcc += deliveries[i];
            pickupAcc += pickups[i];

            while (deliverAcc > 0 || pickupAcc > 0) {
                deliverAcc -= cap;
                pickupAcc -= cap;
                answer += (i + 1) * 2L;
            }
        }

        return answer;
    }
}
