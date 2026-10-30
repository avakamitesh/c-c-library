import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) g[e[0]].add(new int[]{e[1], e[2]});

        long INF = Long.MAX_VALUE;
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) Arrays.fill(row, INF);

        // {distance, node, run}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1], run = (int) cur[2];
            if (d > dist[u][run]) continue;

            for (int[] nxt : g[u]) {
                int v = nxt[0];
                long nd = d + nxt[1];
                int nr;
                if (labels.charAt(v) == labels.charAt(u)) {
                    nr = run + 1;
                    if (nr > k) continue;
                } else {
                    nr = 1;
                }
                if (nd < dist[v][nr]) {
                    dist[v][nr] = nd;
                    pq.offer(new long[]{nd, v, nr});
                }
            }
        }

        long ans = INF;
        for (int r = 1; r <= k; r++) ans = Math.min(ans, dist[n - 1][r]);
        return ans == INF ? -1 : (int) ans;
    }
}