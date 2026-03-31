/**
 * 상품 검색 랭킹 시스템 (feat.gemini)
 */

package exercise.algorithm.programmers.d20260330;

import lombok.ToString;

import java.util.Arrays;
import java.util.PriorityQueue;

public class exam02 {
    public static void main(String[] args) {
        int[][] products = {{80, 250, 15000}, {82, 50, 20000}, {80, 290, 12000}, {70, 1500, 15000}, {80, 200, 15000}};
        int k = 3;

        System.out.println(Arrays.toString(solution(products, k)));
    }

    @ToString
    static class Product implements Comparable<Product> {
        int accScore;
        int accumReviewCount;
        int price;
        int idx;
        int totalScore;

        public Product(int accScore, int accumReviewCount, int price, int idx) {
            this.accScore = accScore;
            this.accumReviewCount = accumReviewCount;
            this.price = price;
            this.idx = idx;
            this.totalScore = accScore + (accumReviewCount / 100);
        }


        @Override
        public int compareTo(Product o) {
            if(this.totalScore == o.totalScore) {
                if(this.price == o.price) {
                    return this.idx - o.idx;
                }
                return this.price - o.price;
            }
            return o.totalScore - this.totalScore;
        }
    }

    public static int[] solution(int[][] products, int k) {
        PriorityQueue<Product> queue = new PriorityQueue<>();

        for (int i = 0; i < products.length; i++) {
            queue.offer(new Product(products[i][0], products[i][1], products[i][2], i));
        }

        int[] answer = new int[k];
        int idx = 0;
        while (k > 0 && !queue.isEmpty()) {
            k--;
            Product p = queue.poll();
            answer[idx++] = p.idx;
        }

        return answer;
    }
}
