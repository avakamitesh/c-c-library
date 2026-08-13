class Solution {

    static class Node {
        char leftChar;
        char rightChar;
        int prefix;
        int suffix;
        int best;
        int len;

        Node(char ch) {
            leftChar = ch;
            rightChar = ch;
            prefix = 1;
            suffix = 1;
            best = 1;
            len = 1;
        }
    }

    Node[] tree;

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();

        tree = new Node[4 * n];

        build(1, 0, n - 1, s);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            ans[i] = tree[1].best;
        }

        return ans;
    }

    private void build(int node, int l, int r, String s) {
        if (l == r) {
            tree[node] = new Node(s.charAt(l));
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(
            int node,
            int l,
            int r,
            int idx,
            char ch) {

        if (l == r) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = l + (r - l) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node left, Node right) {

        Node res = new Node(left.leftChar);

        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;
        res.len = left.len + right.len;

        res.best = Math.max(left.best, right.best);

        res.prefix = left.prefix;

        if (left.prefix == left.len &&
                left.rightChar == right.leftChar) {

            res.prefix = left.len + right.prefix;
        }

        res.suffix = right.suffix;

        if (right.suffix == right.len &&
                left.rightChar == right.leftChar) {

            res.suffix = right.len + left.suffix;
        }

        if (left.rightChar == right.leftChar) {
            res.best = Math.max(
                    res.best,
                    left.suffix + right.prefix
            );
        }

        return res;
    }
}