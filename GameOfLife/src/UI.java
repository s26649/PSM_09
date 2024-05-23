import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// This file is new - previous UI was renamed to GameView
// This file will aggregate and control multiple smaller UI elements
public class UI extends JFrame {
    GameView gameView;
    Timer timer;
    boolean isRunning = true;

    public UI(int size, String liveRules, String birthRules) {
        // Initializing UI, extracted from psvm in old UI (now GameView) class (second half)
        setTitle("Game of Life");
        gameView = new GameView(size, liveRules, birthRules);
        add(gameView);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        timer = new Timer(100, e -> {
            gameView.game.nextGeneration();
            gameView.repaint();
        });
        initListeners();
        timer.start();
    }

    private void initListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    // P -> pause
                    case KeyEvent.VK_P -> {
                        if (isRunning) {
                            timer.stop();
                            isRunning = false;
                        } else {
                            timer.start();
                            isRunning = true;
                        }
                    }
                    // SPACE -> pause (hold)
                    case KeyEvent.VK_SPACE -> {
                        timer.stop();
                        isRunning = false;
                    }
                    // R -> restart
                    case KeyEvent.VK_R -> gameView.game.initializeRandom();
                    // UP ARROW -> faster
                    case KeyEvent.VK_UP -> timer.setDelay(Math.max(timer.getDelay() - 10, 10));
                    // DOWN ARROW -> slower
                    case KeyEvent.VK_DOWN -> timer.setDelay(Math.min(timer.getDelay() + 10, 1000));
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    timer.start();
                    isRunning = true;
                }
            }
        });
    }
}
