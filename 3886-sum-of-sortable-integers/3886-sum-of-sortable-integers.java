class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k == 0 && check(nums, k)) sum += k;
        }
        return sum;
    }

    private boolean check(int[] a, int k) {
        int n = a.length;
        int prevMax = Integer.MIN_VALUE;
        for (int s = 0; s < n; s += k) {
            int descents = 0;
            int mn = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
            for (int i = s; i < s + k; i++) {
                int next = (i + 1 < s + k) ? a[i + 1] : a[s]; // cyclic wrap
                if (a[i] > next) descents++;
                mn = Math.min(mn, a[i]);
                mx = Math.max(mx, a[i]);
            }
            if (descents > 1) return false;
            if (mn < prevMax) return false;
            prevMax = mx;
        }
        return true;
    }
}