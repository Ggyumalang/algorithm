/**
 * 호텔 대실
 */
package exercise.algorithm.programmers.d20260331;

import java.util.Arrays;
import java.util.Comparator;

public class exam02 {

    public static void main(String[] args) {
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        System.out.println("solution(book_time) = " + solution(book_time));

        book_time = new String[][]{{"09:10", "10:10"}, {"10:20", "12:20"}};
        System.out.println("solution(book_time) = " + solution(book_time));

        book_time = new String[][]{{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}};
        System.out.println("solution(book_time) = " + solution(book_time));

        book_time = new String[][]{{"09:00", "10:00"}, {"10:10", "11:10"}, {"11:20", "12:20"}, {"11:20", "12:20"}};
        System.out.println("solution(book_time) = " + solution(book_time));
    }

    // 최소한의 객실만 사용하여 예약 손님들을 받고자 함.
    // 한 번 사용한 객실은 퇴실 시간 기준 10분간 청소 후 사용 가능
    public static int solution(String[][] book_time) {
        int roomCnt = 0;
        Arrays.sort(book_time, Comparator.comparing(a -> a[0]));
        boolean[] visited = new boolean[book_time.length];

        System.out.println("book_time = " + Arrays.deepToString(book_time));

        for (int i = 0; i < book_time.length; i++) {
            if(!visited[i]) {
                int cleanEndTime = transform(book_time[i][1], 10);
                visited[i] = true;
                dfs(visited, book_time, cleanEndTime, i);
                roomCnt++;
            }
        }

        return roomCnt;
    }

    private static void dfs(boolean[] visited, String[][] bookTime, int cleanEndTime, int idx) {
        System.out.println("visited = " + Arrays.toString(visited));
        if(idx == bookTime.length - 1) {
            return;
        }

        for (int i = idx + 1; i < visited.length ; i++) {
            if(!visited[i] && transform(bookTime[i][0], 0) >= cleanEndTime) {
                visited[i] = true;
                cleanEndTime = transform(bookTime[i][1], 10);
                dfs(visited, bookTime, cleanEndTime, i);
                break;
            }
        }
    }

    private static int transform(String time, int cleaningTime) {
        int hh = Integer.parseInt(time.split(":")[0]);
        int mm = Integer.parseInt(time.split(":")[1]);

        if(cleaningTime > 0) {
            mm += cleaningTime;
            if(mm >= 60) {
                hh += 1;
                mm = mm - 60;
            }
        }
        return ( hh * 100 ) + mm;
    }
}
