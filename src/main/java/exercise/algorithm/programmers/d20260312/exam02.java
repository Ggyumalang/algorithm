/**
 * 2025 프로그래머스 코드챌린지 2차 예선
 * 봉인된 주문
 */

package exercise.algorithm.programmers.d20260312;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class exam02 {

    public static void main(String[] args) {
        long n = 30;
        String [] bans = {"d", "e", "bb", "aa", "ae"};
        System.out.println(solution(n, bans));
    }

    public static String solution(long n, String[] bans) {
        // 금지어 정렬
        Arrays.sort(bans, (o1, o2) -> {
            if(o1.length() == o2.length()){
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });

        System.out.println("bans = " + Arrays.toString(bans));
        for (String ban : bans) {
            if (stringToNumber(ban) <= n) {
                n++;
            }
        }

        for (int i = 0; i < 26; i++) {
            System.out.println("'a' + i = " + (char) ('a' + i));
        }

        return numberToString(n);
    }

    public static long stringToNumber(String s) {
        long num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // number = number * 26 + val Horner's method - 이전 결과값에 진법을 계속 곱하며 자릿수를 밀어 올리는 방식
            num = num * 26 + (c - 'a' + 1);
        }

        return num;
    }

    public static String numberToString(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            num--; // 1부터 시작하는 체계를 0부터 시작하도록 1 빼줌
            long rem = num % 26; // 0 ~ 25의 값이 나옴

            sb.insert(0, (char) ('a' + rem));

            num = num / 26;
        }

        return sb.toString();
    }
}
