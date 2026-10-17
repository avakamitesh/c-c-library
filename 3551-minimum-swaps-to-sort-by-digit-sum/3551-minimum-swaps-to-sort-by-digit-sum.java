import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2]; 
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            int da = digitSum(a[0]), db = digitSum(b[0]);
            if (da != db) return Integer.compare(da, db);
            return Integer.compare(a[0], b[0]);
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || arr[i][1] == i) continue;

            int cycleLen = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j][1];
                cycleLen++;
            }
            swaps += cycleLen - 1;
        }

        return swaps;
    }

    private int digitSum(int x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}