import java.util.Arrays; 
  class Solution {
    private int[] prefix;
    private int[][] memo;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        return dp(0, n - 1);
    }

    private int sum(int i, int j) {
        return prefix[j + 1] - prefix[i];
    }

    private int dp(int i, int j) {
        if (i >= j) return 0; 
        if (memo[i][j] != -1) return memo[i][j];

        int best = 0;
        for (int k = i; k < j; k++) {
            int leftSum = sum(i, k);
            int rightSum = sum(k + 1, j);

            int score;
            if (leftSum < rightSum) {
                score = leftSum + dp(i, k);
            } else if (leftSum > rightSum) {
                score = rightSum + dp(k + 1, j);
            } else {
                score = leftSum + Math.max(dp(i, k), dp(k + 1, j));
            }
            best = Math.max(best, score);
        }

        return memo[i][j] = best;
    }
}