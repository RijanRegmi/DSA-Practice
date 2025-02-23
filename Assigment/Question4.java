package Assigment;

import java.util.*;

public class Question4 {
    static class Edge {
        int u, v, cost;

        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    static class UnionFind {
        int[] parent, rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // Path compression
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY)
                return false;
            if (rank[rootX] > rank[rootY])
                parent[rootY] = rootX;
            else if (rank[rootX] < rank[rootY])
                parent[rootX] = rootY;
            else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    public static int minTotalCost(int n, int[] modules, int[][] connections) {
        List<Edge> edges = new ArrayList<>();

        // Add virtual edges (connecting each device to the dummy node 0)
        for (int i = 0; i < n; i++) {
            edges.add(new Edge(0, i + 1, modules[i]));
        }

        // Add real connections
        for (int[] conn : connections) {
            edges.add(new Edge(conn[0], conn[1], conn[2]));
        }

        // Sort edges by cost (for Kruskal's algorithm)
        edges.sort(Comparator.comparingInt(e -> e.cost));

        // Apply Kruskal's Algorithm
        UnionFind uf = new UnionFind(n + 1);
        int totalCost = 0, edgesUsed = 0;

        for (Edge edge : edges) {
            if (uf.union(edge.u, edge.v)) {
                totalCost += edge.cost;
                edgesUsed++;
                if (edgesUsed == n)
                    break; // MST should have (n) edges
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] modules = { 1, 2, 2 };
        int[][] connections = { { 1, 2, 1 }, { 2, 3, 1 } };

        System.out.println(minTotalCost(n, modules, connections)); // Output: 3
    }
}
