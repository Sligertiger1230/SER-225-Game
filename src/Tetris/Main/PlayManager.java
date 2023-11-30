package Tetris.Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import Tetris.Tetrominos.Block;
import Tetris.Tetrominos.Tetromino;
import Tetris.Tetrominos.TetrominoI;
import Tetris.Tetrominos.TetrominoJ;
import Tetris.Tetrominos.TetrominoL;
import Tetris.Tetrominos.TetrominoO;
import Tetris.Tetrominos.TetrominoS;
import Tetris.Tetrominos.TetrominoT;
import Tetris.Tetrominos.TetrominoZ;

public class PlayManager {
    // Main Play Area
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    // Tetromino
    Tetromino currentTetromino;
    final int TETROMINO_START_X;
    final int TETROMINO_START_Y;
    Tetromino nextTetromino;
    final int TETROMINO_NEXT_X;
    final int TETROMINO_NEXT_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();

    // Others
    public static int dropInterval = 60;
    boolean gameOver;

    // Effect
    boolean effectCounterOn;
    int effectCounter;
    ArrayList<Integer> effectY = new ArrayList<>();

    // Scoring
    int level = 1;
    int lines;
    int score;

    public PlayManager() {
        // Main Play Area Frame
        left_x = (GamePanel.WIDTH / 2) - (WIDTH / 2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        // Tetromino Start Position
        TETROMINO_START_X = left_x + (WIDTH / 2) - Block.SIZE;
        TETROMINO_START_Y = top_y + Block.SIZE;

        // Tetromino Next Position
        TETROMINO_NEXT_X = right_x + 175;
        TETROMINO_NEXT_Y = top_y + 500;

        // Set Starting Tetromino
        currentTetromino = pickTetromino();
        currentTetromino.setXY(TETROMINO_START_X, TETROMINO_START_Y);

        // Set Next Tetromino
        nextTetromino = pickTetromino();
        nextTetromino.setXY(TETROMINO_NEXT_X, TETROMINO_NEXT_Y);
    }

    private Tetromino pickTetromino() {
        Tetromino tetromino = null;
        int i = new Random().nextInt(7);

        switch (i) {
            case 0:
                tetromino = new TetrominoL();
                break;
            case 1:
                tetromino = new TetrominoJ();
                break;
            case 2:
                tetromino = new TetrominoO();
                break;
            case 3:
                tetromino = new TetrominoI();
                break;
            case 4:
                tetromino = new TetrominoT();
                break;
            case 5:
                tetromino = new TetrominoZ();
                break;
            case 6:
                tetromino = new TetrominoS();
                break;
        }
        return tetromino;
    }

    public void update() {
        if (currentTetromino.active == false) {
            // adding current tetromino to staticBlocks if inactive
            staticBlocks.add(currentTetromino.block[0]);
            staticBlocks.add(currentTetromino.block[1]);
            staticBlocks.add(currentTetromino.block[2]);
            staticBlocks.add(currentTetromino.block[3]);

            // check for ended game
            if (currentTetromino.block[0].x == TETROMINO_START_X && currentTetromino.block[0].y == TETROMINO_START_Y) {
                // means that the current tetromino collided and couldn't move
                gameOver = true;
            }

            currentTetromino.deactivating = false;

            // replacing currentTetromino with nextTetromino
            currentTetromino = nextTetromino;
            currentTetromino.setXY(TETROMINO_START_X, TETROMINO_START_Y);
            nextTetromino = pickTetromino();
            nextTetromino.setXY(TETROMINO_NEXT_X, TETROMINO_NEXT_Y);

            // checks to see if the line can be deleted
            checkDelete();

        } else {
            currentTetromino.update();
        }
    }

    private void checkDelete() {
        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;

        while (x < right_x && y < bottom_y) {
            for (int i = 0; i < staticBlocks.size(); i++) {
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
                    // increase the count if there is a static block
                    blockCount++;
                }
            }

            x += Block.SIZE;

            if (x == right_x) {
                // seeing if the line is full (all 12 blocks)
                if (blockCount == 12) {
                    effectCounterOn = true;
                    effectY.add(y);

                    for (int i = staticBlocks.size() - 1; i > -1; i--) {
                        // remove all the blocks if full
                        if (staticBlocks.get(i).y == y) {
                            staticBlocks.remove(i);
                        }
                    }

                    lineCount++;
                    lines++;

                    // bringing the lines down once row is deleted
                    for (int i = 0; i < staticBlocks.size(); i++) {
                        if (staticBlocks.get(i).y < y) {
                            staticBlocks.get(i).y += Block.SIZE;
                        }
                    }
                }

                blockCount = 0;
                x = left_x;
                y += Block.SIZE;
            }
        }

        // Add Score
        if (lineCount > 0) {
            int singleLineScore = 10 * level;
            score += singleLineScore * lineCount;
        }
    }

    public void draw(Graphics2D g2) {
        // Draw Play Area Frame
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);

        // Draw Next Tetromino Frame
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x, y, 200, 200);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x + 60, y + 60);

        // Draw Score Area
        g2.drawRect(x, top_y, 250, 300);
        x += 40;
        y = top_y + 90;
        g2.drawString("LEVEL: " + level, x, y);
        y += 70;
        g2.drawString("LINES: " + lines, x, y);
        y += 70;
        g2.drawString("SCORE: " + score, x, y);

        // Draw Current Tetromino
        if (currentTetromino != null) {
            currentTetromino.draw(g2);
        }

        // Draw Next Tetromino
        nextTetromino.draw(g2);

        // Draw Static Blocks
        for (int i = 0; i < staticBlocks.size(); i++) {
            staticBlocks.get(i).draw(g2);
        }

        // Draw Effect
        if (effectCounterOn) {
            effectCounter++;

            g2.setColor(Color.red);
            for (int i = 0; i < effectY.size(); i++) {
                g2.fillRect(left_x, effectY.get(i), WIDTH, Block.SIZE);
            }

            if (effectCounter == 10) {
                effectCounterOn = false;
                effectCounter = 0;
                effectY.clear();
            }
        }

        // Draw Pause
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(50f));

        if (gameOver) {
            x = left_x + 25;
            y = top_y + 320;
            g2.setColor(Color.red);
            g2.drawString("GAME OVER", x, y);
        } else if (KeyHandler.pausePressed) {
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("PAUSED", x, y);
        }
    }
}