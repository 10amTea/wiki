package com.mark.leetcode.stack.everyday._2021_07;

import java.util.*;

/**
 * @author Mark
 * @date 2021-07-25 17:55
 */
public class _25_RestoreArray {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] adjacentPair : adjacentPairs) {
            if (!map.containsKey(adjacentPair[0])) {
                map.put(adjacentPair[0], new ArrayList<>());
            }
            map.get(adjacentPair[0]).add(adjacentPair[1]);
            if (!map.containsKey(adjacentPair[1])) {
                map.put(adjacentPair[1], new ArrayList<>());
            }
            map.get(adjacentPair[1]).add(adjacentPair[0]);
        }
        Deque<Integer> deque = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        outer:
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                deque.add(entry.getKey());
                visited.add(entry.getKey());
                for (Integer next : entry.getValue()) {
                    if (!visited.contains(next)) {
                        deque.add(next);
                        visited.add(next);
                        dfs(map, visited, next, deque);
                        break outer;
                    }
                }
            }
        }
        int[] res = new int[deque.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = deque.remove();
        }
        return res;
    }

    public void dfs(Map<Integer, List<Integer>> map, Set<Integer> visited, int node, Deque<Integer> deque) {
        for (Integer next : map.get(node)) {
            if (!visited.contains(next)) {
                deque.add(next);
                visited.add(next);
                dfs(map, visited, next, deque);
            }
        }
    }
}
