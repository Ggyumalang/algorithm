/**
 * 기지국 설치 > 그리디 문제
 * ** 중요한 것 올림 할 때 (분모 + 분자 - 1) / 분자 >>> 자동 올림
 */

package exercise.algorithm.programmers.d20260506;

import java.util.LinkedList;
import java.util.Queue;

public class exam01 {

    public static void main(String[] args) {
        int n = 11;
        int[] stations = {4,11};
        int w = 1;
        System.out.println(solution(n, stations, w));
        System.out.println(solution2(n, stations, w));

        n = 16;
        stations = new int[]{9};
        w = 2;
        System.out.println(solution(n, stations, w));
        System.out.println(solution2(n, stations, w));
    }

    //뻘짓./.
    public static int solution(int n, int[] stations, int w) {
        return bfs(n, stations, w);
    }

    public static int solution2(int n, int[] stations, int w) {
        int answer = 0;
        int pointer = 1;

        for (int station: stations) {
            int emptySpace = (station - w - pointer);
            answer += (emptySpace + (w * 2 + 1) - 1) / (w * 2 + 1);
            pointer = station + w + 1;
        }

        if (pointer <= n) {
            answer += ((n - pointer + 1) + (w * 2 + 1) - 1) / (w * 2 + 1);
        }
        return answer;
    }

    private static int bfs(int n, int[] stations, int w) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int answer = 0;
        for (int station: stations) {
            queue.add(station);
            visited[station] = true;
        }

        while (!queue.isEmpty()) {
            int num =  queue.poll();
            int left = Math.max(num - w, 1);
            int right = Math.min(num + w, n);
            int nleft = Math.max(left - w - 1, 1);
            int nRight = Math.min(right + w + 1, n);

            while (!visited[left] && left < num) {
                visited[left++] = true;
            }

            while (!visited[right] && num < right) {
                visited[right--] = true;
            }

            if(!visited[nleft]) {
                visited[nleft] = true;
                queue.offer(nleft);
                answer++;
            }

            if(!visited[nRight]) {
                visited[nRight] = true;
                queue.offer(nRight);
                answer++;
            }
        }
        return answer;
    }
}
