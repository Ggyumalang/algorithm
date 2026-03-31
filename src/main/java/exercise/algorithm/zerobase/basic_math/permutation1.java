//Practice1
// 1,2,3,4 를 이용하여 세 자리 자연수 만들기 (순서 o, 중복 x)
// 방법 1
package exercise.algorithm.zerobase;

public class permutation1 {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        per(arr,0,4,3);
    }

    private static void per(int[] arr, int depth, int n, int r) {

        if(depth == r) {
            for (int i = 0; i < r; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            per(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }

    }

    private static void swap(int[] arr, int depth, int idx) {
        int tmp = arr[depth];
        arr[depth] = arr[idx];
        arr[idx] = tmp;
    }
}
