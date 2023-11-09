package Screens;

import java.awt.Color;

import Asteroid.Asteroid;
import Asteroid.AsteroidState;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import GameObject.Sprite;

public class AsteroidScreen extends Screen {
    protected Sprite window;
    protected Sprite startScreen;
    protected Sprite deadScreen;
    protected Sprite winScreen;

    protected Key startGame = Key.ENTER;
    protected Key exitGame = Key.E;
    protected Key restartGame = Key.R;

    protected Asteroid aster;
    protected AsteroidState asteroidState;
    PlayLevelScreen playLevelScreen;

    public AsteroidScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        window = new Sprite(ImageLoader.load("AsteroidScreen.png"));
        startScreen = new Sprite(ImageLoader.load("startScreen.png"));
        deadScreen = new Sprite(ImageLoader.load("deadScreen.png"));
        winScreen = new Sprite(ImageLoader.load("winScreen.png"));
        this.asteroidState = AsteroidState.START;
        aster = new Asteroid(this);
    }

    @Override
    public void update() {
        handleGameState();
    }

    public void handleGameState() {
        switch (asteroidState) {
            case START:
                if (Keyboard.isKeyDown(startGame)) {
                    asteroidState = AsteroidState.RUNNING;
                }
                break;
            case RUNNING:
                aster.update();
                break;
            case DEAD:
                if (Keyboard.isKeyDown(restartGame)){
                    asteroidState = AsteroidState.RUNNING;
                    aster = new Asteroid(this);
                }
                break;
            case WIN:
                if (Keyboard.isKeyDown(exitGame)){
                    PlayLevelScreen.returnFromAsteroid();
                }
                break;
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        window.draw(graphicsHandler);
        handleGameDraw(graphicsHandler);
    }

    public void handleGameDraw(GraphicsHandler graphicsHandler) {
        switch (asteroidState) {
            case RUNNING:
                aster.draw(graphicsHandler);
                break;
            case START:
                graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(),
                        ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
                startScreen.draw(graphicsHandler);
                break;
            case DEAD:
                graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(),
                        ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
                deadScreen.draw(graphicsHandler);
                break;
            case WIN:
                graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(),
                        ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
                winScreen.draw(graphicsHandler);
                break;
        }
    }

    public void setAsteroidState(AsteroidState newState) {
        this.asteroidState = newState;
    }

}
