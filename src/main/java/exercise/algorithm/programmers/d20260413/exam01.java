/**
 * 바이러스 파이프
 */

package exercise.algorithm.programmers.d20260413;

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
        edges = new int[][]{{1, 2, 1}, {1, 3, 1}, {1, 4, 3}, {1, 5, 2}, {5, 6, 1}, {5, 7, 1}, {2, 8, 3}, {2, 9, 2}, {9, 10, 1}};
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

    static List<List<Node>> graph;
    public static int solution(int n, int infection, int[][] edges, int k) {
        if(n <= 1) {
            return n;
        }

        int answer = 0;
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 파이프들 연결 시키기
        for(int[] edge : edges) {
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }

        // 맨 처음 파이프 열기 시작!
        // 가장 많이 해야하므로..
        bfs(infection,k);
        return answer;
    }

    static class Plain {
        int node;
        int depth;

        public Plain(int node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    private static void bfs(int infection, int k) {

    }
}
