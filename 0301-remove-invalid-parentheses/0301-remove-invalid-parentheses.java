import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) leftRem--;
                else rightRem++;
            }
        }

        Set<String> result = new HashSet<>();
        dfs(s, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void dfs(String s, int idx, int open, int leftRem, int rightRem,
                     StringBuilder sb, Set<String> result) {
        if (idx == s.length()) {
            if (open == 0 && leftRem == 0 && rightRem == 0) {
                result.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(idx);
        int len = sb.length();

        if (c == '(' && leftRem > 0) {
            dfs(s, idx + 1, open, leftRem - 1, rightRem, sb, result);
        } else if (c == ')' && rightRem > 0) {
            dfs(s, idx + 1, open, leftRem, rightRem - 1, sb, result);
        }

        sb.append(c);
        if (c != '(' && c != ')') {
            dfs(s, idx + 1, open, leftRem, rightRem, sb, result);
        } else if (c == '(') {
            dfs(s, idx + 1, open + 1, leftRem, rightRem, sb, result);
        } else if (open > 0) {
            dfs(s, idx + 1, open - 1, leftRem, rightRem, sb, result);
        }
        sb.setLength(len); 
    }
}