package Screens;

import Asteroid.Ship;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import GameObject.Sprite;

public class AsteroidScreen extends Screen{
    Sprite window;
    Ship ship;
    PlayLevelScreen playLevelScreen;

    public AsteroidScreen(PlayLevelScreen playLevelScreen){
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        window = new Sprite(ImageLoader.load("AsteroidScreen.png"));
        ship = new Ship(400, 300);
    }

    @Override
    public void update() {
        ship.update();
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        window.draw(graphicsHandler);
        //ship.draw(graphicsHandler);
    }
    
}
