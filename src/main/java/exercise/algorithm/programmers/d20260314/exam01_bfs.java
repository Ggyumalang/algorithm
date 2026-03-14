/**
 * 2025 프로그래머스 코드 챌린지 1차 예썬 > 지게차와 크레인
 */

package exercise.algorithm.programmers.d20260314;

import java.util.*;

public class exam01_bfs {

    public static void main(String[] args) {
        String[] storage = {"AZWQY", "CAABX", "BBDDA", "ACACA"};
        String[] requests = {"A", "C", "A"};
        System.out.println(solution(storage, requests));

        storage =  new String[] {"HAH", "HBH", "HHH", "HAH", "HBH"};
        requests = new String[]  {"C", "B", "B", "B", "B", "H"};
        System.out.println(solution(storage, requests));

        storage =  new String[] {"HHH", "HBH", "HHH", "HAH", "ABB"};
        requests = new String[]  {"C", "B", "B", "B", "B", "H", "H"};
        System.out.println(solution(storage, requests));
    }

    static String[][] strArr;
    static int[] dirX = {-1,1,0,0};
    static int[] dirY = {0,0,1,-1};
    public static int solution(String[] storage, String[] requests) {
        if(storage.length == 0) {
            return 0;
        }

        if(requests.length == 0) {
            return storage[0].length() * storage.length;
        }

        int answer = 0;

        strArr = new String[storage.length + 2][storage[0].length() + 2];
        for (int i = 0; i < strArr.length; i++) {
            if(i == 0 || i == strArr.length - 1) {
                Arrays.fill(strArr[i], "");
                continue;
            }
            for (int j = 0; j < strArr[0].length; j++) {
                if(j == 0 || j == strArr[0].length - 1) {
                    strArr[i][j] = "";
                    continue;
                }
                strArr[i][j] = String.valueOf(storage[i-1].charAt(j-1));
            }
        }


        for (String request: requests) {
            if(request.length() == 1) {
                // 지게차
                getOutItem(0,0,request);
            } else {
                // 크레인
                getAllItem(request);
            }

        }
        System.out.println("strArr = " + Arrays.deepToString(strArr));

        for (int i = 1; i < strArr.length - 1; i++) {
           answer += Math.toIntExact(Arrays.stream(strArr[i]).filter(y -> !y.isEmpty()).count());
        }

        return answer;
    }

    private static void getAllItem(String request) {
        String value = String.valueOf(request.charAt(0));
        for(int i = 1; i < strArr.length - 1; i++) {
            for (int j = 1; j < strArr[i].length - 1; j++) {
                if(strArr[i][j].equals(value)) {
                    strArr[i][j] = "";
                }
            }
        }
    }

    private static void getOutItem(int x, int y, String request) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[strArr.length][strArr[0].length];
        List<int[]> list = new ArrayList<>();
        visited[x][y] = true;
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            x = arr[0];
            y = arr[1];

            for (int i = 0; i < dirX.length; i++) {
                int newX = x + dirX[i];
                int newY = y + dirY[i];

                if(newX < 0 || newY < 0 || newX >= strArr.length || newY >= strArr[i].length) {
                    continue;
                }

                if(strArr[newX][newY].isEmpty() && !visited[newX][newY]) {
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                    continue;
                }

                if(strArr[newX][newY].equals(request) && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    list.add(new int[]{newX, newY});
                }
            }
        }

        for (int[] ints: list) {
            strArr[ints[0]][ints[1]] = "";
        }
    }
}
