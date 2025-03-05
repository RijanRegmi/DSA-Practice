package Assigment;

import java.util.*;

public class MinimumRoadTraversal {
    public static int minRoadsToCollectPackages(int[] packages, int[][] roads) {
        int n = packages.length;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        Set<Integer> packageLocations = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (packages[i] == 1) {
                packageLocations.add(i);
            }
        }

        Set<Integer> essentialNodes = new HashSet<>();
        for (int loc : packageLocations) {
            Queue<int[]> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            queue.add(new int[] { loc, 0 });
            visited.add(loc);

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int node = current[0], dist = current[1];

                if (dist <= 2) {
                    essentialNodes.add(node);
                }

                if (dist < 2) {
                    for (int neighbor : graph.get(node)) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.add(new int[] { neighbor, dist + 1 });
                        }
                    }
                }
            }
        }

        return findMinimumTraversal(graph, essentialNodes);
    }

    private static int findMinimumTraversal(List<List<Integer>> graph, Set<Integer> essentialNodes) {
        if (essentialNodes.isEmpty())
            return 0;

        int startNode = essentialNodes.iterator().next();
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(new int[] { startNode, 0 });
        visited.add(startNode);

        int totalRoads = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0], steps = current[1];

            for (int neighbor : graph.get(node)) {
                if (essentialNodes.contains(neighbor) && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new int[] { neighbor, steps + 1 });
                    totalRoads++;
                }
            }
        }

        return totalRoads * 2;
    }

    public static void main(String[] args) {
        int[] packages1 = { 1, 0, 0, 0, 0, 1 };
        int[][] roads1 = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        System.out.println(minRoadsToCollectPackages(packages1, roads1));

        int[] packages2 = { 0, 0, 0, 1, 1, 0, 0, 1 };
        int[][] roads2 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 5, 6 }, { 5, 7 } };
        System.out.println(minRoadsToCollectPackages(packages2, roads2));
    }
}
