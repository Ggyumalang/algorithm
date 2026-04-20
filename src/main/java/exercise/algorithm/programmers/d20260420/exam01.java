/**
 * 이모티콘 할인 행사
 */
package exercise.algorithm.programmers.d20260420;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exam01 {
    public static void main(String[] args) {
        int[][] users = new int[][]{{40, 10000}, {25, 10000}};
        int[] emoticons = new int[]{7000, 9000};
        System.out.println(Arrays.toString(solution(users, emoticons)));

        users = new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        emoticons = new int[]{1300, 1500, 1600, 4900};
        System.out.println(Arrays.toString(solution(users, emoticons)));
    }

    static int[] discountRate = {10,20,30,40};
    static List<List<Integer>> emoticonList;
    static int len;
    public static int[] solution(int[][] users, int[] emoticons) {
        emoticonList = new ArrayList<>();
        len = emoticons.length;

        boolean[] visited = new boolean[emoticons.length];
        dfs(0, 0, visited, new ArrayList<>(), emoticons);


        int maxVal = 0;
        int maxAmount = 0;
        for (List<Integer> emoticonPrices : emoticonList) {
            System.out.println("emoticonPrices = " + emoticonPrices);
            int totVal = 0;
            int totAmount = 0;
            int[] rates = new int[emoticonPrices.size()];

            for (int i = 0; i < emoticonPrices.size(); i++) {
                rates[i] = calcRate(emoticonPrices.get(i), emoticons[i]);
            }

            for (int [] user : users) {
                int rate = user[0];
                int limit = user[1];
                int amount = 0;

                for (int i = 0; i < emoticonPrices.size(); i++) {
                    if(rates[i] >= rate) {
                        amount += emoticonPrices.get(i);
                    }
                }

                if(amount >= limit) {
                    totVal ++;
                    amount = 0;
                }
                totAmount += amount;
            }
            if(totVal > maxVal) {
                maxVal = totVal;
                maxAmount = totAmount;
            } else if(totVal == maxVal && totAmount > maxAmount) {
                maxAmount = totAmount;
            }
        }

        return new int[]{maxVal, maxAmount};
    }

    private static int calcRate(int discountedPrice, int price) {
        return ((price - discountedPrice) * 100 / price);
    }

    private static void dfs(int eIdx, int depth, boolean[] visited, List<Integer> emoticonPrices, int[] emoticons) {
        if(depth == len) {
            emoticonList.add(new ArrayList<>(emoticonPrices));
            return;
        }

        for (int i = eIdx; i < emoticons.length; i++) {
            int emoticonPrice = emoticons[i];
            if(!visited[i]) {
                visited[i] = true;
                for (int k : discountRate) {
                    int val = emoticonPrice * (100 - k) / 100;
                    emoticonPrices.add(val);
                    dfs(i, depth + 1, visited, emoticonPrices, emoticons);
                    emoticonPrices.remove(emoticonPrices.size() - 1);
                }
                visited[i] = false;
            }
        }
    }
}
