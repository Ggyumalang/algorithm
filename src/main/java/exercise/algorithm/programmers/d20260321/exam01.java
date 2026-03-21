/**
 * 탐욕법 > 큰 수 만들기
 */

package exercise.algorithm.programmers.d20260321;

public class exam01 {

    public static void main(String[] args) {
        String number = "1924";
        int k = 2;
        System.out.println(solution(number, k));

        number = "1231234";
        k = 3;
        System.out.println(solution(number, k));
    }

    static int answer;
    public static String solution(String number, int k) {
        answer = 0;
        boolean[] visited = new boolean[number.length()];
        permutation(number,k,0,visited, "");
        return String.valueOf(answer);
    }

    private static void permutation(String number, int k, int depth, boolean[] visited, String str) {
        if(depth == number.length() - k) {
            System.out.println("str = " + str);
            answer = Math.max(answer, Integer.parseInt(str));
            return;
        }

        for (int i = 0; i < number.length(); i++) {
            if( i == number.length() - k) {
                answer = Math.max(answer, Integer.parseInt(number.substring(i)));
                return;
            }
            if(!visited[i]) {
                visited[i] = true;
                permutation(number, k, depth + 1, visited, str + number.charAt(i));
                visited[i] = false;
            }
        }
    }
}
