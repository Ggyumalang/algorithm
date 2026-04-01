// 팩토리얼을 재귀함수로 구현
package exercise.algorithm.zerobase.basic_math.recurrence;

public class Practice1 {

    public static void main(String[] args) {
        int n = 4;
        System.out.println("result = " + factorial(n));

    }

    private static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }
}
