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
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CountingSortVisualization extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int ARRAY_SIZE = 100;
    private static final int DELAY = 50;

    private int[] arr = new int[ARRAY_SIZE];
    private int[] count = new int[HEIGHT];

    public CountingSortVisualization() {
        JFrame frame = new JFrame();
        frame.setTitle("Counting Sort Algorithm Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);

        initializeArray();
        countingSort();
    }

    private void initializeArray() {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(HEIGHT);
        }
    }

    private void countingSort() {
        Thread t = new Thread(() -> {
            for (int i = 0; i < arr.length; i++) {
                count[arr[i]]++;
            }
            int index = 0;
            for (int i = 0; i < count.length; i++) {
                for (int j = 0; j < count[i]; j++) {
                    arr[index] = i;
                    index++;
                    repaint();
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < arr.length; i++) {
            g.setColor(Color.BLACK);
            g.fillRect(i * (WIDTH / arr.length), HEIGHT - arr[i], WIDTH / arr.length, arr[i]);
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                g.setColor(Color.RED);
                g.fillRect(i * (WIDTH / count.length), HEIGHT - count[i], WIDTH / count.length, count[i]);
            }
        }
    }

   
}

