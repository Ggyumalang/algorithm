// 최대공약수 구하는 것을 재귀함수로 구현
package exercise.algorithm.zerobase.basic_math.recurrence;

public class Practice2 {

    public static void main(String[] args) {
        int a = 4;
        int b = 12;
        System.out.println("result = " + gcd(a,b));

    }

    private static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        
        return gcd(b, a % b);
    }
}
