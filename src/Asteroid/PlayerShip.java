package Asteroid;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;

public class PlayerShip extends Ship{
    static SpriteSheet ship = new SpriteSheet(ImageLoader.load("Ship.png"), 13, 13);
    static String startingAnim = "STAND_UP";
    protected Asteroid aster;

    public PlayerShip(float x, float y, Asteroid aster) {
        super(x, y, ship,startingAnim, aster);
        this.aster = aster;
    }
    
    @Override
    public void handleShipShooting(){
        if (Keyboard.isKeyDown(SHOOT) && !shot){
            fire();
            shot = true;
        }
        if (Keyboard.isKeyUp(SHOOT)){
            shot = false;
        }
    }

    @Override
    public void handleShipDirection() {
        if (Keyboard.isKeyDown(AIM_UP_KEY)) {
            this.currentAnimationName = "STAND_UP";
        } else if (Keyboard.isKeyDown(AIM_DOWN_KEY)) {
            this.currentAnimationName = "STAND_DOWN";
        } else if (Keyboard.isKeyDown(AIM_LEFT_KEY)) {
            this.currentAnimationName = "STAND_LEFT";
        } else if (Keyboard.isKeyDown(AIM_RIGHT_KEY)) {
            this.currentAnimationName = "STAND_RIGHT";
        } else {
            this.currentAnimationName = "STAND_UP";
        }
    }

    public void checkCollisionX(){
        if (!aster.getEnemies().isEmpty()){
            for (Enemy enemy : aster.getEnemies()){
                if (enemy.getX() < this.x){
                    checkLeft(enemy);
                }
            }
        }
    }

    public void checkLeft(GameObject object){
        
    }

    @Override
    public void handleShipMovement() {
        if (Keyboard.isKeyDown(MOVE_UP_KEY)) {
            moveAmountY -= moveSpeed;
        } else if (Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            moveAmountY += moveSpeed;
        }

        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountX -= moveSpeed;
        } else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            moveAmountX += moveSpeed;
        }
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
