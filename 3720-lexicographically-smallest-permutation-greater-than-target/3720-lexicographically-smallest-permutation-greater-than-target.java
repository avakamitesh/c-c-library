class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int pos = 0;
        while (pos < n && cnt[target.charAt(pos) - 'a'] > 0) {
            cnt[target.charAt(pos) - 'a']--;
            pos++;
        }

        int startI;
        if (pos == n) {
            startI = n - 1;
            cnt[target.charAt(n - 1) - 'a']++;
        } else {
            startI = pos; 
        }

        for (int i = startI; i >= 0; i--) {
            int tChar = target.charAt(i) - 'a';
            int found = -1;
            for (int c = tChar + 1; c < 26; c++) {
                if (cnt[c] > 0) { found = c; break; }
            }

            if (found != -1) {
                cnt[found]--;
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i);
                sb.append((char) ('a' + found));
                for (int c = 0; c < 26; c++)
                    for (int k = 0; k < cnt[c]; k++)
                        sb.append((char) ('a' + c));
                return sb.toString();
            }

            if (i > 0) {
                cnt[target.charAt(i - 1) - 'a']++;
            }
        }

        return "";
    }
}