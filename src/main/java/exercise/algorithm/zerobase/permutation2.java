//Practice1
// 1,2,3,4 를 이용하여 세 자리 자연수 만들기 (순서 o, 중복 x)
// 방법 2
package exercise.algorithm.zerobase;

import java.util.Arrays;

public class permutation2 {

    public static void main(String[] args) {
        int n = 4;
        int r = 3;
        int[] arr = {1,2,3,4};
        boolean[] visited = new boolean[n];
        int[] out = new int[n];
        per(arr,0,n,r,visited,out);
    }

    private static void per(int[] arr, int depth, int n, int r, boolean[] visited, int[] out) {
        if(depth == r) {
            System.out.println("out = " + Arrays.toString(out));
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                per(arr, depth + 1, n, r, visited, out);
                visited[i] = false;
            }
        }
    }

}
