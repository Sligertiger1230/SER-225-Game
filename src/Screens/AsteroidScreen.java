package Screens;

import Asteroid.PlayerShip;
import Asteroid.Asteroid;
import Asteroid.Enemy;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import GameObject.Sprite;

public class AsteroidScreen extends Screen{
    protected Sprite window;
    protected PlayerShip playerShip;
    protected Enemy enemyShip;
    protected Asteroid aster;
    PlayLevelScreen playLevelScreen;

    public AsteroidScreen(PlayLevelScreen playLevelScreen){
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        window = new Sprite(ImageLoader.load("AsteroidScreen.png"));
        aster = new Asteroid();
        /*playerShip = new PlayerShip(400, 300);
        enemyShip = new Enemy(400, 200, playerShip);*/
    }

    @Override
    public void update() {
        aster.update();
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        window.draw(graphicsHandler);
        aster.draw(graphicsHandler);
    }
    
}
