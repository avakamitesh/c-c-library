class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int ans = 0;
        
        for (int right = 0; right < nums.length; right++) {
            int x = nums[right];
            freq.put(x, freq.getOrDefault(x, 0) + 1);
            
            while (freq.get(x) > k) {
                int leftVal = nums[left];
                freq.put(leftVal, freq.get(leftVal) - 1);
                left++;
            }
            
            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}