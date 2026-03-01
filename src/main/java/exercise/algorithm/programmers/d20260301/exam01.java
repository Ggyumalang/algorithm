/**
 * 완전탐색 > 소수찾기
 */

package exercise.algorithm.programmers.d20260301;

import java.util.*;

public class exam01 {

    static Set<Integer> answerSet;

    public static void main(String[] args) {
        String numbers = "17";
        System.out.println(solution(numbers));
        System.out.println(solution2(numbers));

        numbers = "011";
        System.out.println(solution(numbers));
        System.out.println(solution2(numbers));
    }

    public static int solution(String numbers) {
        String[] numbersArray = new String[numbers.length()];
        answerSet = new HashSet<>();

        for (int i = 0; i < numbers.length(); i++) {
            numbersArray[i] = numbers.substring(i, i + 1);
        }

        for (int i = 0; i < numbersArray.length; i++) {
            if(numbersArray[i].equals("0")) {
                continue;
            }
            List<String> list = new  ArrayList<>();
            for (int j = 0; j < numbersArray.length; j++) {
                if(i==j) {
                    continue;
                }

                list.add(numbersArray[j]);
            }
            int startNum = Integer.parseInt(numbersArray[i]);
            if(isPrime(startNum)) {
                answerSet.add(startNum);
            }
            dfs(list, startNum);
        }

        System.out.println("answerSet = " + answerSet);
        return answerSet.size();
    }

    private static void dfs(List<String> numbers, int startNum) {
        if((numbers.size() == 1 && numbers.getFirst().equals("0")) || numbers.isEmpty()) {
            return;
        }

        for (int i = 0; i < numbers.size(); i++) {
            int newNum = Integer.parseInt(startNum + numbers.get(i));
            List<String> list = new  ArrayList<>();
            for (int j = 0; j < numbers.size(); j++) {
                if(i==j) {
                    continue;
                }

                list.add(numbers.get(j));
            }

            if(isPrime(newNum)) {
                answerSet.add(newNum);
            }

            dfs(list, newNum);
        }
    }

    private static boolean isPrime(int n) {
        if( n <= 1) {
            return false;
        }

        if( n == 2 ) {
            return true;
        }

        for (int i = 3; i <= (int)Math.sqrt(n); i+=2) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int solution2(String numbers) {
        HashSet<Integer> set = new HashSet<>();
        permutation("", numbers, set);
        System.out.println("set = " + set);
        int answer = 0;

        while (set.iterator().hasNext()) {
            int num = set.iterator().next();
            set.remove(num);
            if(num == 2) answer++;
            if(num % 2 != 0 && isPrime(num)) {
                answer++;
            }
        }
        return answer;
    }

    public static void permutation(String prefix, String numbers, HashSet<Integer> set) {
        int len =  numbers.length();
        if(!prefix.isEmpty()) {
            set.add(Integer.parseInt(prefix));
        }
        for (int i = 0; i < len; i++) {
            // i 에 해당하는 수 제외 하고 다시..
            permutation(prefix + numbers.charAt(i), numbers.substring(0,i) + numbers.substring(i+1, len), set);
        }
    }

}
