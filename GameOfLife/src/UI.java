import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class UI extends JPanel {
    private final int size;
    private final GameOfLife game;

    public UI(int size, String liveRules, String birthRules) {
        this.size = size;
        this.game = new GameOfLife(size, liveRules, birthRules);
        this.game.initializeRandom();
        Timer timer = new Timer(100, e -> {
            game.nextGeneration();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = Math.min(getWidth(), getHeight()) / size;
        int[][] grid = game.getGrid();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == 1) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }
    }
    
}