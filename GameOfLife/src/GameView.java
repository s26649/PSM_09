import javax.swing.*;
import java.awt.*;

// This file was renamed from UI to GameView
// New ui components might appear (start menu, parameters selection etc.)
// so this file will now be responsible only for drawing main view of the game
public class GameView extends JPanel {
    private final int size;
    protected final GameOfLife game;

    public GameView(int size, String liveRules, String birthRules) {
        this.size = size;
        this.game = new GameOfLife(size, liveRules, birthRules);
        this.game.initializeRandom();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = Math.min(getWidth(), getHeight()) / size;
        int[][] grid = game.getGrid();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == 1) {
                    g.setColor(new Color(60, 16, 235));
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }
    }
}