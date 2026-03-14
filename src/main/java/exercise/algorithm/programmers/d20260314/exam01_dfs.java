/**
 * 2025 프로그래머스 코드 챌린지 1차 예썬 > 지게차와 크레인
 */

package exercise.algorithm.programmers.d20260314;

import java.util.Arrays;

public class exam01_dfs {

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
                boolean[][] visited = new boolean[strArr.length][strArr[0].length];
                for (int i = 0; i < strArr[0].length; i++) {
                    getOutItem(request,0,i, visited);
                    getOutItem(request,strArr.length - 1,i, visited);
                }

                for (int i = 0; i < strArr.length; i++) {
                    getOutItem(request,i,0, visited);
                    getOutItem(request,i,strArr[0].length - 1, visited);
                }

                //getOutItem(request,0,0, visited);
                //System.out.println("0,0 = " + Arrays.deepToString(visited));
                //getOutItem(request,0,strArr[0].length - 1, visited);
                //System.out.println("0,len = " + Arrays.deepToString(visited));
                //getOutItem(request,strArr.length - 1,0, visited);
                //System.out.println("len,0 = " + Arrays.deepToString(visited));
                //getOutItem(request,strArr.length - 1,strArr[0].length - 1, visited);
                //System.out.println("len,len = " + Arrays.deepToString(visited));
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

    private static void getOutItem(String request, int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0; i < dirX.length; i++) {
            int newX = x + dirX[i];
            int newY = y + dirY[i];

            if(newX < 0 || newY < 0 || newX >= strArr.length || newY >= strArr[i].length) {
                continue;
            }

            if(strArr[newX][newY].isEmpty() && !visited[newX][newY]) {
                //System.out.println("newX = " + newX + " newY = " + newY);
                getOutItem(request,newX,newY, visited);
                continue;
            }

            if(strArr[newX][newY].equals(request) && !visited[newX][newY]) {
                strArr[newX][newY] = "";
                visited[newX][newY] = true;
                //System.out.println("newX = " + newX + " newY = " + newY + " request = " + request);
                return;
            }
        }
    }
}
