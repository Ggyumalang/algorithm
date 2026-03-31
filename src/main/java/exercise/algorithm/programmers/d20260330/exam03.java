/**
 * 장바구니 방치 유저 타겟팅 (문자열 파싱 & 해시) (feat.gemini)
 */
package exercise.algorithm.programmers.d20260330;

import java.util.*;

public class exam03 {
    public static void main(String[] args) {
        String[] logs = {"alex CART book", "jane CART clothes", "alex PURCHASE book", "jane VIEW book", "tom CART electronics", "tom CART book", "tom PURCHASE book", "alex CART electronics"};
        System.out.println(Arrays.toString(solution(logs)));
        System.out.println(Arrays.toString(solution2(logs)));
    }

    public static String[] solution(String[] logs) {
        Map<String, String> cartMap = new HashMap<>();

        for (String log : logs) {
            String[] strs = log.split(" ");
            System.out.println("strs = " + strs[1]);
            String key = strs[0] + " " + strs[2];
            switch (strs[1]) {
                case "CART":
                    cartMap.put(key, strs[1]);
                    break;
                case "PURCHASE":
                    cartMap.remove(key);
                    break;
                default:
                    break;
            }
            System.out.println("cartMap = " + cartMap);
        }

        return cartMap.keySet().stream().map(x -> x.split(" ")[0]).sorted().toArray(String[]::new);
    }

    public static String[] solution2(String[] logs) {
        Set<String> cartSet = new HashSet<>();
        Set<String> purchaseSet = new HashSet<>();

        for (String log : logs) {
            String[] strs = log.split(" ");
            String key = strs[0] + " " + strs[2];
            switch (strs[1]) {
                case "CART":
                    cartSet.add(key);
                    break;
                case "PURCHASE":
                    purchaseSet.add(key);
                    break;
                default:
                    break;
            }
        }

        cartSet.removeAll(purchaseSet);

        return cartSet.stream()
                .map(k -> k.split(" ")[0])
                .distinct()
                .sorted()
                .toArray(String[]::new);
    }
}
