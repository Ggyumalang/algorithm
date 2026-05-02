/**
 * Best Time to Buy and Sell Stock
 */

package exercise.algorithm.programmers.d20260502;

public class exam02 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));

        prices = new int[] {7, 6, 4, 3, 1};
        System.out.println(maxProfit(prices));

        prices = new int[] {1, 2};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {

        int buyPrice = prices[0];
        int profit = 0;
        for (int price: prices) {
            if (buyPrice > price) {
                buyPrice = price;
            } else {
                profit = Math.max(profit, price - buyPrice);
            }
        }

        return profit;
    }
}
