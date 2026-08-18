class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int maxStart = n - k;

        Map<Integer, List<int[]>> intervals = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - k + 1);
            int right = Math.min(i, maxStart);
            intervals.computeIfAbsent(nums[i], x -> new ArrayList<>())
                      .add(new int[]{left, right});
        }

        int ans = -1;
        for (Map.Entry<Integer, List<int[]>> entry : intervals.entrySet()) {
            List<int[]> ivs = entry.getValue();
            ivs.sort((a, b) -> a[0] - b[0]);

            int total = 0;
            int curStart = ivs.get(0)[0], curEnd = ivs.get(0)[1];
            for (int idx = 1; idx < ivs.size(); idx++) {
                int[] iv = ivs.get(idx);
                if (iv[0] <= curEnd + 1) {
                    curEnd = Math.max(curEnd, iv[1]);
                } else {
                    total += curEnd - curStart + 1;
                    curStart = iv[0];
                    curEnd = iv[1];
                }
            }
            total += curEnd - curStart + 1;

            if (total == 1) {
                ans = Math.max(ans, entry.getKey());
            }
        }

        return ans;
    }
}