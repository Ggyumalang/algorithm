// 알고리즘 - 백트래킹
// N Queen 문제
package exercise.algorithm.zerobase.algorithm.backtracking;

public class exam01 {

    public static void main(String[] args) {
        System.out.println(nQueen(0));
    }

    static int n = 4;
    static int[] board = new int[n];
    static int cnt;

    public static int nQueen(int row) {
        if(row == n) {
            cnt++;
            for (int i = 0; i < n; i++) {
                System.out.print(board[i] + " ");
            }
            System.out.println();
            return cnt;
        }

        for (int i = 0; i < n; i++) {
            board[row] = i;

            // promising
            if(isPromising(row)) {
                nQueen(row + 1);
            }
        }
        return cnt;
    }

    public static boolean isPromising(int row) {
        for (int i = 0; i < row; i++) {
            // 같은 열인지? 같은 대각선인지?
            if(board[row] == board[i] || row - i == Math.abs(board[row] - board[i])) {
                return false;
            }
        }
        return true;
    }
}
