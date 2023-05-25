/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Asus
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javax.swing.Box;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopologicalSortVisualizer extends JFrame {
    private Graph graph;
    private JPanel graphPanel;
    private JPanel sortedPanel;

    public TopologicalSortVisualizer() {
        setTitle("Topological Sort Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the graph and add nodes and edges
        graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        // Create the graph panel and add it to the frame
        graphPanel = new GraphPanel();
        add(graphPanel, BorderLayout.CENTER);

        // Create the sorted panel and add it to the frame
        sortedPanel = new JPanel();
        sortedPanel.setPreferredSize(new Dimension(200, 600));
        sortedPanel.setBackground(Color.WHITE);
        add(sortedPanel, BorderLayout.EAST);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TopologicalSortVisualizer();
    }

    // Class to represent the graph data structure
    private class Graph {
        private int V;
        private List<List<Integer>> adjList;

        public Graph() {
            V = 8;
            adjList = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
        }

        public List<Integer> topologicalSort() {
            List<Integer> sorted = new ArrayList<>();
            int[] inDegree = new int[V];
            for (int i = 0; i < V; i++) {
                for (int j : adjList.get(i)) {
                    inDegree[j]++;
                }
            }
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < V; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
            while (!queue.isEmpty()) {
                int u = queue.poll();
                sorted.add(u);
                for (int v : adjList.get(u)) {
                    if (--inDegree[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
            return sorted;
        }
    }

    // Class to represent the graph panel
    private class GraphPanel extends JPanel {
        private int nodeWidth = 50;
        private int nodeHeight = 50;

        public GraphPanel() {
            setPreferredSize(new Dimension(400, 600));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the nodes
            for (int i = 0; i < graph.V; i++) {
                int x = (i % 3) * 100 + 50;
                            int y = (i / 3) * 100 + 50;
            g.setColor(Color.RED);
            g.fillOval(x, y, nodeWidth, nodeHeight);
            g.setColor(Color.WHITE);
            g.drawOval(x, y, nodeWidth, nodeHeight);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i), x + nodeWidth/2, y + nodeHeight/2);
        }

        // Draw the edges
        for (int i = 0; i < graph.V; i++) {
            for (int j : graph.adjList.get(i)) {
                int x1 = (i % 3) * 100 + 50 + nodeWidth/2;
                int y1 = (i / 3) * 100 + 50 + nodeHeight/2;
                int x2 = (j % 3) * 100 + 50 + nodeWidth/2;
                int y2 = (j / 3) * 100 + 50 + nodeHeight/2;
                g.setColor(Color.BLACK);
                g.drawLine(x1, y1, x2, y2);
            }
        }

        // Perform the topological sort and display the sorted nodes
        List<Integer> sorted = graph.topologicalSort();
        int y = 50;
        for (int i : sorted) {
            JLabel nodeLabel = new JLabel(String.valueOf(i));
            nodeLabel.setOpaque(true);
            nodeLabel.setBackground(Color.RED);
            nodeLabel.setForeground(Color.WHITE);
            nodeLabel.setHorizontalAlignment(JLabel.CENTER);
            nodeLabel.setPreferredSize(new Dimension(100, 50));
            sortedPanel.add(nodeLabel);
            sortedPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }
 }
}
        



