class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);

        long multiple = k;
        while (set.contains((int) multiple)) {
            multiple += k;
        }
        return (int) multiple;
    }
}