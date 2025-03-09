package Assigment.Five;

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.AbstractMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class Dijkstra {
    public static Map<String, Integer> shortestPath(Graph graph, String startNode) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue);
        Map<String, String> previousNodes = new HashMap<>();

        for (String node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(startNode, 0);
        pq.add(new AbstractMap.SimpleEntry<>(startNode, 0));

        while (!pq.isEmpty()) {
            String currentNode = pq.poll().getKey();

            for (Map.Entry<String, Integer> neighbor : graph.getNeighbors(currentNode).entrySet()) {
                int newDist = distances.get(currentNode) + neighbor.getValue();
                if (newDist < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), newDist);
                    pq.add(new AbstractMap.SimpleEntry<>(neighbor.getKey(), newDist));
                    previousNodes.put(neighbor.getKey(), currentNode);
                }
            }
        }
        return distances;
    }
}
