/**
 * 2025 프로그래머스 코드챌린지 2차 예선
 * 봉인된 주문
 */

package exercise.algorithm.programmers.d20260312;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exam02 {

    public static void main(String[] args) {
        long n = 30;
        String [] bans = {"d", "e", "bb", "aa", "ae"};
        System.out.println(solution(n, bans));
    }

    //static long N;
    //static String[] ARR = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    //static String[] BANS;
    //static String answer;
    public static String solution(long n, String[] bans) {
        //N = n;
        String answer = "";
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            list.add((long) Math.pow(26, i+1));
        }

        for (int i = 0; i < bans.length; i++) {
            switch (bans[i].length()) {
                case 1:
                    list.set(0,list.get(0)-1);
                    break;
                case 2:
                    list.set(1,list.get(1)-1);
                    break;
                case 3:
                    list.set(2,list.get(2)-1);
                    break;
                case 4:
                    list.set(3,list.get(3)-1);
                    break;
                case 5:
                    list.set(4,list.get(4)-1);
                    break;
                case 6:
                    list.set(5,list.get(5)-1);
                    break;
                case 7:
                    list.set(6,list.get(6)-1);
                    break;
                case 8:
                    list.set(7,list.get(7)-1);
                    break;
                case 9:
                    list.set(8,list.get(8)-1);
                    break;
                case 10:
                    list.set(9,list.get(9)-1);
                    break;
                case 11:
                    list.set(10,list.get(10)-1);
                    break;
            }
        }

        System.out.println("arr = " + list);
        int startIdx = 0;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) > n) {
                if(i != 0) {
                    startIdx = i - 1;
                }
                break;
            } else if (list.get(i) == n) {
                startIdx = i;
                break;
            }
        }

        System.out.println("startIdx = " + startIdx);

        StringBuilder str = new StringBuilder();
        str.append("a".repeat(startIdx + 1));

        for (int i = startIdx + 1; i <= n ; i++) {

        }

        return answer;
    }
}
