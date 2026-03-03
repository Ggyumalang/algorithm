/**
 * 그리디 > 조이스틱
 */

package exercise.algorithm.programmers.d20260302;

import java.util.HashMap;
import java.util.Map;

public class exam01 {

    public static Map<String, Integer> map = new HashMap<>();
    public static String[] strArr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L","M", "N", "O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public static void main(String[] args) {
        String name = "JEROEN";
        System.out.println(solution(name));
        name = "JAN";
        System.out.println(solution(name));
    }

    public static int solution(String name) {
        for (int i = 1; i <= 26; i++) {
            map.put(strArr[i-1], i);
        }

        int length = name.length();
        int getStrMin = 0;
        int getDirMin = name.length() - 1;
        for (int i = 0; i < length; i++) {
            String str = String.valueOf(name.charAt(i));
            getStrMin += getMinCnt(str);

            int continueIdx = i + 1;
            while (continueIdx < length && name.charAt(continueIdx) == 'A') {
                continueIdx++;
            }

            getDirMin = Math.min(getDirMin, Math.min((i * 2) + length - continueIdx, (length - continueIdx) * 2 + i));
        }

        return getStrMin + getDirMin;
    }

    private static int getMinCnt(String str) {
        int forwardCnt = map.get(str) - 1;
        int backwardCnt = 26 - map.get(str) + 1;
        return Math.min(forwardCnt, backwardCnt);
    }

}
