import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;

        Arrays.sort(order, (a, b) -> intervals.get(a).get(1) - intervals.get(b).get(1));

        int[] sortedL = new int[n];
        int[] sortedR = new int[n];
        int[] sortedW = new int[n];
        int[] origIdx = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> iv = intervals.get(order[i]);
            sortedL[i] = iv.get(0);
            sortedR[i] = iv.get(1);
            sortedW[i] = iv.get(2);
            origIdx[i] = order[i];
        }

        long[][] dpScore = new long[n + 1][5];
        @SuppressWarnings("unchecked")
        List<Integer>[][] dpList = new List[n + 1][5];
        for (int j = 0; j <= 4; j++) {
            dpScore[0][j] = 0;
            dpList[0][j] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            dpScore[i][0] = 0;
            dpList[i][0] = new ArrayList<>();

            int l = sortedL[i - 1];
            int p = lowerBound(sortedR, 0, i - 1, l);

            for (int j = 1; j <= 4; j++) {
                long skipScore = dpScore[i - 1][j];
                List<Integer> skipList = dpList[i - 1][j];

                long takeScore = dpScore[p][j - 1] + sortedW[i - 1];
                List<Integer> takeList = new ArrayList<>(dpList[p][j - 1]);
                insertSorted(takeList, origIdx[i - 1]);

                if (isBetter(takeScore, takeList, skipScore, skipList)) {
                    dpScore[i][j] = takeScore;
                    dpList[i][j] = takeList;
                } else {
                    dpScore[i][j] = skipScore;
                    dpList[i][j] = skipList;
                }
            }
        }

        List<Integer> ans = dpList[n][4];
        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) result[i] = ans.get(i);
        return result;
    }

    private int lowerBound(int[] arr, int lo, int hi, int target) {
        int l = lo, r = hi;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private void insertSorted(List<Integer> list, int val) {
        int idx = 0;
        while (idx < list.size() && list.get(idx) < val) idx++;
        list.add(idx, val);
    }

    private boolean isBetter(long scoreA, List<Integer> listA, long scoreB, List<Integer> listB) {
        if (scoreA != scoreB) return scoreA > scoreB;
        int m = Math.min(listA.size(), listB.size());
        for (int i = 0; i < m; i++) {
            int va = listA.get(i), vb = listB.get(i);
            if (va != vb) return va < vb;
        }
        return listA.size() < listB.size();
    }
}