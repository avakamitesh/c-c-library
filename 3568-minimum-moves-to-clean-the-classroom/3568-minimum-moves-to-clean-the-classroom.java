import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1;
        int startC = -1;
        int litterCount = 0;

        int[][] litterIndex = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(litterIndex[i], -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterIndex[i][j] = litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int fullMask = (1 << litterCount) - 1;

        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{startR, startC, 0, energy});
        visited[startR][startC][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                int r = current[0];
                int c = current[1];
                int mask = current[2];
                int remainingEnergy = current[3];

                if (mask == fullMask) {
                    return moves;
                }

                if (remainingEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    char cell = classroom[nr].charAt(nc);

                    if (cell == 'X') {
                        continue;
                    }

                    int nextEnergy = remainingEnergy - 1;
                    int nextMask = mask;

                    if (cell == 'L') {
                        nextMask |= (1 << litterIndex[nr][nc]);
                    }

                    if (cell == 'R') {
                        nextEnergy = energy;
                    }

                    if (!visited[nr][nc][nextMask][nextEnergy]) {
                        visited[nr][nc][nextMask][nextEnergy] = true;
                        queue.offer(new int[]{
                                nr,
                                nc,
                                nextMask,
                                nextEnergy
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}