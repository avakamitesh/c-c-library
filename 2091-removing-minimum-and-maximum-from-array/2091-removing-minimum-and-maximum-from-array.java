class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0, maxIdx = 0;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        
        int lo = Math.min(minIdx, maxIdx);
        int hi = Math.max(minIdx, maxIdx);
        
        int fromFront = hi + 1;
        
        int fromBack = n - lo;
        
        int mixed = (lo + 1) + (n - hi);
        
        return Math.min(fromFront, Math.min(fromBack, mixed));
    }
}