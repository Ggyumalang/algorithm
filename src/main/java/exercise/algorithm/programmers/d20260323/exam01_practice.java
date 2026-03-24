package exercise.algorithm.programmers.d20260323;

import java.util.Stack;

public class exam01_practice {
    public static void main(String[] args) {
        String number = "1924";
        int k = 2;
        System.out.println(solution(number, k));

        number = "1231234";
        k = 3;
        System.out.println(solution(number, k));

        number = "1000000";
        k = 2;
        System.out.println(solution(number, k));

        number = "987986";
        k = 4;
        System.out.println(solution(number, k));
    }

    public static String solution(String number, int k) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && c > stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        while (k > 0) {
             stack.pop();
             k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
