/**
 * 뉴스 클러스터링
 */
package exercise.algorithm.programmers.d20260423;

import java.util.HashMap;
import java.util.Map;

public class exam01 {
    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";
        System.out.println(solution(str1, str2));

        str1 = "handshake";
        str2 = "shake hands";
        System.out.println(solution(str1, str2));

        str1 = "aa1+aa2";
        str2 = "AAAA12";
        System.out.println(solution(str1, str2));

        str1 = "E=M*C^2";
        str2 = "e=m*c^2";
        System.out.println(solution(str1, str2));
    }

    static final int MOD = 65536;

    public static int solution(String str1, String str2) {
        Map<String, Integer> str1Map = new HashMap<>();
        Map<String, Integer> str2Map = new HashMap<>();
        addList(str1, str1Map);
        addList(str2, str2Map);

        // 교집합
        int anb = 0;
        // 합집합
        int aub = 0;

        for (String key : str1Map.keySet()) {
            if (str2Map.containsKey(key)) {
                anb += Math.min(str1Map.get(key), str2Map.get(key));
            }
        }

        int total1 = str1Map.values().stream().reduce(0, Integer::sum);
        int total2 = str2Map.values().stream().reduce(0, Integer::sum);
        aub = total1 + total2 - anb;

        if (aub == 0) {
            return MOD;
        }
        return (int) (((double) anb / (double) aub) * MOD);
    }

    private static void addList(String str, Map<String, Integer> map) {
        str = str.toUpperCase();
        for (int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                String key = "" + c1 + c2;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
    }
}
