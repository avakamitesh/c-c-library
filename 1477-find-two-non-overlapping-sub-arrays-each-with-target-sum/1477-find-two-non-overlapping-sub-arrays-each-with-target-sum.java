class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        
        int ans = Integer.MAX_VALUE;
        int left = 0, sum = 0;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            best[right] = (right > 0) ? best[right - 1] : Integer.MAX_VALUE;
            
            if (sum == target) {
                int curLen = right - left + 1;
                
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, curLen + best[left - 1]);
                }
                
                best[right] = Math.min(best[right], curLen);
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}