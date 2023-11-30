package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

    private final int gameWidth = 300;
    private final int gameHeight = 300;
    private final int dotSize = 10;
    private final int totalDots = 900;
    private final int randomPos = 29;
    private final int delay = 140;

    private final int x[] = new int[totalDots];
    private final int y[] = new int[totalDots];

    private int dots;
    private int food_x;
    private int food_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer time;
    private Image body;
    private Image food;
    private Image head;

    public Game() {
        initializeBoard();
    }

    private void initializeBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        loadImages();
        initializeGame();
    }

    private void loadImages() {
        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        body = iid.getImage();
        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        food = iia.getImage();
        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    private void initializeGame() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        locateApple();
        time = new Timer(delay, this);
        time.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(food, food_x, food_y, this);
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(body, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String gameOver = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = getFontMetrics(font);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(gameOver, (gameWidth - fontMetrics.stringWidth(gameOver)) / 2, gameHeight / 2);
    }

    private void checkApple() {
        if ((x[0] == food_x) && (y[0] == food_y)) {
            dots++;
            locateApple();
        }
    }

    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (leftDirection) {
            x[0] -= dotSize;
        }
        if (rightDirection) {
            x[0] += dotSize;
        }
        if (upDirection) {
            y[0] -= dotSize;
        }
        if (downDirection) {
            y[0] += dotSize;
        }
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }
        if (y[0] >= gameHeight) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] >= gameWidth) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            time.stop();
        }
    }

    private void locateApple() {
        int i = (int) (Math.random() * randomPos);
        food_x = ((i * dotSize));
        i = (int) (Math.random() * randomPos);
        food_y = ((i * dotSize));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}