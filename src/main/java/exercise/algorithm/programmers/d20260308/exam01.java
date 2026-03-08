/**
 * 2025 프로그래머스 코드 챌린지 1차 예선 > 유연근무제
 */


package exercise.algorithm.programmers.d20260308;

public class exam01 {

    public static void main(String[] args) {
        int[] schedules = {700, 800, 1100};
        int[][] timelogs = {{710, 2359, 1050, 700, 650, 631, 659}, {800, 801, 805, 800, 759, 810, 809}, {1105, 1001, 1002, 600, 1059, 1001, 1100}};
        int startday = 5;
        System.out.println(solution(schedules, timelogs, startday));

        schedules = new int[]{730, 855, 700, 720};
        timelogs = new int[][]{{710, 700, 650, 735, 700, 931, 912}, {908, 901, 805, 815, 800, 831, 835}, {705, 701, 702, 705, 710, 710, 711}, {707, 731, 859, 913, 934, 931, 905}};
        startday = 1;
        System.out.println(solution(schedules, timelogs, startday));
    }

    public static int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for (int i = 0; i < timelogs.length; i++) {
            int limit = schedules[i] + 10;
            if(limit % 100 > 59) {
                limit = limit + 100 - 60;
            }
            int nowDay = startday;
            answer++;
            for (int j = 0; j < timelogs[i].length; j++) {
                // 주말은 제외
                if(nowDay % 7 == 6 || nowDay % 7 == 0) {
                    nowDay++;
                    continue;
                }

                if(timelogs[i][j] > limit) {
                    System.out.println("limit = " + limit);
                    System.out.println("timelogs[i][j] = " + timelogs[i][j]);
                    answer--;
                    break;
                }
                nowDay++;
            }
        }
        return answer;
    }
}
