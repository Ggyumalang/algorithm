/**
 * 2025 카카오 하반기 1차 > 중요한 단어를 스포 방지
 */

package exercise.algorithm.programmers.d20260311;

public class exam01 {
    public static void main(String[] args) {
        String message = "here is muzi here is a secret message";
        int[][] spoiler_ragnes = {{0,3}, {23,28}};
        System.out.println(solution(message, spoiler_ragnes));

        message = "my phone number is 01012345678 and may i have your phone number";
        spoiler_ragnes = new int[][]{{5, 5}, {25, 28}, {34, 40}, {53, 59}};
        System.out.println(solution(message, spoiler_ragnes));
    }

    public static int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        //SET 2개를 만든다 - 하나는 일반, 하나는 중요
        //만약 일반에 이미 있는 거라면 중요 단어가 아니게 된다.
        //중요 단어는 이전에 공개된 스포 방지 단어와 중복되지 않아야 한다.
        //여러 단어가 동시에 공개 시 왼쪽부터 순서대로 하나씩 중요 단어가 된다.
        //해당 위치에 포함되는 단어들을 어떻게 표현할 수 있을 지가 문제다..
        for (int i = 0; i < message.length(); i++) {
//            String
        }


        return answer;
    }
}
