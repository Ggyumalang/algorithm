package exercise.algorithm.zerobase.algorithm.divide;

public class exam01 {

    public static void main(String[] args) {
        int arr[] = {3, 5, 10 , 50, 25, 30, 1, 15};
        System.out.println(getMax(arr, 0, arr.length - 1));
    }

    private static int getMax(int[] arr, int left, int right) {
        int m = (left + right) / 2;
        if(left == right) return arr[left];

        left = getMax(arr, left, m);
        right = getMax(arr, m + 1, right);

        return Math.max(left, right);
    }
}
