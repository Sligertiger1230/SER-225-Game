package Screens;

import Engine.*;
import GameObject.Sprite;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class WinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont instructions;
    protected Sprite winImage;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;

    public WinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
        winImage = new Sprite(ImageLoader.load("pixil-frame-0_480.png"));
    }

    @Override
    public void initialize() {
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
        winMessage = new SpriteFont("You graduated. I'm proud", 400, 500, "Comic Sans", 30, Color.BLACK);
    }

    @Override
    public void update() {
    }

    public void draw(GraphicsHandler graphicsHandler) {
        winImage.draw(graphicsHandler);
        winMessage.draw(graphicsHandler);
    }
}
