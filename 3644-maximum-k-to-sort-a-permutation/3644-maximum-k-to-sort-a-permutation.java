class Solution {
    public int sortPermutation(int[] nums) {
        int k = -1; 
        boolean sorted = true;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                k &= nums[i];
                sorted = false;
            }
        }

        return sorted ? 0 : k;
    }
}