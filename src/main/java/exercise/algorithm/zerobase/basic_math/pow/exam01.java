/**
 * 지수와 로그 - 제곱 및 제곱근을 직접 구하는 방법
 */

package exercise.algorithm.zerobase.basic_math.pow;

public class exam01 {

    public static void main(String[] args) {
        System.out.println("== Math pow ==");
        int a = 2;
        int b = 3;
        System.out.println("pow = " + pow(a,b));
        a = 2;
        b = -3;
        System.out.println("pow = " + pow(a,b));

        a = -2;
        b = -3;
        System.out.println("pow = " + pow(a,b));

        System.out.println("== Math sqrt ==");
        a = 16;
        System.out.println(sqrt(a));

        a = 8;
        System.out.println(sqrt(a));
    }

    static double pow(int a, double b) {
        double result = 1;
        boolean isMinus = false;

        if( b == 0 ) {
            return 1;
        } else if ( b < 0 ) {
            b *= -1;
            isMinus = true;
        }

        for (int i = 0; i < b; i++) {
            result *= a;
        }

        return isMinus ? 1 / result : result;
    }

    static double sqrt(double a) {
        double result = 1;

        //바빌로니아 방법
        for (int i = 0; i < 10 ; i++) {
            result = (result + (a / result)) / 2;
        }

        return result;
    }
}
