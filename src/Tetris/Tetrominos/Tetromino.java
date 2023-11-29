package Tetris.Tetrominos;

import java.awt.Color;
import java.awt.Graphics2D;

import Tetris.Main.GamePanel;
import Tetris.Main.KeyHandler;

import Tetris.Main.PlayManager;

public class Tetromino {
    public Block block[] = new Block[4];
    public Block tempBlock[] = new Block[4];
    int autoDropCounter = 0;
    public int direction = 1;
    boolean leftCollision, rightCollision, bottomCollision;
    public boolean active = true;
    public boolean deactivating;
    int deactivateCounter = 0;

    public void create(Color color) {
        block[0] = new Block(color);
        block[1] = new Block(color);
        block[2] = new Block(color);
        block[3] = new Block(color);
        tempBlock[0] = new Block(color);
        tempBlock[1] = new Block(color);
        tempBlock[2] = new Block(color);
        tempBlock[3] = new Block(color);
    }

    public void setXY(int x, int y) {
    }

    public void updateXY(int direction) {
        checkRotationCollision();

        if (leftCollision == false && rightCollision == false && bottomCollision == false) {
            this.direction = direction;
            block[0].x = tempBlock[0].x;
            block[0].y = tempBlock[0].y;
            block[1].x = tempBlock[1].x;
            block[1].y = tempBlock[1].y;
            block[2].x = tempBlock[2].x;
            block[2].y = tempBlock[2].y;
            block[3].x = tempBlock[3].x;
            block[3].y = tempBlock[3].y;
        }
        KeyHandler.upPressed = false;
    }

    public void getDirection1() {
    }

    public void getDirection2() {
    }

    public void getDirection3() {
    }

    public void getDirection4() {
    }

    public void checkMovementCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        // Checking Static Block Collision
        checkStaticBlockCollision();

        // Check Frame Collision
        // Left Wall
        for (int i = 0; i < block.length; i++) {
            if (block[i].x == PlayManager.left_x) {
                leftCollision = true;
            }
        }

        // Right Wall
        for (int i = 0; i < block.length; i++) {
            if (block[i].x + Block.SIZE == PlayManager.right_x) {
                rightCollision = true;
            }
        }

        // Bottom Floor
        for (int i = 0; i < block.length; i++) {
            if (block[i].y + Block.SIZE == PlayManager.bottom_y) {
                bottomCollision = true;
            }
        }
    }

    public void checkRotationCollision() {
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        // Checking Static Block Collision
        checkStaticBlockCollision();

        // Check Frame Collision
        // Left Wall
        for (int i = 0; i < block.length; i++) {
            if (tempBlock[i].x < PlayManager.left_x) {
                leftCollision = true;
            }
        }

        // Right Wall
        for (int i = 0; i < block.length; i++) {
            if (tempBlock[i].x + Block.SIZE > PlayManager.right_x) {
                rightCollision = true;
            }
        }

        // Bottom Floor
        for (int i = 0; i < block.length; i++) {
            if (tempBlock[i].y + Block.SIZE > PlayManager.bottom_y) {
                bottomCollision = true;
            }
        }
    }

    private void checkStaticBlockCollision() {
        for (int i = 0; i < PlayManager.staticBlocks.size(); i++) {
            int targetX = PlayManager.staticBlocks.get(i).x;
            int targetY = PlayManager.staticBlocks.get(i).y;

            // Check Down
            for (int j = 0; j < block.length; j++) {
                if (block[j].y + Block.SIZE == targetY && block[j].x == targetX) {
                    bottomCollision = true;
                }
            }

            // Check Left
            for (int j = 0; j < block.length; j++) {
                if (block[j].x - Block.SIZE == targetX && block[j].y == targetY) {
                    leftCollision = true;
                }
            }

            // Check Right
            for (int j = 0; j < block.length; j++) {
                if (block[j].x + Block.SIZE == targetX && block[j].y == targetY) {
                    rightCollision = true;
                }
            }
        }
    }

    public void update() {
        if (deactivating) {
            deactivating();
        }

        if (KeyHandler.upPressed) {
            switch (direction) {
                case 1:
                    getDirection2();
                    break;
                case 2:
                    getDirection3();
                    break;
                case 3:
                    getDirection4();
                    break;
                case 4:
                    getDirection1();
                    break;
            }
            KeyHandler.upPressed = false;
            GamePanel.soundEffect.play(1, false);
        }

        checkMovementCollision();

        if (KeyHandler.leftPressed) {
            if (leftCollision == false) {
                block[0].x -= Block.SIZE;
                block[1].x -= Block.SIZE;
                block[2].x -= Block.SIZE;
                block[3].x -= Block.SIZE;
            }

            KeyHandler.leftPressed = false;
        }

        if (KeyHandler.downPressed) {
            if (bottomCollision == false) {
                block[0].y += Block.SIZE;
                block[1].y += Block.SIZE;
                block[2].y += Block.SIZE;
                block[3].y += Block.SIZE;
                autoDropCounter = 0;
            }

            KeyHandler.downPressed = false;
        }

        if (KeyHandler.rightPressed) {
            if (rightCollision == false) {
                block[0].x += Block.SIZE;
                block[1].x += Block.SIZE;
                block[2].x += Block.SIZE;
                block[3].x += Block.SIZE;
            }

            KeyHandler.rightPressed = false;
        }

        if (bottomCollision) {
            if (deactivating == false) {
                GamePanel.soundEffect.play(6, false);
            }
            deactivating = true;
        } else {
            autoDropCounter++;
            if (autoDropCounter == PlayManager.dropInterval) {
                block[0].y += Block.SIZE;
                block[1].y += Block.SIZE;
                block[2].y += Block.SIZE;
                block[3].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }
    }

    private void deactivating() {
        deactivateCounter++;

        if (deactivateCounter == 30) {
            deactivateCounter = 0;
            checkMovementCollision();

            if (bottomCollision) {
                active = false;
            }
        }
    }

    public void draw(Graphics2D g2) {
        int margin = 2;
        g2.setColor(block[0].color);
        g2.fillRect(block[0].x + margin, block[0].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(block[1].x + margin, block[1].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(block[2].x + margin, block[2].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(block[3].x + margin, block[3].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
    }
}