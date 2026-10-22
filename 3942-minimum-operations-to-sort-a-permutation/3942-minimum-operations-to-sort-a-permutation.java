class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        boolean inc = true, dec = true;
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) p = i;
            int next = nums[(i + 1) % n];
            if (next != (nums[i] + 1) % n) inc = false;
            if (next != (nums[i] - 1 + n) % n) dec = false;
        }

        int ans = Integer.MAX_VALUE;

        if (inc) {
            if (p == 0) ans = 0;
            else ans = Math.min(p, n - p + 2);
        }
        if (dec) {
            int q = n - 1 - p;          
                        int a = (p + 1) % n;        
            ans = Math.min(ans, Math.min(q + 1, a + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}