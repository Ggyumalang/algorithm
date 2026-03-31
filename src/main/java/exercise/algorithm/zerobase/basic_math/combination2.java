// 조합
// 1,2,3,4 를 이용하여 세 자리 자연수를 만드는 방법 (순서 x, 중복 x) 의 결과 출력
package exercise.algorithm.zerobase;

public class combination2 {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        boolean[] visited = new boolean[arr.length];
        combinations(arr,visited,0,4,3);
    }

    private static void combinations(int[] arr, boolean[] visited, int depth, int n, int r) {

        if(r == 0) {
            for (int i = 0; i < n; i++) {
                if(visited[i]) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
            return;
        }

        if(depth == n) {
            return;
        }

        visited[depth] = true;
        combinations(arr,visited,depth+1,n,r-1);
        visited[depth] = false;
        combinations(arr,visited,depth+1,n,r);
    }
}
