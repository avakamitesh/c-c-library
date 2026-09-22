import java.util.*;

class Solution {
    private int k, n;
    private int[] total;      
    private int[][] cnt;     

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        total = new int[4 * n];
        cnt = new int[4 * n][];
        build(1, 0, n - 1, nums);

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0], val = queries[i][1], start = queries[i][2], x = queries[i][3];
            update(1, 0, n - 1, idx, val);
            int[] r = query(1, 0, n - 1, start, n - 1);
            res[i] = (r == null) ? 0 : r[x];
        }
        return res;
    }

    private void setLeaf(int node, int val) {
        int v = val % k;
        total[node] = v;
        int[] c = new int[k];
        c[v] = 1;
        cnt[node] = c;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            setLeaf(node, nums[l]);
            return;
        }
        int mid = (l + r) >>> 1;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        pull(node);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            setLeaf(node, val);
            return;
        }
        int mid = (l + r) >>> 1;
        if (idx <= mid) update(2 * node, l, mid, idx, val);
        else update(2 * node + 1, mid + 1, r, idx, val);
        pull(node);
    }

    private void pull(int node) {
        int[] a = cnt[2 * node], b = cnt[2 * node + 1];
        int ta = total[2 * node], tb = total[2 * node + 1];
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            if (a[i] != 0) res[i] += a[i];
        }
        for (int j = 0; j < k; j++) {
            if (b[j] != 0) {
                int nr = (int) (((long) ta * j) % k);
                res[nr] += b[j];
            }
        }
        cnt[node] = res;
        total[node] = (int) (((long) ta * tb) % k);
    }

    private int[] mergeArr(int[] a, int ta, int[] b, int tb, int[] outTotalHolder) {
        int[] res = new int[k];
        for (int i = 0; i < k; i++) if (a[i] != 0) res[i] += a[i];
        for (int j = 0; j < k; j++) {
            if (b[j] != 0) {
                int nr = (int) (((long) ta * j) % k);
                res[nr] += b[j];
            }
        }
        outTotalHolder[0] = (int) (((long) ta * tb) % k);
        return res;
    }

    private int[] queryTotal; 
    private int lastTotal;

    private int[] query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return null;
        if (ql <= l && r <= qr) {
            lastTotal = total[node];
            return cnt[node];
        }
        int mid = (l + r) >>> 1;
        int[] left = query(2 * node, l, mid, ql, qr);
        int tLeft = lastTotal;
        int[] right = query(2 * node + 1, mid + 1, r, ql, qr);
        int tRight = lastTotal;

        if (left == null) { lastTotal = tRight; return right; }
        if (right == null) { lastTotal = tLeft; return left; }

        int[] holder = new int[1];
        int[] merged = mergeArr(left, tLeft, right, tRight, holder);
        lastTotal = holder[0];
        return merged;
    }
}