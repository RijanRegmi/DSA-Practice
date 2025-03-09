package Assigment.Five;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class NetworkOptimizerGUI extends JFrame {
    private final Graph networkGraph;
    private final JTextArea outputArea;

    public NetworkOptimizerGUI() {
        networkGraph = new Graph();
        setTitle("Network Optimizer");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JButton addNodeButton = new JButton("Add Node");
        JButton addEdgeButton = new JButton("Add Edge");
        JButton calculatePathButton = new JButton("Calculate Path");
        outputArea = new JTextArea(15, 50);

        panel.add(addNodeButton);
        panel.add(addEdgeButton);
        panel.add(calculatePathButton);
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        addNodeButton.addActionListener(e -> addNode());
        addEdgeButton.addActionListener(e -> addEdge());
        calculatePathButton.addActionListener(e -> calculateShortestPath());
    }

    private void addNode() {
        String nodeName = JOptionPane.showInputDialog("Enter node name:");
        if (nodeName != null) {
            networkGraph.addNode(nodeName);
            outputArea.append("Node added: " + nodeName + "\n");
        }
    }

    private void addEdge() {
        String node1 = JOptionPane.showInputDialog("Enter first node:");
        String node2 = JOptionPane.showInputDialog("Enter second node:");
        String costStr = JOptionPane.showInputDialog("Enter cost:");

        if (node1 != null && node2 != null && costStr != null) {
            int cost = Integer.parseInt(costStr);
            networkGraph.addEdge(node1, node2, cost);
            outputArea.append("Edge added between " + node1 + " and " + node2 + " with cost " + cost + "\n");
        }
    }

    private void calculateShortestPath() {
        String startNode = JOptionPane.showInputDialog("Enter start node:");
        if (startNode != null) {
            Map<String, Integer> distances = Dijkstra.shortestPath(networkGraph, startNode);
            outputArea.append("Shortest Paths from " + startNode + ":\n");
            for (Map.Entry<String, Integer> entry : distances.entrySet()) {
                outputArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NetworkOptimizerGUI().setVisible(true));
    }
}
