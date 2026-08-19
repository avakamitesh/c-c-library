import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();
        
        for (int[] rs : reservedSeats) {
            int row = rs[0];
            int seat = rs[1];
            if (seat >= 2 && seat <= 9) {
                int bit = seat - 2; 
                int mask = rowMask.getOrDefault(row, 0);
                mask |= (1 << bit);
                rowMask.put(row, mask);
            }
        }
        
        final int LEFT  = 0b00001111; // seats 2-5
        final int MID   = 0b00111100; // seats 4-7
        final int RIGHT = 0b11110000; // seats 6-9
        
        long total = 0;
        
        for (int mask : rowMask.values()) {
            if ((mask & LEFT) == 0 && (mask & RIGHT) == 0) {
                total += 2;
            } else if ((mask & MID) == 0) {
                total += 1;
            } else if ((mask & LEFT) == 0 || (mask & RIGHT) == 0) {
                total += 1;
            }
        }
        
        long untouchedRows = n - rowMask.size();
        total += untouchedRows * 2L;
        
        return (int) total;
    }
}