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


public class FoodFillVisualization extends JFrame {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final int CELL_SIZE = 20;
    private static final int GRID_WIDTH = FRAME_WIDTH / CELL_SIZE;
    private static final int GRID_HEIGHT = FRAME_HEIGHT / CELL_SIZE;
    private static final int ANIMATION_DELAY = 50;

    private final int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];
    private final JPanel panel;

    public FoodFillVisualization() {
        setTitle("Food Fill Algorithm Visualization");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < GRID_WIDTH; i++) {
                    for (int j = 0; j < GRID_HEIGHT; j++) {
                        if (grid[i][j] == 1) {
                            g.setColor(Color.GREEN);
                            g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        } else if (grid[i][j] == 2) {
                            g.setColor(Color.RED);
                            g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        }
                    }
                }
            }
        };

        getContentPane().add(panel);
        setVisible(true);
        fillGridRandomly();
        foodFill();
    }

    private void fillGridRandomly() {
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                grid[i][j] = Math.random() < 0.5 ? 0 : 1;
            }
        }
        repaint();
        sleep(ANIMATION_DELAY);
    }

    private void foodFill() {
        int[][] visited = new int[GRID_WIDTH][GRID_HEIGHT];
        int foodCount = 0;

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                if (grid[i][j] == 0 && visited[i][j] == 0) {
                    foodCount++;
                    dfs(i, j, visited, foodCount);
                }
            }
        }
    }

    private void dfs(int i, int j, int[][] visited, int foodCount) {
        visited[i][j] = foodCount;
        grid[i][j] = 2;
        repaint();
        sleep(ANIMATION_DELAY);

        int[][] neighbors = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] neighbor : neighbors) {
            int x = i + neighbor[0];
            int y = j + neighbor[1];
            if (isValid(x, y) && grid[x][y] == 0 && visited[x][y] == 0) {
                dfs(x, y, visited, foodCount);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT;
    }

    private void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
}

