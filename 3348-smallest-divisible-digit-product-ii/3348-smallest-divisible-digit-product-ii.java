import java.util.*;

class Solution {
    private int A, B, C, D;
    private int[][] digitExp = new int[10][4];
    private Map<Long, Integer> memo = new HashMap<>();

    public String smallestNumber(String num, long t) {   // <-- changed int -> long
        // ---- 1. Factor t = 2^A * 3^B * 5^C * 7^D ----
        long tt = t;
        while (tt % 2 == 0) { tt /= 2; A++; }
        while (tt % 3 == 0) { tt /= 3; B++; }
        while (tt % 5 == 0) { tt /= 5; C++; }
        while (tt % 7 == 0) { tt /= 7; D++; }
        if (tt != 1) return "-1";

        int[][] de = {
            {0,0,0,0}, // 0 unused
            {0,0,0,0}, // 1
            {1,0,0,0}, // 2
            {0,1,0,0}, // 3
            {2,0,0,0}, // 4
            {0,0,1,0}, // 5
            {1,1,0,0}, // 6
            {0,0,0,1}, // 7
            {3,0,0,0}, // 8
            {0,2,0,0}  // 9
        };
        digitExp = de;

        int n = num.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) digits[i] = num.charAt(i) - '0';

        int[][] pref = new int[n+1][4];
        pref[0] = new int[]{0,0,0,0};
        for (int i = 0; i < n; i++) {
            int dgt = digits[i];
            int[] e = (dgt >= 1 && dgt <= 9) ? digitExp[dgt] : new int[]{0,0,0,0};
            pref[i+1] = new int[]{
                Math.min(A, pref[i][0] + e[0]),
                Math.min(B, pref[i][1] + e[1]),
                Math.min(C, pref[i][2] + e[2]),
                Math.min(D, pref[i][3] + e[3])
            };
        }

        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) { firstZero = i; break; }
        }

        if (firstZero == n) {
            int[] e = pref[n];
            if (e[0] >= A && e[1] >= B && e[2] >= C && e[3] >= D) {
                return num;
            }
        }

        int maxI = Math.min(n - 1, firstZero);
        for (int i = maxI; i >= 0; i--) {
            int[] pe = pref[i];
            int ra = Math.max(0, A - pe[0]);
            int rb = Math.max(0, B - pe[1]);
            int rc = Math.max(0, C - pe[2]);
            int rd = Math.max(0, D - pe[3]);
            for (int dig = digits[i] + 1; dig <= 9; dig++) {
                int[] e = digitExp[dig];
                int na = Math.max(0, ra - e[0]);
                int nb = Math.max(0, rb - e[1]);
                int nc = Math.max(0, rc - e[2]);
                int nd = Math.max(0, rd - e[3]);
                int remLen = n - 1 - i;
                if (f(na, nb, nc, nd) <= remLen) {
                    String suffix = construct(remLen, na, nb, nc, nd);
                    return num.substring(0, i) + dig + suffix;
                }
            }
        }

        int L = Math.max(n + 1, f(A, B, C, D));
        return construct(L, A, B, C, D);
    }

    private int f(int a, int b, int c, int d) {
        if (a == 0 && b == 0 && c == 0 && d == 0) return 0;
        long key = ((long)a << 24) | ((long)b << 16) | ((long)c << 8) | d;
        Integer cached = memo.get(key);
        if (cached != null) return cached;

        int best = Integer.MAX_VALUE / 2;
        for (int dig = 1; dig <= 9; dig++) {
            int[] e = digitExp[dig];
            int na = Math.max(0, a - e[0]);
            int nb = Math.max(0, b - e[1]);
            int nc = Math.max(0, c - e[2]);
            int nd = Math.max(0, d - e[3]);
            if (na == a && nb == b && nc == c && nd == d) continue;
            int v = 1 + f(na, nb, nc, nd);
            if (v < best) best = v;
        }
        memo.put(key, best);
        return best;
    }

    private String construct(int L, int a, int b, int c, int d) {
        StringBuilder sb = new StringBuilder();
        int ca = a, cb = b, cc = c, cd = d;
        int rem = L;
        for (int step = 0; step < L; step++) {
            rem--;
            for (int dig = 1; dig <= 9; dig++) {
                int[] e = digitExp[dig];
                int na = Math.max(0, ca - e[0]);
                int nb = Math.max(0, cb - e[1]);
                int nc = Math.max(0, cc - e[2]);
                int nd = Math.max(0, cd - e[3]);
                if (f(na, nb, nc, nd) <= rem) {
                    sb.append((char) ('0' + dig));
                    ca = na; cb = nb; cc = nc; cd = nd;
                    break;
                }
            }
        }
        return sb.toString();
    }
}