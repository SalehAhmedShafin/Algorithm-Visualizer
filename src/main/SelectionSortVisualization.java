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
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectionSortVisualization extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int ARRAY_SIZE = 100;
    private static final int DELAY = 50;

    private int[] arr = new int[ARRAY_SIZE];
    private int currentMinIndex = 0;
    private int currentSwapIndex = 0;

    public SelectionSortVisualization() {
        JFrame frame = new JFrame();
        frame.setTitle("Selection Sort Algorithm Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);

        initializeArray();
        selectionSort();
    }

    private void initializeArray() {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(HEIGHT);
        }
    }

    private void selectionSort() {
        Thread t = new Thread(() -> {
            for (int i = 0; i < arr.length - 1; i++) {
                currentMinIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] < arr[currentMinIndex]) {
                        currentMinIndex = j;
                    }
                }
                swap(i, currentMinIndex);
            }
        });
        t.start();
    }

    private void swap(int i, int j) {
        currentSwapIndex = j;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        repaint();
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < arr.length; i++) {
            if (i == currentMinIndex) {
                g.setColor(Color.YELLOW);
            } else if (i == currentSwapIndex) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillRect(i * (WIDTH / arr.length), HEIGHT - arr[i], WIDTH / arr.length, arr[i]);
        }
    }
}