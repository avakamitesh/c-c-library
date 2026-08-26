class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        int minLen = Integer.MAX_VALUE;
        
        int left = 0, ones = 0;
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') ones++;
            
            while (ones > k || (ones == k && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1') ones--;
                left++;
            }
            
            if (ones == k) {
                int len = right - left + 1;
                String candidate = s.substring(left, right + 1);
                if (len < minLen) {
                    minLen = len;
                    result = candidate;
                } else if (len == minLen && candidate.compareTo(result) < 0) {
                    result = candidate;
                }
            }
        }
        
        return result;
    }
}