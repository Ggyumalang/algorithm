/**
 * 프로그래머스 > 힙 > 이중우선순위큐
 */
package exercise.algorithm.programmers.d20260317;

import java.util.*;

public class exam01 {
    public static void main(String[] args) {
        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        System.out.println(Arrays.toString(solution(operations)));
        System.out.println(Arrays.toString(solution2(operations)));

        operations = new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(solution(operations)));
        System.out.println(Arrays.toString(solution2(operations)));
    }

    public static int[] solution(String[] operations) {
        // 우선순위 큐 -
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < operations.length; i++) {
            String[] split = operations[i].split(" ");
            if("I".equals(split[0])) {
                list.add(Integer.parseInt(split[1]));
                Collections.sort(list);
            } else if ("D".equals(split[0])) {
                if(list.isEmpty()) {
                    continue;
                }
                if("1".equals(split[1])) {
                    list.remove(list.size()-1);
                } else if("-1".equals(split[1])) {
                    list.remove(0);
                } else {
                    return new int[0];
                }
            } else {
                return new int[0];
            }
        }

        if(list.isEmpty()) {
            return new int[]{0,0};
        }
        return new int[]{list.get(list.size()-1), list.get(0)};
    }

    public static int[] solution2(String[] operations) {
        Queue<Integer> minpq = new PriorityQueue<>();
        Queue<Integer> maxpq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < operations.length; i++) {
            if(operations[i].startsWith("I")) {
                int val = Integer.parseInt((operations[i].substring(2)));
                minpq.offer(val);
                maxpq.offer(val);
            } else if(operations[i].startsWith("D")) {
                int val = Integer.parseInt((operations[i].substring(2)));

                if(minpq.isEmpty() || maxpq.isEmpty()) {
                    continue;
                }
                if (val == -1) {
                    Integer polled = minpq.poll();
                    maxpq.remove(polled);
                } else if(val == 1) {
                    Integer polled = maxpq.poll();
                    minpq.remove(polled);
                }
            }
        }

        if(minpq.isEmpty() && maxpq.isEmpty()) {
            return new int[]{0,0};
        }

        return  new int[]{maxpq.poll(), minpq.poll()};
    }
}
