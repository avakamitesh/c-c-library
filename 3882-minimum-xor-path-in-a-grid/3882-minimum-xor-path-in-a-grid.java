class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int max = 0;
        for (int[] row : grid)
            for (int v : row) max = Math.max(max, v);
        int size = Integer.highestOneBit(Math.max(max, 1)) << 1;

        boolean[][] dp = new boolean[n][size];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[] cur = new boolean[size];
                int v = grid[i][j];

                if (i == 0 && j == 0) {
                    cur[v] = true;
                } else {
                    for (int x = 0; x < size; x++) {
                        boolean fromUp = i > 0 && dp[j][x];
                        boolean fromLeft = j > 0 && dp[j - 1][x];
                        if (fromUp || fromLeft) cur[x ^ v] = true;
                    }
                }
                dp[j] = cur;
            }
        }

        for (int x = 0; x < size; x++) {
            if (dp[n - 1][x]) return x;
        }
        return -1;
    }
}