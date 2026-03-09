/**
 * 2025 프로그래머스 코드챌린지 1차 예선 > 비밀 코드 해독
 */
package exercise.algorithm.programmers.d20260309;

import java.util.*;
import java.util.stream.Collectors;

public class exam01 {
    public static void main(String[] args) {
        int n = 10;
        int[][] q = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}};
        int [] ans = {2,3,4,3,3};
        System.out.println(solution(n,q,ans));
    }

    // n 개 중 5 개를 오름차순으로 뽑는다 nP5
    static Set<int[]> answer;
    static int N;
    public static int solution(int n, int[][] q, int[] ans) {
        answer = new HashSet<>();
        N = n;
        int[] selected = new int[5];
        boolean[] visited = new boolean[n+1];

        permutation(selected, visited, q, ans, 0);
        return answer.size();
    }

    private static void permutation(int[] selected, boolean[] visited, int[][] q, int[] ans, int depth) {
        if(depth == 5){
            Arrays.sort(selected);
            System.out.println("selected = " + Arrays.toString(selected));

            if(answer.contains(selected)){
                System.out.println("answer = " + answer);
                return;
            }

            if(isMatched(q, ans, selected)){
                answer.add(selected);
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(!visited[i]){
                visited[i] = true;
                selected[depth] = i;
                permutation(selected, visited, q, ans, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean isMatched(int[][] q, int[] ans, int[] selected) {

        for(int i = 0; i < q.length; i++){
            int matchedCnt = 0;
            int answerCnt = ans[i];
            for (int qVal : q[i]) {
                for (int sVal : selected) {
                    if (qVal == sVal) {
                        matchedCnt++;
                        break;
                    }
                }
            }
            if(answerCnt != matchedCnt) {
                return false;
            }
        }

        return true;
    }
}
