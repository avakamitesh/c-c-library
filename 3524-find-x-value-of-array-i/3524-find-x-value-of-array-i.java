class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k]; 

        for (int num : nums) {
            int m = num % k;
            long[] next = new long[k];

            next[m]++;

            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    next[(r * m) % k] += dp[r];
                }
            }

            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }

            dp = next;
        }

        return result;
    }
}