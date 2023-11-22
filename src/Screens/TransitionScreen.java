package Screens;

import Engine.*;
import java.awt.*;

// This class is for the win level screen
public class TransitionScreen extends Screen {
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;

    private float alpha = 0.0f; // Variable to track fade effect's alpha value

    public TransitionScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        keyLocker.lockKey(Key.SPACE);
    }

    private boolean fadeInComplete = false; // Flag to track fade-in completion
    private boolean fadeOutComplete = false;   // Flag to track fade-out completion

public void update() {
    // Check for keyboard events before applying the fade effect
    
    if (Keyboard.isKeyUp(Key.SPACE)) {
        keyLocker.unlockKey(Key.SPACE);
    }

    // applies fade-in effect
    if (!fadeInComplete) {
        alpha += 0.07f; // gradually increases alpha for fade-in

        if (alpha >= 1.0f) {
            fadeInComplete = true;
        }
    }

    // applies fade-out effect
    if (fadeInComplete && !fadeOutComplete) {
        alpha -= 0.05f; // gradually decreases alpha for fade-out

        if (alpha <= 0.0f) {
            fadeOutComplete = true; 
            playLevelScreen.returnFromTransition();

        }
    }
}


    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        // Draw the current map under the fade effect
        playLevelScreen.map.draw(playLevelScreen.player, graphicsHandler);

        // Apply alpha value to the fade effect
        Color fadeColor = new Color(0, 0, 0, (int) (255 * alpha));
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), fadeColor);
    }
}
