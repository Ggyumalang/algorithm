package exercise.algorithm.programmers.d20260414;

import lombok.ToString;

import java.util.*;

public class exam01 {
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

    static List<List<Integer>> types;
    static List<List<Node>> graph;
    static int maxVal;
    public static int solution(int n, int infection, int[][] edges, int k) {
        types = new ArrayList<>();
        graph = new ArrayList<>();
        maxVal = 0;

        for (int i = 0; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }

        // 노드 간 연결
        for(int[] edge : edges) {
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }

        // 먼저 3개로 k 개를 뽑는 모든 경우의 수를 구한다.
        // => 순서 의미 있고 중복은 없으므로 순열
        permutation(k, 0, new ArrayList<>());

        for(List<Integer> typeList : types) {
            boolean[] infected = new boolean[n + 1];
            infected[infection] = true;
            bfs(typeList, infected, infection);
            int infectedCnt = 0;
            // 최상값으로..
            for(boolean b : infected) {
                if(b) {
                    infectedCnt++;
                }
            }
            maxVal = Math.max(maxVal, infectedCnt);
        }
        return maxVal;
    }

    private static void bfs(List<Integer> typeList, boolean[] infected, int infection) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(infection);

        for(int type : typeList) {
            if(queue.isEmpty()) {
                continue;
            }

            // 이번 턴의 사이즈 지정
            Queue<Integer> turnQueue = new LinkedList<>(queue);

            // 이번에 쭉 뻗어져 나간다!
            while(!turnQueue.isEmpty()) {
                int cur = turnQueue.poll();
                for(Node next : graph.get(cur)) {
                    if(next.type == type && !infected[next.to]) {
                        infected[next.to] = true;
                        turnQueue.add(next.to);
                        queue.add(next.to);
                    }
                }
            }
        }
    }

    private static void permutation(int k, int depth, List<Integer> list) {
        if(depth == k) {
            types.add(new ArrayList<>(list));
            return;
        }

        for (int i = 1; i <= 3 ; i++) {
            // 연속되는 수는 의미 없으므로 처리
            if(!list.isEmpty() && Objects.equals(list.get(list.size() - 1), i)) {
                continue;
            }
            list.add(i);
            permutation(k, depth + 1, list);
            list.remove(list.size()-1);
        }
    }
}
