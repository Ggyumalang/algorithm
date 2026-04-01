package exercise.algorithm.zerobase.basic_math.recurrence;

public class Main {

    static int recursion(int n) {
        if( n == 1) {
            return 1;
        }

        return 3 * recursion(n - 1);
    }

    static int recursion2(int n) {
        if( n == 1) {
            return 1;
        }

        return n + recursion2(n - 1);
    }

    static int recursion3(int n) {
        if( n == 1 || n == 2) {
            return 1;
        }

        return recursion3(n - 2) + recursion3(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("== 점화식 / 재귀함수 연습 1");

        // 1,3,9,27,...의 n 번째 수
        int n = 4;
        int result = 1;

        for (int i = 1; i < n; i++) {
            result *= 3;
        }

        System.out.println("result = " + result);
        System.out.println("recursion = " + recursion(n));

        System.out.println("== 점화식 / 재귀함수 연습 2");
        // 1,2,3,4,5,6 ... 의 n 번째까지의 합
        n = 5;
        result = 0;
        for (int i = 1; i <= n; i++) {
                result += i;
        }

        System.out.println("result = " + result);
        System.out.println("recursion2 = " + recursion2(n));

        System.out.println("== 점화식 / 재귀함수 연습 3");
        // 1, 1, 2, 3, 5, 8, 13 의 n 번째 수
        n = 6;
        int a1 = 1;
        int a2 = 1;
        result = a2;

        for (int i = 2; i < n; i++) {
            result = a1 + a2;
            a1 = a2;
            a2 = result;
        }

        System.out.println("result = " + result);
        System.out.println("recursion3 = " + recursion3(n));
    }
}
