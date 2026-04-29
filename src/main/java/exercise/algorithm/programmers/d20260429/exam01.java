/**
 * 문자열 압축
 */

package exercise.algorithm.programmers.d20260429;

public class exam01 {

    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.println(solution(s));

        s = "ababcdcdababcdcd";
        System.out.println(solution(s));

        s = "abcabcdede";
        System.out.println(solution(s));

        s = "abcabcabcabcdededededede";
        System.out.println(solution(s));

        s = "xababcdcdababcdcd";
        System.out.println(solution(s));

        s = "aacaabdda"; //2aacdda
        System.out.println(solution(s));

        s = "aaa"; //2aacdda
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int answer = s.length();
        // n 개 단위로 계속 잘라본다. 단 n 은 s 의 절반까지만 해보는 거다.
        // i 는 단어를 자르는 규모이고, idx 는 인덱스, len 은 길이!
        for (int i = 1; i <= s.length()/2; i++) {
            int idx = i;
            int len = s.length();
            int sameNumCnt = 1;
            String str = s.substring(0, i);

            while (idx + i <= s.length()) {
                String newStr = s.substring(idx, idx + i);
                // 만약 같다면 i 만큼 길이에서 빼준다.
                if(str.equals(newStr)) {
                    sameNumCnt++;

                    if(sameNumCnt == 2) {
                        len = len - i + 1;
                    } else {
                        // 3번 이상 반복 시
                        len -= i;

                        //자릿수 변경 시 마다 len 업
                        if(sameNumCnt == 10 || sameNumCnt == 100 || sameNumCnt == 1000) {
                            len++;
                        }
                    }
                } else {
                    // 다르다면?
                    sameNumCnt = 1;
                    str = newStr;
                }

                idx = idx + i;
            }

            answer = Math.min(answer, len);
        }
        return answer;
    }
}
