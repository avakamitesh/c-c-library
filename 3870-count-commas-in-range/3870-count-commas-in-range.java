class Solution {
    public int countCommas(int n) {
        int total = 0;
        int low = 1;
        long pow = 10; 
        int d = 1;
        while (low <= n) {
            long high = Math.min(n, pow - 1);
            int commas = (d - 1) / 3;
            long count = high - low + 1;
            total += commas * count;
            low = (int) pow;
            pow *= 10;
            d++;
            if (low <= 0) break; 
        }
        return total;
    }
}