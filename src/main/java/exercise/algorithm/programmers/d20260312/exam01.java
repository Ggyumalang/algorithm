/**
 * 2025 프로그래머스 코드챌린지 2차 예선 > 서버 증설 횟수
 */
package exercise.algorithm.programmers.d20260312;

import lombok.ToString;

import java.util.PriorityQueue;
import java.util.Queue;

public class exam01 {

    @ToString
    static class Server implements Comparable<Server> {
        int count; // 증설된 서버의 수
        int index; // 증설되었을 때 index

        public Server(int count, int index) {
            this.count = count;
            this.index = index;
        }

        @Override
        public int compareTo(Server o) {
            return this.index - o.index;
        }
    }
    public static void main(String[] args) {
        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;
        System.out.println(solution(players,m, k));
        System.out.println(solution2(players,m, k));

        players = new int[]{0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0};
        m = 5;
        k = 1;
        System.out.println(solution(players,m, k));
        System.out.println(solution2(players,m, k));

        players = new int[]{0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1};
        m = 1;
        k = 1;
        System.out.println(solution(players,m, k));
        System.out.println(solution2(players,m, k));
    }

    public static int solution(int[] players, int m, int k) {
        int answer = 0; // 증설 횟수
        int currentServer = 0; // 현재의 총 서버 갯수
        Queue<Server> queue = new PriorityQueue<>();
        // 24시간 돌린다.
        for (int i = 0; i < players.length; i++) {
            // 몫은 몇 개의 서버가 필요한 지다.
            int share = players[i] / m;

            //queue 의 peek + k 가 i 보다 적거나 같다면 서버가 종료되었다는 의미다.
            if(!queue.isEmpty() && queue.peek().index + k <= i) {
                Server polled = queue.remove();
                currentServer -= polled.count;
            }

            // 증설
            if (share > currentServer) {
                int count = share - currentServer;
                queue.add(new Server(count, i));
                answer += count;
                currentServer = share;
            }
        }
        return answer;
    }

    public static int solution2(int[] players, int m, int k) {
        int answer = 0;
        int activeServer = 0;
        int[] installed = new int[players.length];

        for (int i = 0; i < 24; i++) {
            if(i >= k) {
                activeServer -= installed[i - k];
            }

            int req = players[i] / m;

            if(activeServer < req) {
                int add = req - activeServer;
                answer += add;
                installed[i] = add;
                activeServer = req;
            }
        }
        return answer;
    }
}
