import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) red[e[0]].add(e[1]);
        for (int[] e : blueEdges) blue[e[0]].add(e[1]);

        int[][] dist = new int[n][2];
        for (int[] d : dist) Arrays.fill(d, -1);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        q.offer(new int[]{0, 1});
        dist[0][0] = 0;
        dist[0][1] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], color = cur[1];

            List<Integer>[] next = (color == 0) ? blue : red;

            for (int nei : next[node]) {
                if (dist[nei][1 - color] == -1) {
                    dist[nei][1 - color] = dist[node][color] + 1;
                    q.offer(new int[]{nei, 1 - color});
                }
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (dist[i][0] == -1 || dist[i][1] == -1) {
                res[i] = Math.max(dist[i][0], dist[i][1]);
            } else {
                res[i] = Math.min(dist[i][0], dist[i][1]);
            }
        }

        return res;
    }
}