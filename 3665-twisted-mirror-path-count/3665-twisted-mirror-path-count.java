class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;

        // r[i][j] = ways to arrive at (i,j) while moving right
        // d[i][j] = ways to arrive at (i,j) while moving down
        long[][] r = new long[m][n];
        long[][] d = new long[m][n];
        r[0][0] = 1; // starting point

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long fromR = r[i][j], fromD = d[i][j];
                if (fromR == 0 && fromD == 0) continue;

                if (grid[i][j] == 0) {
                    long total = (fromR + fromD) % MOD;
                    if (j + 1 < n) r[i][j + 1] = (r[i][j + 1] + total) % MOD;
                    if (i + 1 < m) d[i + 1][j] = (d[i + 1][j] + total) % MOD;
                } else {
                    if (i + 1 < m) d[i + 1][j] = (d[i + 1][j] + fromR) % MOD;
                    if (j + 1 < n) r[i][j + 1] = (r[i][j + 1] + fromD) % MOD;
                }
            }
        }

        if (grid[m - 1][n - 1] == 1) return 0;
        return (int) ((r[m - 1][n - 1] + d[m - 1][n - 1]) % MOD);
    }
}