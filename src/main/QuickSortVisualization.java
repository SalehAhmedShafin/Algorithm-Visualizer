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
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class QuickSortVisualization extends JPanel {
    private static final int ARRAY_SIZE = 100;
    private static final int BAR_WIDTH = 5;
    private static final int BAR_GAP = 1;
    private static final int FRAME_WIDTH = (ARRAY_SIZE * (BAR_WIDTH + BAR_GAP)) + 15;
    private static final int FRAME_HEIGHT = 500;
    private int[] data;
    private int low;
    private int high;
    private int pivot;
    private int pivotIndex;
    private int i;
    private int j;
    private boolean finished;

    public QuickSortVisualization() {
        data = generateRandomArray();
        low = 0;
        high = data.length - 1;
        pivot = data[high];
        pivotIndex = low;
        i = low;
        j = high - 1;
        finished = false;
        JFrame frame = new JFrame("Quick Sort Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        sort();
    }

    private int[] generateRandomArray() {
        int[] array = new int[ARRAY_SIZE];
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt(FRAME_HEIGHT);
        }
        return array;
    }

    private void sort() {
        Thread thread = new Thread(() -> {
            quickSort(low, high);
            finished = true;
        });
        thread.start();
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        pivot = data[high];
        pivotIndex = low;
        for (int i = low; i < high; i++) {
            if (data[i] <= pivot) {
                swap(i, pivotIndex);
                pivotIndex++;
            }
        }
        swap(high, pivotIndex);
        return pivotIndex;
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!finished) {
            for (int i = 0; i < data.length; i++) {
                g.setColor(Color.BLACK);
                g.fillRect(i * (BAR_WIDTH + BAR_GAP), FRAME_HEIGHT - data[i], BAR_WIDTH, data[i]);
            }
            g.setColor(Color.RED);
            g.fillRect(pivotIndex * (BAR_WIDTH + BAR_GAP), FRAME_HEIGHT - pivot, BAR_WIDTH, pivot);
            g.setColor(Color.BLUE);
            g.fillRect(i * (BAR_WIDTH + BAR_GAP), FRAME_HEIGHT - data[i], BAR_WIDTH, data[i]);
            g.setColor(Color.GREEN);
            g.fillRect(j * (BAR_WIDTH + BAR_GAP), FRAME_HEIGHT - data[j], BAR_WIDTH, data[j]);
        } else {
            for (int i = 0; i < data.length; i++) {
            g.setColor(Color.BLACK);
            g.fillRect(i * (BAR_WIDTH + BAR_GAP), FRAME_HEIGHT - data[i], BAR_WIDTH, data[i]);
        }
    }
}

}

