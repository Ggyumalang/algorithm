package exercise.algorithm.programmers.d20260326;

import lombok.ToString;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class exam02 {
    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(solution(picks, minerals));

        picks = new int[]{0, 1, 1};
        minerals = new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println("solution() = " + solution(picks, minerals));

        picks = new int[]{0, 1, 0};
        minerals = new String[]{"stone", "stone", "stone", "stone", "stone", "diamond", "diamond", "diamond", "diamond", "diamond"};
        System.out.println("solution() = " + solution(picks, minerals));
    }

    @ToString
    static class Placement implements Comparable<Placement> {
        int pos;
        int size;

        public Placement(int pos, int size) {
            this.pos = pos;
            this.size = size;
        }

        @Override
        public int compareTo(Placement o) {
            return Integer.compare(o.size, this.size);
        }
    }

    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;

        // 5개씩 나눠서 가장 큰 부분은 다이아로, 나머지는 철, 마지막은 돌
        // 다이아는 가중치 5로 철은 3 돌은 1
        int maxPick = Math.min(Arrays.stream(picks).sum() * 5, minerals.length);
        List<Integer> dividedList = getList(maxPick, minerals);

        // 중요한 게.. 어디 쪽이 피로도가 제일 높은 지 확인하고 그것부터 진행하는 것
        // 그럴려면 어디 쪽이 얼마다 이게 필요.
        // 그럴려면 크기와 위치를 저장하는 게 필요.
        // 그리고 이것을 순서대로 정리하여 처리하면 됨.
        PriorityQueue<Placement> pq = new PriorityQueue<>();
        for (int i = 0; i < dividedList.size(); i++) {
            pq.offer(new Placement(i * 5, dividedList.get(i)));
        }

        int diaPick = picks[0]; // 다이아는 모든 것 피로도 1
        int ironPick = picks[1]; // 철은 다이아 5, 철 1, 돌1
        int stonePick = picks[2]; // 돌은 다이아 25, 철 5, 돌 1

        while (!pq.isEmpty()) {
            Placement p = pq.poll();
            int pos = p.pos;
            int maxPos = Math.min(pos + 5, minerals.length);
            if(diaPick > 0) {
                answer += maxPos - pos;
                diaPick--;
            } else if(ironPick > 0) {
                answer += calcFatigue("iron",  minerals, pos, maxPos);
                ironPick--;
            } else if(stonePick > 0) {
                answer += calcFatigue("stone",  minerals, pos, maxPos);
                stonePick--;
            } else {
                // 모두 떨어진 것
                return answer;
            }
        }
        return answer;
    }

    private static List<Integer> getList(int maxPick, String[] minerals) {
        List<Integer> dividedList = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < maxPick; i++) {
            if(i > 0 && i % 5 == 0) {
                dividedList.add(sum);
                sum = 0;
            }

            if(minerals[i].equals("diamond")) {
                sum += 25;
            } else if(minerals[i].equals("iron")) {
                sum += 5;
            } else {
                sum += 1;
            }
        }

        if(sum != 0) {
            dividedList.add(sum);
        }
        return dividedList;
    }

    private static int calcFatigue(String pick, String[] minerals, int pos, int maxPos) {

        int result = 0;
        for (int i = pos; i < maxPos; i++) {
            if(pick.equals("iron")) {
                if(minerals[i].equals("diamond")) {
                    result += 5;
                } else {
                    result += 1;
                }
            } else if(pick.equals("stone")) {
                if(minerals[i].equals("diamond")) {
                    result += 25;
                } else if(minerals[i].equals("iron")) {
                    result += 5;
                } else {
                    result += 1;
                }
            }
        }

        return result;
    }
}
