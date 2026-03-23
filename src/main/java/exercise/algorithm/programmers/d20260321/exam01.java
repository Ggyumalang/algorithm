/**
 * 탐욕법 > 큰 수 만들기
 */

package exercise.algorithm.programmers.d20260321;

import java.util.Stack;

public class exam01 {

    public static void main(String[] args) {
        String number = "1924";
        int k = 2;
        System.out.println(solution2(number, k));

        number = "1231234";
        k = 3;
        System.out.println(solution2(number, k));

        number = "1000000";
        k = 2;
        System.out.println(solution2(number, k));

        number = "987986";
        k = 4;
        System.out.println(solution2(number, k));
    }

    static long answer;
    public static String solution(String number, int k) {
        answer = 0;
        boolean[] visited = new boolean[number.length()];
        for (int i = 0; i < number.length(); i++) {
            permutation(number,k,1,visited, number.charAt(i) + "", i + 1);
        }
        return String.valueOf(answer);
    }

    private static void permutation(String number, int k, int depth, boolean[] visited, String str, int idx) {
        if(depth == ( number.length() - k) / 2) {
            long num = Long.parseLong(str);
            if(num < (long)(answer / Math.pow(10, (double) (number.length() - k) / 2))) {
                System.out.println("num = " + num);
                return;
            }
        }

        if(depth == number.length() - k) {
            System.out.println("str = " + str);
            answer = Math.max(answer, Long.parseLong(str));
            return;
        }

        for (int i = idx; i < number.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                permutation(number, k, depth + 1, visited, str + number.charAt(i), i + 1);
                visited[i] = false;
            }
        }
    }

    public static String solution2(String number, int k) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < number.length(); i++) {
            char val = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < val && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(val);
        }

        System.out.println("stack = " + stack);

        while( k > 0 ) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0,stack.pop());
        }
        return sb.toString();
    }
}
