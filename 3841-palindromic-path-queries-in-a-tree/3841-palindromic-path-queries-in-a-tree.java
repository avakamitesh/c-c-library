import java.util.*;

class Solution {
    int n, LOG;
    int[] bit, tin, tout, depth;
    int[][] up;

    void upd(int i, int v) {
        for (; i <= n + 1; i += i & -i) bit[i] ^= v;
    }

    int qry(int i) {
        int r = 0;
        for (; i > 0; i -= i & -i) r ^= bit[i];
        return r;
    }

    int lca(int a, int b) {
        if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
        int diff = depth[a] - depth[b];
        for (int k = 0; k < LOG; k++)
            if (((diff >> k) & 1) == 1) a = up[k][a];
        if (a == b) return a;
        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }
        return up[0][a];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        LOG = 1;
        while ((1 << LOG) < n) LOG++;

        int m = Math.max(1, 2 * (n - 1));
        int[] head = new int[n], nxt = new int[m], to = new int[m];
        Arrays.fill(head, -1);
        int ec = 0;
        for (int[] e : edges) {
            to[ec] = e[1]; nxt[ec] = head[e[0]]; head[e[0]] = ec++;
            to[ec] = e[0]; nxt[ec] = head[e[1]]; head[e[1]] = ec++;
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[LOG][n];
        char[] cs = s.toCharArray();

        int[] stack = new int[n];
        int[] it = new int[n];
        System.arraycopy(head, 0, it, 0, n);
        int sp = 0, timer = 0;
        stack[sp++] = 0;
        up[0][0] = 0;
        tin[0] = ++timer;
        while (sp > 0) {
            int v = stack[sp - 1];
            if (it[v] != -1) {
                int e = it[v];
                it[v] = nxt[e];
                int w = to[e];
                if (v != 0 && w == up[0][v]) continue;
                up[0][w] = v;
                depth[w] = depth[v] + 1;
                tin[w] = ++timer;
                stack[sp++] = w;
            } else {
                tout[v] = timer;
                sp--;
            }
        }

        for (int k = 1; k < LOG; k++)
            for (int v = 0; v < n; v++)
                up[k][v] = up[k - 1][up[k - 1][v]];

        bit = new int[n + 3];
        for (int v = 0; v < n; v++) {
            int mk = 1 << (cs[v] - 'a');
            upd(tin[v], mk);
            upd(tout[v] + 1, mk);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);
                if (cs[u] != c) {
                    int delta = (1 << (cs[u] - 'a')) ^ (1 << (c - 'a'));
                    cs[u] = c;
                    upd(tin[u], delta);
                    upd(tout[u] + 1, delta);
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);
                int mask = qry(tin[u]) ^ qry(tin[v]) ^ (1 << (cs[l] - 'a'));
                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }
}