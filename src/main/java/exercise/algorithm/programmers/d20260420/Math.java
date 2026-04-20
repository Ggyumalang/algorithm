package exercise.algorithm.programmers.d20260420;

public class Math {
    public static void main(String[] args) {
        int[] arr = {1300, 1500, 1600, 4900};
        int[] disc = {40, 40, 20, 40};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" [ " + arr[i] * (100 - disc[i]) / 100 + " ]");
        }
    }
}
