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
                    g.setColor(new Color(60, 16, 235));
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rozmiar siatki:");
        int size = scanner.nextInt();
        System.out.println("Zasady dla zycia (np. 23):");
        String liveRules = scanner.next();
        System.out.println("Zasady dla narodzin (np. 3):");
        String birthRules = scanner.next();

        JFrame frame = new JFrame("Game of Life");
        UI ui = new UI(size, liveRules, birthRules);
        frame.add(ui);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}