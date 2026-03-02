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
        int forwardAnswer = getMinCnt(String.valueOf(name.charAt(0)));
        int backwardAnswer = forwardAnswer;

        for (int i = 1; i < name.length(); i++) {
            String str = String.valueOf(name.charAt(i));
            forwardAnswer++;
            if(str.equals("A") && i == name.length() - 1) {
                forwardAnswer--;
                continue;
            }
            int minCnt = getMinCnt(str);
            forwardAnswer += minCnt;
            System.out.println("forwardAnswer = " + forwardAnswer);
        }

        for (int i = name.length() - 1; i >= 1 ; i--) {
            String str = String.valueOf(name.charAt(i));
            backwardAnswer++;
            if(str.equals("A") && i == 1) {
                backwardAnswer--;
                continue;
            }
            int minCnt = getMinCnt(str);
            backwardAnswer += minCnt;
            System.out.println("backwardAnswer = " + backwardAnswer);
        }

        System.out.println("forwardAnswer = " + forwardAnswer);
        System.out.println("backwardAnswer = " + backwardAnswer);

        return Math.min(forwardAnswer, backwardAnswer);
    }

    private static int getMinCnt(String str) {
        int forwardCnt = map.get(str) - 1;
        int backwardCnt = 26 - map.get(str) + 1;
        return Math.min(forwardCnt, backwardCnt);
    }

}
