package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import Level.Map;
import Level.Sound;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the credits screen
public class CreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont creditsLabel;
    protected SpriteFont createdByLabel;
    protected SpriteFont createdByLabel2;
    protected SpriteFont createdByLabel3;
    protected SpriteFont createdByLabel4;
    protected SpriteFont createdByLabel5;
    protected SpriteFont createdByLabel6;
    protected SpriteFont returnInstructionsLabel;

    protected Sound cursorSound;

    private Sprite javaJohn;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        cursorSound = new Sound();
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        javaJohn = new Sprite(ImageLoader.load("JavaJohnPortrait.png"), 400, 200);
        creditsLabel = new SpriteFont("Credits", 15, 7, "Times New Roman", 30, Color.white);
        createdByLabel = new SpriteFont("Created by:", 130, 121, "Times New Roman", 20, Color.white);
        createdByLabel2 = new SpriteFont("Julia Bock", 130, 151, "Times New Roman", 20, Color.white);
        createdByLabel3 = new SpriteFont("Brooks Jackson", 130, 181, "Times New Roman", 20, Color.white);
        createdByLabel4 = new SpriteFont("Hayden Lacy", 130, 211, "Times New Roman", 20, Color.white);
        createdByLabel5 = new SpriteFont("Connor Ryan", 130, 241, "Times New Roman", 20, Color.white);
        createdByLabel6 = new SpriteFont("Ryan Sliger", 130, 271, "Times New Roman", 20, Color.white);
        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 20, 532, "Times New Roman", 30,
                Color.white);
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            cursorSound.setFile(21);
            cursorSound.play();
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        creditsLabel.draw(graphicsHandler);
        createdByLabel.draw(graphicsHandler);
        createdByLabel2.draw(graphicsHandler);
        createdByLabel3.draw(graphicsHandler);
        createdByLabel4.draw(graphicsHandler);
        createdByLabel5.draw(graphicsHandler);
        createdByLabel6.draw(graphicsHandler);
        javaJohn.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
