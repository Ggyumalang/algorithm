/**
 * 코딩 테스트 연습 > 2025 프로그래머스 코드챌린지 2차 예선 > 완전 범죄
 */

package exercise.algorithm.programmers.d20260304;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exam01 {

    public static void main(String[] args) {
        int[][] info = {{1,2}, {2,3}, {2,1}};
        int n = 4;
        int m = 4;
        System.out.println(solution(info, n, m));
        System.out.println(solution2(info, n, m));
        System.out.println(solution3(info, n, m));

        info = new int[][]{{1,2}, {2,3}, {2,1}};
        n = 1;
        m = 7;
        System.out.println(solution(info, n, m));
        System.out.println(solution2(info, n, m));
        System.out.println(solution3(info, n, m));

        info = new int[][]{{3,3}, {3,3}};
        n = 7;
        m = 1;
        System.out.println(solution(info, n, m));
        System.out.println(solution2(info, n, m));
        System.out.println(solution3(info, n, m));

        info = new int[][]{{3,3}, {3,3}};
        n = 6;
        m = 1;
        System.out.println(solution(info, n, m));
        System.out.println(solution2(info, n, m));
        System.out.println(solution3(info, n, m));

        info = new int[][]{{1,2}, {2,3}, {3,2}, {4,7}};
        n = 7;
        m = 8;
        System.out.println(solution(info, n, m));
        //System.out.println(solution2(info, n, m));
        //System.out.println(solution3(info, n, m));
        System.out.println(solution4(info, n, m));
    }

    public static int solution(int[][] info, int n, int m) {
        int answer = 0;
        // 1번째 수(A 가 흔적이 많이 남는) 기준 내림차순하고, a[1],b[1] 오름차순
        Arrays.sort(info, (a, b) -> {
            if(b[0] == a[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        List<Integer> removedIdx = new ArrayList<>();
        int M = m;

        for (int i = 0; i < info.length; i++) {
            if( M == 0 ) {
                break;
            }
            if(info[i][1] < M) {
                //answer+=info[i][0];
                removedIdx.add(i);
                M -= info[i][1];
            }
        }
        for (int i = 0; i < info.length; i++) {
            if(!removedIdx.contains(i)) {
                answer += info[i][0];
            }
        }

        return answer >= n ? -1 : answer;
    }

    static final int INF = 100000;
    // dp[x][b] = a x = 물건을 훔친 갯수 b = B 도둑의 흔적 갯수 a = A 도둑의 흔적 갯수
    public static int solution2(int[][] info, int n, int m) {
        int size = info.length;
        int [][] dp = new int [size+1][m];
        for(int i = 0; i <= size; i++){
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        for(int i = 1; i <= size; i++){
            int a = info[i-1][0];
            int b = info[i-1][1];
            for(int j = 0; j < m; j++){
                // a 선택
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                // b 선택
                if(j + b < m){
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i-1][j]);
                }
            }
        }
        int min = INF;
        for(int j = 0; j < m; j++){
            min = Math.min(dp[size][j], min);
        }
        return min >= n ? -1 : min;
    }

    public static int solution3(int[][] info, int n, int m) {
        int len = info.length; // 훔쳐야하는 갯수
        int [][] dp = new int [len+1][m];

        // 일단 가장 최댓값으로 채운다
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= len ; i++) {
            // a가 훔친 경우
            int a = info[i-1][0];
            // b가 훔친 경우
            int b = info[i-1][1];
            for(int j = 0; j < m; j++){

                // a가 훔쳤을 경우 ㅡ 값 증가 a 가 훔쳤으므로..
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);

                // b가 훔쳤을 경우 ㅡ 열 증가 b 가 훔쳤으므로..
                if(j + b < m){
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i-1][j]);
                }
            }
        }
        return Arrays.stream(dp[len]).min().getAsInt();
    }

    /**
     * 시간 초과 > dfs (완전탐색)
     */

    public static int[][] arr;
    public static int N;
    public static int M;
    public static int len;
    public static int answer = (int) (Math.pow(2, 10) - 1);

    public static int solution4(int[][] info, int n, int m) {
        N = n;
        M = m;
        len = info.length;
        arr = info.clone();

        //Arrays.sort(info, (a,b) -> (b[1] + b[0]) - (a[1] + a[0]));
        Arrays.sort(info, (a, b) -> {
            if(b[0] == a[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        dfs(0,0,0);

        return answer >= n ? -1 : answer;
    }

    private static void dfs(int sumA, int sumB, int stealedNum) {
        if(sumA >= N || sumB >= M || stealedNum > len) {
            return;
        }

        if(stealedNum == len) {
            answer = Math.min(answer, sumA);
            return;
        }

        // a 선택
        dfs(sumA + arr[stealedNum][0], sumB, stealedNum + 1);
        // b 선택
        dfs(sumA, sumB + arr[stealedNum][1], stealedNum + 1);
    }
}
