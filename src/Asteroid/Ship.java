package Asteroid;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;

public class Ship extends GameObject {
    static SpriteSheet ship = new SpriteSheet(ImageLoader.load("Ship.png"), 13, 14);

    protected Key MOVE_LEFT_KEY = Key.LEFT;
    protected Key MOVE_RIGHT_KEY = Key.RIGHT;
    protected Key MOVE_UP_KEY = Key.UP;
    protected Key MOVE_DOWN_KEY = Key.DOWN;
    protected Key INTERACT_KEY = Key.SPACE;
    protected Key SPRINT_KEY = Key.SHIFT;

    protected float moveSpeed;
    protected float moveAmountX, moveAmountY;

    public Ship(float x, float y) {
        super(ship, x, y, "STAND_UP");
    }

    public void update(){

        moveAmountX = 0;
        moveAmountY = 0;


        handleShipMovement();

        super.update();
    }

    public void handleShipMovement() {
        if (Keyboard.isKeyDown(MOVE_UP_KEY)) {
            moveAmountY -= moveSpeed * 8;
        } else if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountY += moveSpeed * 8;
        }

        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountY -= moveSpeed * 8;
        } else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            moveAmountY += moveSpeed * 8;
        }

    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("STAND_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(2)
                                .withBounds(6, 12, 12, 7)
                                .build()
                });
            }
        };
    }
}
