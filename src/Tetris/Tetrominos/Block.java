package Tetris.Tetrominos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Rectangle {
    public int x, y;
    public static final int SIZE = 30;
    public Color color;

    public Block(Color color) {
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        int margin = 2;
        g2.setColor(color);
        g2.fillRect(x + margin, y + margin, SIZE - (margin * 2), SIZE - (margin * 2));
    }
}