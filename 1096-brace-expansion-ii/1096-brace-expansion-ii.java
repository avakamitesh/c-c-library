import java.util.*;

class Solution {
    private int pos;
    private String expr;

    public List<String> braceExpansionII(String expression) {
        this.expr = expression;
        this.pos = 0;
        Set<String> result = parseExpr();
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    private Set<String> parseExpr() {
        Set<String> current = new TreeSet<>();
        current.add("");

        while (pos < expr.length() && expr.charAt(pos) != ',' && expr.charAt(pos) != '}') {
            Set<String> termSet = parseTerm();
            current = concat(current, termSet);
        }
        return current;
    }

    private Set<String> parseTerm() {
        if (expr.charAt(pos) == '{') {
            pos++;
            Set<String> union = new TreeSet<>();
            union.addAll(parseExpr());
            while (pos < expr.length() && expr.charAt(pos) == ',') {
                pos++;
                union.addAll(parseExpr());
            }
            pos++;
            return union;
        } else {
            int start = pos;
            while (pos < expr.length() && Character.isLetter(expr.charAt(pos))) {
                pos++;
            }
            Set<String> single = new TreeSet<>();
            single.add(expr.substring(start, pos));
            return single;
        }
    }

    private Set<String> concat(Set<String> a, Set<String> b) {
        Set<String> result = new TreeSet<>();
        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }
        return result;
    }
}