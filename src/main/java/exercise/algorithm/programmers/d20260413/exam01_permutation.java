/**
 * 바이러스 파이프
 */
package exercise.algorithm.programmers.d20260413;

import lombok.ToString;

import java.util.*;

public class exam01_permutation {
    public static void main(String[] args) {
        int n = 10;
        int infection = 1;
        int[][] edges = {{1, 2, 1}, {1, 3, 1}, {1, 4, 3}, {1, 5, 2}, {5, 6, 1}, {5, 7, 1}, {2, 8, 3}, {2, 9, 2}, {9, 10, 1}};
        int k = 2;
        System.out.println(solution(n, infection, edges, k));

        n = 7;
        infection = 6;
        edges = new int[][]{{1, 2, 3}, {1, 4, 3}, {4, 5, 1}, {5, 6, 1}, {3, 6, 2}, {3, 7, 2}};
        k = 3;
        System.out.println(solution(n, infection, edges, k));
    }

    @ToString
    static class Node {
        int to;
        int type;

        public Node(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }

    static List<List<Integer>> cases;
    static List<List<Node>> graph;
    static int maxInfected;
    static int N;
    public static int solution(int n, int infection, int[][] edges, int k) {
        if(n <= 1) {
            return n;
        }

        cases = new ArrayList<>();
        graph = new ArrayList<>();
        maxInfected = 0;
        N = n;

        for (int i = 0; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }
        // 3가지의 수로 k 를 장식할 경우의 수 3Pk
        permutation(0,k, new ArrayList<>());
        System.out.println("cases = " + cases);
        // 파이프들 연결 시키기
        for(int[] edge : edges) {
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }

        for (List<Integer> caseList : cases) {
            bfs(infection, caseList);
        }

        return maxInfected;
    }

    private static void bfs(int infection, List<Integer> caseList) {
        boolean[] infected = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        // 1. 초기 감염자 설정
        infected[infection] = true;
        queue.add(infection);

        // 2. 파이프를 하나씩 연다
        for(int type : caseList) {
            if(queue.isEmpty()) {
                break;
            }

            // [수정된 핵심] 이번 파이프 개방으로 끝까지 퍼져나갈 '확산 전용 큐' 생성
            // 기존에 감염된 모든 노드들이 출발점이 됩니다.
            Queue<Integer> turnQueue = new LinkedList<>(queue);
            System.out.println("turnQueue = " + turnQueue);

            while (!turnQueue.isEmpty()) {
                int cur = turnQueue.poll();

                for(Node next : graph.get(cur)) {
                    // 간선 타입이 열린 파이프와 일치하고, 아직 감염되지 않았다면..
                    if(next.type == type && !infected[next.to]) {
                        infected[next.to] = true;
                        // 꼬리에 꼬리를 물고 더 퍼지도록 추가
                        turnQueue.add(next.to);
                        queue.add(next.to); // 다음 턴에서도 출발점이 되어야 함.
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N ; i++) {
            if(infected[i]) {
                cnt++;
            }
        }

        maxInfected = Math.max(maxInfected, cnt);
    }

    private static void permutation(int depth, int k, List<Integer> list) {
        if(depth == k) {
            cases.add(new ArrayList<>(list));
            return;
        }

        for (int i = 1; i <= 3 ; i++) {
            list.add(i);
            if(list.size() > 1 && Objects.equals(list.get(list.size() - 2), list.get(list.size() - 1))) {
                list.remove(list.size() - 1);
                continue;
            }
            permutation(depth + 1, k, list);
            list.remove(list.size()-1);
        }
    }
}
