import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + (j <= i - 1 ? comb[i - 1][j] : 0)) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);

        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        int size = list.size();
        if (size <= 2) return 1;

        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < size; i++) {
            int v = list.get(i);
            if (v < root) left.add(v);
            else right.add(v);
        }

        long ways = comb[size - 1][left.size()];
        ways = ways * dfs(left) % MOD;
        ways = ways * dfs(right) % MOD;
        return ways;
    }
}