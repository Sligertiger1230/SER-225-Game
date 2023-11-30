package Tetris.Tetrominos;

import java.awt.Color;

public class TetrominoI extends Tetromino {
    public TetrominoI() {
        create(Color.cyan);
    }

    public void setXY(int x, int y) {
        block[0].x = x;
        block[0].y = y;
        block[1].x = block[0].x - Block.SIZE;
        block[1].y = block[0].y;
        block[2].x = block[0].x + Block.SIZE;
        block[2].y = block[0].y;
        block[3].x = block[0].x + Block.SIZE * 2;
        block[3].y = block[0].y;
    }

    public void getDirection1() {
        tempBlock[0].x = block[0].x;
        tempBlock[0].y = block[0].y;
        tempBlock[1].x = block[0].x - Block.SIZE;
        tempBlock[1].y = block[0].y;
        tempBlock[2].x = block[0].x + Block.SIZE;
        tempBlock[2].y = block[0].y;
        tempBlock[3].x = block[0].x + Block.SIZE * 2;
        tempBlock[3].y = block[0].y;
        updateXY(1);
    }

    public void getDirection2() {
        tempBlock[0].x = block[0].x;
        tempBlock[0].y = block[0].y;
        tempBlock[1].x = block[0].x;
        tempBlock[1].y = block[0].y - Block.SIZE;
        tempBlock[2].x = block[0].x;
        tempBlock[2].y = block[0].y + Block.SIZE;
        tempBlock[3].x = block[0].x;
        tempBlock[3].y = block[0].y + Block.SIZE * 2;
        updateXY(2);
    }

    public void getDirection3() {
        getDirection1();
    }

    public void getDirection4() {
        getDirection2();
    }
}