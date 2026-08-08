class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        int[] suf0 = new int[n + 1];
        suf0[n] = m;
        for (int i = n - 1; i >= 0; i--) {
            suf0[i] = suf0[i + 1];
            if (suf0[i] > 0 && w1[i] == w2[suf0[i] - 1]) {
                suf0[i]--;
            }
        }

        int[] suf1 = new int[n + 1];
        suf1[n] = m;
        for (int i = n - 1; i >= 0; i--) {
            int best = suf1[i + 1]; 

            int j = suf1[i + 1];
            if (j > 0 && w1[i] == w2[j - 1]) {
                best = Math.min(best, j - 1);
            }

            int j0 = suf0[i + 1];
            if (j0 > 0) { 
                best = Math.min(best, j0 - 1);
            }

            suf1[i] = best;
        }

        if (suf1[0] != 0) {
            return new int[0];
        }

        int[] result = new int[m];
        int idx = 0;
        int i = 0, j = 0, b = 1;

        while (j < m) {
            boolean placed = false;
            while (i < n) {
                char c = w1[i];
                if (c == w2[j]) {
                    int fut = (b == 1) ? suf1[i + 1] : suf0[i + 1];
                    if (fut <= j + 1) {
                        result[idx++] = i;
                        i++;
                        j++;
                        placed = true;
                        break;
                    }
                } else if (b == 1) {
                    if (suf0[i + 1] <= j + 1) {
                        result[idx++] = i;
                        i++;
                        j++;
                        b = 0;
                        placed = true;
                        break;
                    }
                }
                i++;
            }
            if (!placed) {
                return new int[0]; 
            }
        }

        return result;
    }
}