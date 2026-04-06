/**
 * 과제 진행하기
 */
package exercise.algorithm.programmers.d20260403;

import lombok.ToString;

import java.util.*;

public class exam01 {

    public static void main(String[] args) {
        String[][] plans = {{"math", "12:30", "40"}, {"korean", "11:40", "30"}, {"english", "12:10", "20"}};
        System.out.println(Arrays.toString(solution(plans)));

        plans = new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
        System.out.println(Arrays.toString(solution(plans)));

        plans = new String[][]{{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}};
        System.out.println(Arrays.toString(solution(plans)));
    }

    @ToString
    static class Music implements Comparable<Music>{
        String name;
        int time;
        int duration;

        Music(String name, int time, int duration) {
            this.name = name;
            this.time = time;
            this.duration = duration;
        }

        @Override
        public int compareTo(Music o) {
            return Integer.compare(this.time, o.time);
        }
    }

    // 새로운 시작이 먼저이다! 끝 이전에 시작하는 게 있으면 시작이 먼저다.
    public static String[] solution(String[][] plans) {
        // 시작 순서에 따른 queue
        PriorityQueue<Music> startQueue = new PriorityQueue<>();
        for (String[] plan : plans) {
            String[] splitedStr = plan[1].split(":");
            startQueue.offer(new Music(plan[0],
                    Integer.parseInt(splitedStr[0]) * 60 + Integer.parseInt(splitedStr[1]),
                    Integer.parseInt(plan[2])));
        }

        // 대기하는 queue
        Stack<Music> standByStack = new Stack<>();
        List<String> answerList = new ArrayList<>();
        while(!startQueue.isEmpty()) {
            Music cur = startQueue.poll();

            System.out.println("startQueue = " + startQueue);
            System.out.println("standByStack = " + standByStack);

            if(!startQueue.isEmpty()) {
                Music next = startQueue.peek();
                int calcTime = cur.time + cur.duration;
                // 현재와 다음 비교
                // 끝내는 시간 보다 다음 것을 시작하는 시간이 빠름
                if(calcTime > next.time) {
                    int duration = calcTime - next.time;
                    // 현재 끝낼 수 없다면 standByStack 에 넣기
                    standByStack.push(new Music(cur.name, cur.time, duration));
                }
                // 현재 과제를 끝마치고 그 다음 과제 시작 전에 시간이 있다면
                // standByStack 에 있는 값을 가져와서 빼주기.
                else if(calcTime < next.time) {
                    // 현재 과제는 이미 끝마친 것이므로..
                    answerList.add(cur.name);
                    // 다음 시작 시간에서 현재 과제 끝난 시간만큼 standBy 값의 duration 에서 빼줍니다.
                    int diff = next.time - calcTime;
                    while(!standByStack.isEmpty() && diff > 0) {
                        Music standBy = standByStack.pop();
                        // standBy 가 못 끝내면 diff 만큼 빼주고 다시 stack에 넣습니다.
                        if(standBy.duration > diff) {
                            standBy.duration -= diff;
                            standByStack.push(standBy);
                            break;
                        }
                        // 끝낼 수 있다면 answer 에 넣고 diff 를 duration 만큼 감소시킵니다.
                        answerList.add(standBy.name);
                        diff -= standBy.duration;
                    }
                }
                // calcTime == next.time
                else {
                    answerList.add(cur.name);
                }
            } else {
                answerList.add(cur.name);
            }
        }

        while(!standByStack.isEmpty()) {
            answerList.add(standByStack.pop().name);
        }
        return answerList.toArray(String[]::new);
    }
}
