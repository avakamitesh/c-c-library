import java.util.BitSet;

class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (((m + n - 1) & 1) == 1) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        int maxBal = (m + n) / 2 + 1;
        BitSet[] row = new BitSet[n];
        for (int j = 0; j < n; j++) row[j] = new BitSet(maxBal + 2);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                BitSet cur = new BitSet(maxBal + 2);

                if (i == 0 && j == 0) {
                    cur.set(0);              
                } else {
                    if (i > 0) cur.or(row[j]);        
                    if (j > 0) cur.or(row[j - 1]);    
                }

                BitSet next = new BitSet(maxBal + 2);
                if (grid[i][j] == '(') {
                    for (int b = cur.nextSetBit(0); b >= 0; b = cur.nextSetBit(b + 1)) {
                        if (b + 1 <= maxBal) next.set(b + 1);
                    }
                } else {
                    for (int b = cur.nextSetBit(0); b >= 0; b = cur.nextSetBit(b + 1)) {
                        if (b - 1 >= 0) next.set(b - 1);
                    }
                }
                row[j] = next;
            }
        }
        return row[n - 1].get(0);
    }
}