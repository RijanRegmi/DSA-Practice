package Assigment.Five;

import java.util.*;

class Graph {
    private final Map<String, Map<String, Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new HashMap<>());
    }

    public void addEdge(String node1, String node2, int cost) {
        adjacencyList.get(node1).put(node2, cost);
        adjacencyList.get(node2).put(node1, cost);
    }

    public Map<String, Integer> getNeighbors(String node) {
        return adjacencyList.get(node);
    }

    public Set<String> getNodes() {
        return adjacencyList.keySet();
    }
}
