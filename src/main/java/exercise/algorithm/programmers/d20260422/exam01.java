/**
 * 압축
 */

package exercise.algorithm.programmers.d20260422;

import java.util.*;

public class exam01 {
    public static void main(String[] args) {
        String msg = "KAKAO";
        System.out.println(Arrays.toString(solution2(msg)));

        msg = "TOBEORNOTTOBEORTOBEORNOT";
        System.out.println(Arrays.toString(solution2(msg)));

        msg = "ABABABABABABABAB";
        System.out.println(Arrays.toString(solution2(msg)));
    }

    public static int[] solution2(String msg) {

        if(msg.isEmpty()) return new int[0];
        if(msg.length() == 1) return new int[]{msg.charAt(0) - 'A' + 1};


        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int dictIdx = 27;
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            map.put( c + "", c - 'A' + 1);
        }

        // 초기화를 한다.
        int idx = 0;
        while (idx < msg.length()) {
            StringBuilder word = new StringBuilder();
            
            // 사전에 존재하는 가장 긴 단어 찾기
            // 다음 글자를 붙여봐서 있으면 w를 갱신하고 idx 를 1 증가 시킨다.
            while (idx < msg.length() && map.containsKey(word.toString() + msg.charAt(idx))) {
                word.append(msg.charAt(idx++));
            }
            
            // 찾은 가장 긴 단어의 인덱스 번호 정답에 출력
            answer.add(map.get(word.toString()));

            // 아직 문자열이 끝나지 않았다면, 방금 찾은 단어에 다음 글자를 붙여서 사전에 등록
            if (idx < msg.length()) {
                map.put(word.toString() + msg.charAt(idx), dictIdx++);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
