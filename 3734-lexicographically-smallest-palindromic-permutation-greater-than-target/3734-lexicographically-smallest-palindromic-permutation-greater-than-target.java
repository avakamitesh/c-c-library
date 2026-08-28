class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] baseCnt = new int[26];
        for (int i = 0; i < n; i++) baseCnt[s.charAt(i) - 'a']++;

        int odd = 0;
        for (int v : baseCnt) if ((v & 1) == 1) odd++;
        if ((n % 2 == 0 && odd != 0) || (n % 2 == 1 && odd != 1)) return "";

        int H = (n + 1) / 2;
        int[] cnt = baseCnt.clone();
        char[] B = new char[H];
        int[] stack = new int[H];
        int top = -1;
        int failAt = -1;

        for (int k = 0; k < H; k++) {
            int qty = (n % 2 == 1 && k == H - 1) ? 1 : 2;
            int need = target.charAt(k) - 'a';
            int chosen = -1;
            for (int c = need; c < 26; c++) {
                if (cnt[c] >= qty) { chosen = c; break; }
            }
            if (chosen == -1) { failAt = k; break; }

            cnt[chosen] -= qty;
            B[k] = (char) ('a' + chosen);

            if (chosen == need) {
                stack[++top] = k;
            } else {
                fillSmallest(B, cnt, k + 1, H, n);
                return new String(buildFull(B, n));
            }
        }

        if (failAt == -1) {
            char[] A = buildFull(B, n);
            String cand = new String(A);
            if (cand.compareTo(target) > 0) return cand;
        }

        while (top >= 0) {
            int j = stack[top--];
            int qty = (n % 2 == 1 && j == H - 1) ? 1 : 2;

            int oldChar = B[j] - 'a';
            cnt[oldChar] += qty;

            int need = target.charAt(j) - 'a';
            int chosen = -1;
            for (int c = need + 1; c < 26; c++) {
                if (cnt[c] >= qty) { chosen = c; break; }
            }
            if (chosen == -1) continue;

            cnt[chosen] -= qty;
            B[j] = (char) ('a' + chosen);
            fillSmallest(B, cnt, j + 1, H, n);
            return new String(buildFull(B, n));
        }

        return "";
    }

    private void fillSmallest(char[] B, int[] cnt, int start, int H, int n) {
        for (int k = start; k < H; k++) {
            int qty = (n % 2 == 1 && k == H - 1) ? 1 : 2;
            int chosen = -1;
            for (int c = 0; c < 26; c++) {
                if (cnt[c] >= qty) { chosen = c; break; }
            }
            cnt[chosen] -= qty;
            B[k] = (char) ('a' + chosen);
        }
    }

    private char[] buildFull(char[] B, int n) {
        char[] A = new char[n];
        int H = B.length;
        for (int i = 0; i < H; i++) A[i] = B[i];
        int half = n / 2;
        for (int i = 0; i < half; i++) A[n - 1 - i] = B[i];
        return A;
    }
}