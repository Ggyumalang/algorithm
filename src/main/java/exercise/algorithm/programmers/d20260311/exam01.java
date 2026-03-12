/**
 * 2025 카카오 하반기 1차 > 중요한 단어를 스포 방지
 */

package exercise.algorithm.programmers.d20260311;

import java.util.*;

public class exam01 {
    public static void main(String[] args) {
        String message = "here is muzi here is a secret message";
        int[][] spoiler_ragnes = {{0,3}, {23,28}};
        System.out.println(solution(message, spoiler_ragnes));

        message = "my phone number is 01012345678 and may i have your phone number";
        spoiler_ragnes = new int[][]{{5, 5}, {25, 28}, {34, 40}, {57, 63}};
        System.out.println(solution(message, spoiler_ragnes));

        message = "here is muzi here is a secret message";
        spoiler_ragnes = new int[][]{{2,5}, {26,29}};
        System.out.println(solution(message, spoiler_ragnes));

        message = "here";
        spoiler_ragnes = new int[][]{{3,4}, {26,29}};
        System.out.println(solution(message, spoiler_ragnes));
    }

    public static int solution(String message, int[][] spoiler_ranges) {
        if(message.isEmpty() || message.trim().isEmpty() || spoiler_ranges.length == 0) {
            return 0;
        }

        List<String> messages = new ArrayList<>(List.of(message.split(" ")));
        Map<Integer, List<Integer>> map = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < messages.size(); i++) {
            int start = idx;
            idx = start + messages.get(i).length() - 1;
            map.put(i, List.of(start, idx));
            idx+=2;
        }
        System.out.println("map = " + map);
        Set<String> answerSet = new HashSet<>();

        for (int[] spoiler_range : spoiler_ranges) {
            int startIdx = spoiler_range[0];
            int endIdx = spoiler_range[1];
            for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                // start 포함  rangeStart <= start <= rangeEnd
                if(entry.getValue().get(0) <= startIdx && startIdx <= entry.getValue().get(1) ) {
                    if(!messages.get(entry.getKey()).isEmpty()) {
                        answerSet.add(messages.get(entry.getKey()));
                        messages.set(entry.getKey(), "");
                    }
                    continue;
                }

                // end 포함 rangeStart <= end <= rangeEnd
                if(entry.getValue().get(0) <= endIdx && entry.getValue().get(1) >= endIdx) {
                    if(!messages.get(entry.getKey()).isEmpty()) {
                        answerSet.add(messages.get(entry.getKey()));
                        messages.set(entry.getKey(), "");
                    }
                    continue;
                }

                // 사이값 포함 start <= range <= endIdx
                if(startIdx <= entry.getValue().get(0) && entry.getValue().get(1) <= endIdx) {
                    if(!messages.get(entry.getKey()).isEmpty()) {
                        answerSet.add(messages.get(entry.getKey()));
                        messages.set(entry.getKey(), "");
                    }
                    continue;
                }

                // 사이값 포함 start <= range <= endIdx
                if(startIdx <= entry.getValue().get(0) && entry.getValue().get(0) <= endIdx) {
                    if(!messages.get(entry.getKey()).isEmpty()) {
                        answerSet.add(messages.get(entry.getKey()));
                        messages.set(entry.getKey(), "");
                    }
                    continue;
                }

                // 사이값 포함 start <= range <= endIdx
                if(startIdx <= entry.getValue().get(1) && entry.getValue().get(1) <= endIdx) {
                    if(!messages.get(entry.getKey()).isEmpty()) {
                        answerSet.add(messages.get(entry.getKey()));
                        messages.set(entry.getKey(), "");
                    }
                }
            }
        }
        System.out.println(answerSet);
        System.out.println(messages);

        for (String s: messages) {
            if (answerSet.contains(s)) {
                System.out.println("s = " + s);
                answerSet.remove(s);
            }
        }


        return answerSet.size();
    }
}
