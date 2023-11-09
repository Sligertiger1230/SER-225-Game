package Asteroid;

import GameObject.Frame;
import GameObject.GameObject;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;

public class Enemy extends Ship{
    static SpriteSheet enemy = new SpriteSheet(ImageLoader.load("enemy.png"), 13, 13);
    static String startingAnim = "STAND_DOWN";
    protected PlayerShip playerShip;
    protected boolean shot;
    

    public Enemy(float x, float y, PlayerShip playerShip, Asteroid aster) {
        super(x, y, enemy, startingAnim, aster);
        this.playerShip = playerShip;
    }

    @Override
    void handleShipShooting() {
        if (x > playerShip.getX() - 10 && x < playerShip.getX() + 10){
            if (!shot){
                fire();
                shot = true;
            }
        }
        else {
            shot = false;
        }
    }

    @Override
    void handleShipDirection() {
    }

    @Override
    void handleShipMovement() {
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("STAND_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(2)
                                .withBounds(0, 0, 13, 13)
                                .build()
                });
                put("STAND_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(2)
                                .withBounds(0, 0, 13, 15)
                                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                                .build()
                });
                put("STAND_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 0))
                                .withScale(2)
                                .withBounds(0, 0, 13, 13)
                                .build()
                });
                put("STAND_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 0))
                                .withScale(2)
                                .withBounds(0, 0, 13, 13)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build()
                });
            }
        };
    }

    
}
