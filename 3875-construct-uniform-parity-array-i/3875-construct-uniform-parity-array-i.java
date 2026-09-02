class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int odd = 0, even = 0;
        for (int x : nums1) {
            if ((x & 1) != 0) odd++;
            else even++;
        }
        
        boolean canAllEven = (odd != 1);
        boolean canAllOdd = (odd != 0);
        
        return canAllEven || canAllOdd; 
    }
}