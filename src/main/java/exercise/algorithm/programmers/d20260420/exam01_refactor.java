/**
 * 이모티콘 할인 행사 - 리팩터링
 */
package exercise.algorithm.programmers.d20260420;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exam01_refactor {
    public static void main(String[] args) {
        int[][] users = new int[][]{{40, 10000}, {25, 10000}};
        int[] emoticons = new int[]{7000, 9000};
        System.out.println(Arrays.toString(solution(users, emoticons)));

        users = new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        emoticons = new int[]{1300, 1500, 1600, 4900};
        System.out.println(Arrays.toString(solution(users, emoticons)));
    }

    static int[] discountRates = {10, 20, 30, 40};
    static int maxSubscribers = 0;
    static int maxSales = 0;
    public static int[] solution(int[][] users, int[] emoticons) {
        maxSubscribers = 0;
        maxSales = 0;

        // 각 이모티콘에 적용될 '할인율'을 담을 배열
        int[] currentRates = new int[emoticons.length];
        dfs(0, currentRates, users, emoticons);

        return new int[]{maxSubscribers, maxSales};
    }

    private static int calcRate(int discountedPrice, int price) {
        return ((price - discountedPrice) * 100 / price);
    }

    // depth 자체가 '현재 이모티콘의 인덱스' 를 의미
    private static void dfs(int depth, int[] currentRates, int[][] users, int[] emoticons) {
        System.out.println("currentRates = " + Arrays.toString(currentRates));
        if(depth == emoticons.length) {
            evaluate(currentRates, users, emoticons);
            return;
        }

        for(int rate : discountRates) {
            currentRates[depth] = rate;
            dfs(depth + 1, currentRates, users, emoticons);
        }
    }

    private static void evaluate(int[] currentRates, int[][] users, int[] emoticons) {
        int subscribers = 0;
        int sales = 0;
        for(int[] user : users) {
            int requiredRate = user[0];
            int limit = user[1];
            int sum = 0;

            for(int i = 0; i < emoticons.length; i++) {
                if(currentRates[i] >= requiredRate) {
                    sum += emoticons[i] * (100 - currentRates[i]) / 100;
                }
            }

            if(sum >= limit) {
                subscribers++;
            } else {
                sales += sum;
            }
        }

        if(subscribers > maxSubscribers) {
            maxSubscribers = subscribers;
            maxSales = sales;
        } else if(subscribers == maxSubscribers && sales > maxSales) {
            maxSales = sales;
        }
    }
}
