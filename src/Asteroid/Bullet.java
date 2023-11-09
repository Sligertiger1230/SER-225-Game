package Asteroid;

import java.util.HashMap;

import Asteroid.Ship.AimingDirection;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;

public class Bullet extends GameObject {
    static SpriteSheet bullet = new SpriteSheet(ImageLoader.load("bullet.png"), 5, 5);
    protected Asteroid aster;
    protected String currentDirection;
    protected Ship ship;

    protected boolean hit;
    protected float bulletSpeed = 8;
    protected int bulletId;

    protected float moveAmountX, moveAmountY;

    public Bullet(Ship ship, int bulletId, Asteroid aster) {
        super(bullet, (ship.getWidth() / 2) + ship.getX1() - 5, (ship.getHeight() / 2) + ship.getY1() - 5,
                directionToAnimation(ship.getAimingDirection()));
        this.aster = aster;
        this.ship = ship;
        this.hit = false;
        this.bulletId = bulletId;
        this.currentAnimationName = directionToAnimation(ship.getAimingDirection());
        handleBulletMovement();
    }

    public static String directionToAnimation(AimingDirection aimingDirection){
        switch (aimingDirection){
            case UP:
                return "STAND_UP";
            case DOWN:
                return "STAND_DOWN";
            case LEFT:
                return "STAND_LEFT";
            case RIGHT:
                return "STAND_RIGHT";
            default:
                return "STAND_UP";
        }
    }

    public void update() {
        moveX(moveAmountX);
        moveY(moveAmountY);

        if (x < 24 || getBoundsX2() > 759
                || y < 111 || getBoundsY2() > 563 || hit) {
            aster.collectBullet(this);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        this.currentFrame.draw(graphicsHandler);
    }

    public Ship getShip(){
        return ship;
    }

    public void handleBulletMovement() {
        if (currentAnimationName.equals("STAND_UP")) {
            moveAmountY = -bulletSpeed;
        } else if (currentAnimationName.equals("STAND_DOWN")) {
            moveAmountY = bulletSpeed;
        } else if (currentAnimationName.equals("STAND_LEFT")) {
            moveAmountX = -bulletSpeed;
        } else if (currentAnimationName.equals("STAND_RIGHT")) {
            moveAmountX = bulletSpeed;
        }
    }

    public int getBulletId() {
        return bulletId;
    }

    public void setBulletId(int bulletId) {
        this.bulletId = bulletId;
    }

    public void setHit(boolean hit){
        this.hit = hit;
    }

    public boolean hit(){
        return hit;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("STAND_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(2)
                                .withBounds(0, 0, 5, 5)
                                .build()
                });
                put("STAND_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(2)
                                .withBounds(0, 0, 5, 15)
                                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                                .build()
                });
                put("STAND_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 1))
                                .withScale(2)
                                .withBounds(0, 0, 5, 5)
                                .build()
                });
                put("STAND_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 1))
                                .withScale(2)
                                .withBounds(0, 0, 5, 5)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build()
                });
                put("WALK_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 0))
                                .withScale(2)
                                .withBounds(0, 0, 5, 5)
                                .build()
                });
                put("WALK_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 0))
                                .withScale(2)
                                .withBounds(0, 0, 5, 15)
                                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                                .build()
                });
                put("WALK_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 1))
                                .withScale(2)
                                .withBounds(0, 0, 5, 5)
                                .build()
                });
                put("WALK_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 1))
                                .withScale(2)
                                .withBounds(0, 0, 5, 5)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build()
                });
            }
        };
    }
}
