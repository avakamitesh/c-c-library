class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        int max = 0;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }

        long[] cnt = new long[max + 1];
        for (int d : diff) cnt[d]++;

        for (int d = max; d >= 1 && k > 0; d--) {
            if (cnt[d] == 0) continue;
            if (k >= cnt[d]) {
                k -= cnt[d];
                cnt[d - 1] += cnt[d];
                cnt[d] = 0;
            } else {
                cnt[d] -= k;
                cnt[d - 1] += k;
                k = 0;
            }
        }

        long result = 0;
        for (int d = 1; d <= max; d++) {
            result += cnt[d] * (long) d * d;
        }
        return result;
    }
}