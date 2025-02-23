package Assigment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Edge {
    int src, dest;
    double cost, bandwidth;

    public Edge(int src, int dest, double cost, double bandwidth) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
        this.bandwidth = bandwidth;
    }
}

class Graph {
    List<Edge> edges = new ArrayList<>();
    Map<Integer, List<Edge>> adjList = new HashMap<>();

    void addEdge(int src, int dest, double cost, double bandwidth) {
        Edge e = new Edge(src, dest, cost, bandwidth);
        edges.add(e);
        adjList.computeIfAbsent(src, k -> new ArrayList<>()).add(e);
        adjList.computeIfAbsent(dest, k -> new ArrayList<>()).add(e);
    }
}
