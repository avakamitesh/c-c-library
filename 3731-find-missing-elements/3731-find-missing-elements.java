import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        int mn = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
        
        for (int num : nums) {
            numSet.add(num);
            mn = Math.min(mn, num);
            mx = Math.max(mx, num);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = mn; i <= mx; i++) {
            if (!numSet.contains(i)) {
                result.add(i);
            }
        }
        
        return result;
    }
}