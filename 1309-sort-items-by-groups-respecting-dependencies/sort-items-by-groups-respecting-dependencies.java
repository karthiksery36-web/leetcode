import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) group[i] = m++;
        }

        List<Integer>[] itemGraph = new ArrayList[n];
        List<Integer>[] groupGraph = new ArrayList[m];
        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) itemGraph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) groupGraph[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph[prev].add(i);
                itemIndegree[i]++;
                if (group[i] != group[prev]) {
                    groupGraph[group[prev]].add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);
        if (groupOrder.size() == 0) return new int[0];

        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        if (itemOrder.size() == 0) return new int[0];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int g : groupOrder) map.put(g, new ArrayList<>());

        for (int item : itemOrder) {
            map.get(group[item]).add(item);
        }

        List<Integer> res = new ArrayList<>();
        for (int g : groupOrder) {
            res.addAll(map.get(g));
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topoSort(List<Integer>[] graph, int[] indegree, int n) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);
            for (int nei : graph[cur]) {
                if (--indegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        return res.size() == n ? res : new ArrayList<>();
    }
}