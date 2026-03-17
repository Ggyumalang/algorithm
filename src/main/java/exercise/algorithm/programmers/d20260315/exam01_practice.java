package exercise.algorithm.programmers.d20260315;

import lombok.ToString;

import java.util.Arrays;
import java.util.PriorityQueue;

public class exam01_practice {

    @ToString
    static class Job implements Comparable<Job> {
        public int no;
        public int reqTime;
        public int duration;

        public Job(int no, int reqTime, int duration) {
            this.no = no;
            this.reqTime = reqTime;
            this.duration = duration;
        }

        @Override public int compareTo(Job o) {
            if(o.duration == this.duration) {
                if(o.reqTime == this.reqTime) {
                    return this.no - o.no;
                }
                return this.reqTime - o.reqTime;
            }
            return this.duration - o.duration;
        }
    }

    public static void main(String[] args) {
        int[][] jobs = {{0,3}, {1,9} , {3,5}};

        System.out.println(solution(jobs));

        jobs = new int[][]{{0,3}, {4,5} , {4,5}};

        System.out.println(solution(jobs));

        jobs = new int[][]{{7,8}, {3,5} , {9,6}};

        System.out.println(solution(jobs));
    }

    public static int solution(int[][] jobs) {
        int length = jobs.length;
        if (length == 0) {
            return 0;
        }

        if(length == 1) {
            return jobs[0][1];
        }

        // 배열을 정렬합니다.
        Arrays.sort(jobs, (o1, o2) -> {
            if(o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        // 대기 큐
        PriorityQueue<Job> pq = new PriorityQueue<>();

        int currentTime = 0;
        int totalTurnaround = 0;
        int finishCnt = 0;
        int idx = 0;

        while(finishCnt < length) {

            while(idx < length && jobs[idx][0] <= currentTime) {
                pq.offer(new Job(idx, jobs[idx][0], jobs[idx][1]));
                idx++;
            }

            // 만약 대기 큐가 비었다면
            // 다음 요청 건으로 이동
            if(pq.isEmpty()) {
                currentTime = jobs[idx][0];
            } else {
                Job job = pq.poll();
                currentTime += job.duration;
                totalTurnaround += currentTime - job.reqTime;
                finishCnt++;
            }
        }
        return totalTurnaround / length;
    }
}
