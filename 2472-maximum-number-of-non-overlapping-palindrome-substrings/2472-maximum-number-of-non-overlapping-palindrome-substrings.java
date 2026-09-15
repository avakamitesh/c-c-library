class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                }
            }
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; 
            if (i - k >= 0 && isPal[i - k][i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }

            if (i - k - 1 >= 0 && isPal[i - k - 1][i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }

        return dp[n];
    }
}