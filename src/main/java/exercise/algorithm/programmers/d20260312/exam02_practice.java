/**
 * 2025 프로그래머스 코드챌린지 2차 예선
 * 봉인된 주문
 */

package exercise.algorithm.programmers.d20260312;

import java.util.Arrays;

public class exam02_practice {

    public static void main(String[] args) {
        long n = 30;
        String [] bans = {"d", "e", "bb", "aa", "ae"};
        System.out.println(solution(n, bans));
    }

    public static String solution(long n, String[] bans) {
        // 길이순, 길이가 같다면 사전순으로 정렬한다.
        Arrays.sort(bans, (o1, o2) -> {
            if(o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }

            return o1.length() - o2.length();
        });

        //ban 당한 애들 중 n 보다 작거나 같은 숫자가 나온다면 1씩 증가시킨다.
        for (int i = 0; i < bans.length; i++) {
            if(strToNum(bans[i]) <= n) {
                n++;
            }
        }

        return numToStr(n);
    }

    private static long strToNum(String ban) {
        long num = 0;
        for (int i = 0; i < ban.length(); i++) {
            char c = ban.charAt(i);
            num = num * 26 + (c - 'a' + 1);
        }
        return num;
    }

    public static String numToStr(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--; // -1을 시켜야 문자열과 맞게 됨
            long remain = n % 26;
            sb.insert(0, (char) ('a' + remain));
            n = n / 26;
        }
        return sb.toString();
    }

}
