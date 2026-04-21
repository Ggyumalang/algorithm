/**
 * 방금그곡
 */
package exercise.algorithm.programmers.d20260421;

import lombok.ToString;

import java.util.PriorityQueue;

public class exam01 {
    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m, musicinfos));

        m = "CC#BCC#BCC#BCC#B";
        musicinfos = new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        System.out.println(solution(m, musicinfos));

        m = "ABC";
        musicinfos = new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m, musicinfos));
    }

    @ToString
    public static class Music implements Comparable<Music>{
        int idx; // 노래 순서
        String title; // 노래 제목
        String melody; // 만든 멜로디
        int time; // 재생된 시간

        public Music(int idx, String title, String melody, int time) {
            this.idx = idx;
            this.title = title;
            this.melody = melody;
            this.time = time;
        }

        @Override
        public int compareTo(Music o) {
            if(this.time == o.time) {
                return this.idx - o.idx;
            }
            return o.time - this.time;
        }
    }

    public static String solution(String m, String[] musicinfos) {
        m = makePlatToSmall(m);

        String answerTitle = "(None)";
        int maxTime = -1; // 최대 재생 시간을 기록할 변수

        for (String musicinfo : musicinfos) {
            String[] split = musicinfo.split(",");
            int time = calcTime(split[0], split[1]);
            String title = split[2];
            String melody = makePlatToSmall(split[3]);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < time; i++) {
                sb.append(melody.charAt(i % melody.length()));
            }

            // 조건: 멜로디가 일치하고, 기존의 정답 곡보다 재생 시간이 더 '길' 때만 갱신
            // (time이 같을 때는 먼저 입력된 곡이 우선이므로 갱신하지 않음)
            if (sb.toString().contains(m)) {
                if (time > maxTime) {
                    maxTime = time;
                    answerTitle = title;
                }
            }
        }
        return answerTitle;
    }

    private static String makePlatToSmall(String m) {
            return m.replace("C#", "c")
                    .replace("D#", "d")
                    .replace("F#", "f")
                    .replace("G#", "g")
                    .replace("A#", "a")
                    .replace("B#", "b") // 방어적 코드
                    .replace("E#", "e"); // 방어적 코드
    }

    private static int calcTime(String startTime, String endTime) {
        String[] start = startTime.split(":");
        String[] end = endTime.split(":");

        int startTotalMin = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
        int endTotalMin = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);

        return endTotalMin - startTotalMin;
    }
}
