class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        long total = 0;
        for (int num : nums) total += num;

        long target = total - x;
        if (target < 0) return -1;
        if (target == 0) return n;

        int left = 0;
        long windowSum = 0;
        int maxLen = -1;

        for (int right = 0; right < n; right++) {
            windowSum += nums[right];

            while (windowSum > target && left <= right) {
                windowSum -= nums[left++];
            }

            if (windowSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : n - maxLen;
    }
}