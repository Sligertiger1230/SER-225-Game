package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Sound;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the credits screen
public class ControlsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont creditsLabel;
    protected SpriteFont escLabel;
    protected SpriteFont upLabel;
    protected SpriteFont downLabel;
    protected SpriteFont leftLabel;
    protected SpriteFont rightLabel;
    protected SpriteFont shiftLabel;
    protected SpriteFont questLabel;
    protected SpriteFont bikeLabel;
    protected SpriteFont returnInstructionsLabel;
    protected SpriteFont fyiLabel;

    protected Sound cursorSound;

    public ControlsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        cursorSound = new Sound();
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        creditsLabel = new SpriteFont("Controls", 15, 7, "Times New Roman", 30, Color.white);
        escLabel = new SpriteFont("ESC = Options", 130, 91, "Times New Roman", 20, Color.white);
        upLabel = new SpriteFont("Up Arrow = Move Up", 130, 121, "Times New Roman", 20, Color.white);
        downLabel = new SpriteFont("Down Arrow = Move Down", 130, 151, "Times New Roman", 20, Color.white);
        leftLabel = new SpriteFont("Left Arrow = Move Left", 130, 181, "Times New Roman", 20, Color.white);
        rightLabel = new SpriteFont("Right Arrow = Move Right", 130, 211, "Times New Roman", 20, Color.white);
        shiftLabel = new SpriteFont("Shift = Sprint", 130, 241, "Times New Roman", 20, Color.white);
        questLabel = new SpriteFont("Q = Quest Menu", 130, 271, "Times New Roman", 20, Color.white);
        bikeLabel = new SpriteFont("E = Bike", 130, 301, "Times New Roman", 20, Color.white);
        fyiLabel = new SpriteFont("You can see/edit these controls in the in-game menu.", 130, 331, "Times New Roman",
                20, Color.white);
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
        escLabel.draw(graphicsHandler);
        upLabel.draw(graphicsHandler);
        downLabel.draw(graphicsHandler);
        leftLabel.draw(graphicsHandler);
        rightLabel.draw(graphicsHandler);
        shiftLabel.draw(graphicsHandler);
        questLabel.draw(graphicsHandler);
        bikeLabel.draw(graphicsHandler);
        fyiLabel.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
