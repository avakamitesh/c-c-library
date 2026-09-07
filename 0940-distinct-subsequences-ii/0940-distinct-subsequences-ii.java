class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        
        int[] last = new int[26]; 
        
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            dp[i] = (2 * dp[i - 1]) % MOD;
            
            if (last[c - 'a'] != 0) {
                dp[i] = (dp[i] - dp[last[c - 'a'] - 1] + MOD) % MOD;
            }
            
            last[c - 'a'] = i;
        }
        
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}