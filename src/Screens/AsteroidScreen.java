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
import Level.Sound;

public class AsteroidScreen extends Screen {
    protected Sprite window;
    protected Sprite startScreen;
    protected Sprite deadScreen;
    protected Sprite winScreen;

    protected int maxWave;

    protected Key startGame = Key.ENTER;
    protected Key exitGame = Key.E;
    protected Key restartGame = Key.R;

    protected Asteroid aster;
    protected AsteroidState asteroidState;
    PlayLevelScreen playLevelScreen;

    protected Sound sound;

    public AsteroidScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        this.maxWave = 0;
        initialize();
    }

    public AsteroidScreen(PlayLevelScreen playLevelScreen, int maxWave){
        this.playLevelScreen = playLevelScreen;
        this.maxWave = maxWave;
        initialize();
    }

    @Override
    public void initialize() {
        window = new Sprite(ImageLoader.load("AsteroidScreen.png"));
        startScreen = new Sprite(ImageLoader.load("startScreen.png"));
        deadScreen = new Sprite(ImageLoader.load("deadScreen.png"));
        winScreen = new Sprite(ImageLoader.load("winScreen.png"));
        this.asteroidState = AsteroidState.START;
        aster = new Asteroid(this, maxWave);
        this.sound = new Sound();
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
                    sound.setFile(24);
                    sound.loop();
                }
                if (Keyboard.isKeyDown(exitGame)) {
                    playLevelScreen.returnFromAsteroid(asteroidState, aster.getFinalWave(), maxWave);
                    sound.stop();
                    sound.setFile(18);
                    sound.loop();
                }
                break;
            case RUNNING:
                aster.update();
                if (Keyboard.isKeyDown(exitGame)) {
                    playLevelScreen.returnFromAsteroid(asteroidState, aster.getFinalWave(), maxWave);
                    sound.stop();
                    sound.setFile(18);
                    sound.loop();
                }
                break;
            case DEAD:
                if (Keyboard.isKeyDown(restartGame)) {
                    asteroidState = AsteroidState.RUNNING;
                    aster = new Asteroid(this, maxWave);
                }
                if (Keyboard.isKeyDown(exitGame)) {
                    playLevelScreen.returnFromAsteroid(asteroidState, aster.getFinalWave(), maxWave);
                    sound.stop();
                    sound.setFile(18);
                    sound.loop();
                }
                break;
            case WIN:
                if (Keyboard.isKeyDown(exitGame)) {
                    playLevelScreen.returnFromAsteroid(asteroidState, aster.getFinalWave(), maxWave);
                    sound.stop();
                    sound.setFile(18);
                    sound.loop();
                }
                break;
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(),
                        ScreenManager.getScreenHeight(), new Color(0, 0, 0));
        handleGameDraw(graphicsHandler);
        window.draw(graphicsHandler);
        aster.drawText(graphicsHandler);
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
