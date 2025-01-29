import java.util.*;

class AdjList {
    private Map<String, List<Edge>> adjacencyList;

    // Constructor
    public AdjList() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a node (location)
    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    // Add an edge (connection) with weight
    public void addEdge(String node1, String node2, int weight) {
        adjacencyList.get(node1).add(new Edge(node2, weight));
        adjacencyList.get(node2).add(new Edge(node1, weight)); // Omit for directed graph
    }

    // Display the graph
    public void display() {
        for (Map.Entry<String, List<Edge>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (Edge edge : entry.getValue()) {
                System.out.print("(" + edge.node + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    // Nested class for edges
    static class Edge {
        String node;
        int weight;

        Edge(String node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    // Main method
    public static void main(String[] args) {
        AdjList graph = new AdjList();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 3);

        graph.display();
    }
}
