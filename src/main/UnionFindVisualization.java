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
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class UnionFindVisualization extends JFrame {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final int CELL_SIZE = 20;
    private static final int GRID_WIDTH = FRAME_WIDTH / CELL_SIZE;
    private static final int GRID_HEIGHT = FRAME_HEIGHT / CELL_SIZE;
    private static final int ANIMATION_DELAY = 100;

    private final int[] id = new int[GRID_WIDTH * GRID_HEIGHT];
    private final int[] size = new int[GRID_WIDTH * GRID_HEIGHT];
    private final JPanel panel;

    public UnionFindVisualization() {
        setTitle("Union Find Algorithm Visualization");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < GRID_WIDTH; i++) {
                    for (int j = 0; j < GRID_HEIGHT; j++) {
                        int p = i * GRID_WIDTH + j;
                        int root = find(p);
                        int x = root / GRID_WIDTH;
                        int y = root % GRID_WIDTH;
                        g.setColor(Color.BLACK);
                        g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        if (size[root] > 1) {
                            g.setColor(Color.GREEN);
                            g.fillRect(y * CELL_SIZE + 2, x * CELL_SIZE + 2, CELL_SIZE - 3, CELL_SIZE - 3);
                        } else {
                            g.setColor(Color.RED);
                            g.fillRect(y * CELL_SIZE + 2, x * CELL_SIZE + 2, CELL_SIZE - 3, CELL_SIZE - 3);
                        }
                    }
                }
            }
        };

        getContentPane().add(panel);
        setVisible(true);
        initializeGrid();
        unionFind();
    }

    private void initializeGrid() {
        Arrays.fill(size, 1);
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
        repaint();
        sleep(ANIMATION_DELAY);
    }

    private void unionFind() {
        int count = 0;

        while (count < GRID_WIDTH * GRID_HEIGHT) {
            int p = (int) (Math.random() * GRID_WIDTH * GRID_HEIGHT);
            int q = (int) (Math.random() * GRID_WIDTH * GRID_HEIGHT);

            if (!connected(p, q)) {
                union(p, q);
                count++;
            }
        }
    }

    private int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        repaint();
        sleep(ANIMATION_DELAY);
    }

    private boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
