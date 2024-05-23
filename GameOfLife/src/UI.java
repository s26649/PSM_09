import javax.swing.*;

// This file is new - previous UI was renamed to GameView
// This file will aggregate and control multiple smaller UI elements
public class UI extends JFrame {
    GameView gameView;

    public UI(int size, String liveRules, String birthRules) {
        // Initializing UI, extracted from psvm in old UI (now GameView) class (second half)
        JFrame frame = new JFrame("Game of Life");
        gameView = new GameView(size, liveRules, birthRules);
        frame.add(gameView);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Timer timer = new Timer(100, e -> {
            gameView.game.nextGeneration();
            gameView.repaint();
        });
        timer.start();
    }
}
