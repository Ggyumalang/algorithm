/**
 * 코딩테스트 연습
 * 힙(Heap)
 * 디스크 컨트롤러
 */

package exercise.algorithm.programmers.d20260315;

import java.util.*;
import lombok.ToString;

public class exam01 {

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

        System.out.println(solution2(jobs));
    }

    public static int solution(int[][] jobs) {
        PriorityQueue<Job> pq = new PriorityQueue<>();

        Arrays.sort(jobs, (o1, o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        System.out.println("jobs = " + Arrays.deepToString(jobs));

        int currentTime = 0;
        pq.offer(new Job(0, jobs[0][0], jobs[0][1]));
        Set<Integer> idxSet = new HashSet<>();
        idxSet.add(0);
        int answer = jobs[0][0];

        while (!pq.isEmpty()) {
            Job job = pq.poll();
            currentTime += job.duration;
            answer += currentTime - job.reqTime;
            System.out.println("currentTime = " + currentTime);
            System.out.println("answer = " + answer);

            for (int i = 0; i < jobs.length; i++) {
                if(idxSet.contains(i)) {
                    continue;
                }
                if(jobs[i][0] <= currentTime) {
                    pq.offer(new Job(i, jobs[i][0], jobs[i][1]));
                    idxSet.add(i);
                } else if (jobs[i][0] > currentTime && pq.isEmpty()) {
                    currentTime = jobs[i][0];
                    pq.offer(new Job(i, jobs[i][0], jobs[i][1]));
                    idxSet.add(i);
                    break;
                }
            }
        }

        return answer / jobs.length;
    }

    public static int solution2(int[][] jobs) {
        PriorityQueue<Job> pq = new PriorityQueue<>();

        Arrays.sort(jobs, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        int currentTime = 0; // 현재 시간
        int totalTurnaround = 0; // 반환 시간 총합
        int jobIdx = 0; // jobs 배열을 순회할 인덱스 포인터
        int count = 0; // 처리가 완료된 작업의 개수

        //모든 작업 처리 시 까지 반복
        while(count < jobs.length) {
            System.out.println("currentTime = " + currentTime);
            // 1. 현재 시간 이하에 요청된 모든 작업을 우선순위 큐에 삽입
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= currentTime) {
                pq.offer(new Job(jobIdx, jobs[jobIdx][0], jobs[jobIdx][1]));
                jobIdx++;
            }

            System.out.println("pq = " + pq);

            //2. 큐가 비어 있다면 -> 디스크 유휴 상태이므로 다음 작업 요청 시간으로 건너뜀.
            if(pq.isEmpty()) {
                currentTime = jobs[jobIdx][0];
            } else {
                // 3. 큐에 작업이 있다면 -> 소요 시간이 가장 짧은 작업을 처리한다.
                Job job = pq.poll();
                currentTime += job.duration;
                totalTurnaround += currentTime - job.reqTime;
                count++;
            }
        }

        return totalTurnaround / jobs.length;
    }
}
