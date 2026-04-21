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
        PriorityQueue<Music> pq = new PriorityQueue<>();
        int idx = 0;
        m = makePlatToSmall(m);
        System.out.println("m = " + m);
        for (String musicinfo : musicinfos) {
            String[] split = musicinfo.split(",");
            int time = calcTime(split[0], split[1]);
            String title = split[2];
            String melody = makePlatToSmall(split[3]);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < time; i++) {
                // 각 멜로디 조립 TIME 만큼..
                sb.append(melody.charAt(i % melody.length()));
            }

            // 포함되는 지 여부 확인하기
            if (sb.toString().contains(m)) {
                pq.add(new Music(idx++, title, sb.toString(), time));
            }
        }

        if(pq.isEmpty()) {
            return "(None)";
        }

        return pq.peek().title;
    }

    private static String makePlatToSmall(String m) {
        StringBuilder sb = new StringBuilder();
        char formerC = '#';
        for (int i = 0; i < m.length(); i++) {
            char c = m.charAt(i);
            if(c == '#') {
                sb.replace(sb.length() - 1, sb.length(), Character.toLowerCase(formerC) + "");
            } else {
                sb.append(c);
                formerC = c;
            }
        }
        return sb.toString();
    }

    private static int calcTime(String startTime, String endTime) {
        String startHour = startTime.split(":")[0];
        String startMin = startTime.split(":")[1];
        String endHour = endTime.split(":")[0];
        String endMin = endTime.split(":")[1];

        int min = Integer.parseInt(endMin) - Integer.parseInt(startMin);
        if(endHour.equals(startHour)) {
            return min;
        }

        int hhDiff = (Integer.parseInt(endHour) - Integer.parseInt(startHour)) * 60;
        if(min < 0) {
            return hhDiff - min;
        }

        return hhDiff + min;
    }
}
