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

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont playGame;
    protected SpriteFont credits;
    protected SpriteFont controls;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationY = 275;
    protected KeyLocker keyLocker = new KeyLocker();

    protected Sound cursorSound;

    private Sprite boomer;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        cursorSound = new Sound();
    }

    @Override
    public void initialize() {
        boomer = new Sprite(ImageLoader.load("boomer.png"), 270, pointerLocationY);
        playGame = new SpriteFont("PLAY GAME", 310, 269, "Times New Roman", 30, new Color(49, 207, 240));
        playGame.setOutlineColor(Color.black);
        playGame.setOutlineThickness(4);
        credits = new SpriteFont("CREDITS", 310, 319, "Times New Roman", 30, new Color(49, 207, 240));
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(4);
        controls = new SpriteFont("CONTROLS", 310, 369, "Times New Roman", 30, new Color(49, 207, 240));
        controls.setOutlineColor(Color.black);
        controls.setOutlineThickness(4);
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if down or up is pressed, change menu item "hovered" over (blue square in
        // front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.DOWN) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
            cursorSound.setFile(21);
            cursorSound.play();
        } else if (Keyboard.isKeyDown(Key.UP) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
            cursorSound.setFile(21);
            cursorSound.play();
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        // if down is pressed on last menu item or up is pressed on first menu item,
        // "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 1) {
            currentMenuItemHovered = 2;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 1;
        } else if (currentMenuItemHovered > 2) {
            currentMenuItemHovered = 2;
        }

        // sets location for blue square in front of text (pointerLocation) and also
        // sets color of spritefont text based on which menu item is being hovered
        //also sets outline color as white if not highlighted
        if (currentMenuItemHovered == 0) {
            playGame.setColor(new Color(255, 215, 0));
            credits.setColor(Color.white);
            credits.setOutlineColor(new Color(12, 34, 63));
            controls.setColor(Color.white);
            controls.setOutlineColor(new Color(12, 34, 63));
            boomer.setY(270);
        } else if (currentMenuItemHovered == 1) {
            playGame.setColor(Color.white);
            playGame.setOutlineColor(new Color(12, 34, 63));
            credits.setColor(new Color(255, 215, 0));
            controls.setColor(Color.white);
            controls.setOutlineColor(new Color(12, 34, 63));
            boomer.setY(320);
        } else if (currentMenuItemHovered == 2) {
            playGame.setColor(Color.white);
            playGame.setOutlineColor(new Color(12, 34, 63));
            credits.setColor(Color.white);
            credits.setOutlineColor(new Color(12, 34, 63));
            controls.setColor(new Color(255, 215, 0));
            boomer.setY(370);
        }

        // if space is pressed on menu item, change to appropriate screen based on which
        // menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            cursorSound.setFile(21);
            cursorSound.play();
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {
                screenCoordinator.setGameState(GameState.LEVEL);
            } else if (menuItemSelected == 1) {
                screenCoordinator.setGameState(GameState.CREDITS);
            } else if (menuItemSelected == 2) {
                screenCoordinator.setGameState(GameState.CONTROLS);
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        playGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        controls.draw(graphicsHandler);
        boomer.draw(graphicsHandler);
        // graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20,
        //         new Color(49, 207, 240), Color.black, 2);
    }
}
