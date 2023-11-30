package Asteroid;

import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;

public class Enemy extends Ship {
    static SpriteSheet enemy = new SpriteSheet(ImageLoader.load("enemy.png"), 13, 13);
    protected PlayerShip playerShip;
    protected Asteroid aster;
    protected boolean shot;

    public Enemy(float x, float y, String startingAnim, PlayerShip playerShip, Asteroid aster) {
        super(x, y, enemy, startingAnim, aster, 1);
        this.playerShip = playerShip;
        this.aster = aster;
    }

    @Override
    public void update() {
        super.update();

        checkPlayerOnSide();

        moveAmountX = 0;
        moveAmountY = 0;
    }

    @Override
    boolean checkCollision() {
        for (Bullet bullet : aster.getPlayerBullets()) {
            if (isCollidingY(bullet) && isCollidingX(bullet)) {
                bullet.setHit(true);
                return true;
            }
        }
        return false;
    }

    public void checkPlayerOnSide() {
        switch (this.aimingDirection) {
            case UP:
            case DOWN:
                if (playerShip.getX() < this.x
                        && playerShip.getY() >= this.y - 26
                        && playerShip.getY() <= this.y + 26) {
                    this.currentAnimationName = "STAND_LEFT";
                } else if (playerShip.getX() > this.x
                        && playerShip.getY() >= this.y - 26
                        && playerShip.getY() <= this.y + 26) {
                    this.currentAnimationName = "STAND_RIGHT";
                } else if (playerShip.getY() > this.y) {
                    this.currentAnimationName = "STAND_DOWN";
                } else if (playerShip.getY() < this.y) {
                    this.currentAnimationName = "STAND_UP";
                }
                break;
            case LEFT:
            case RIGHT:
                if (playerShip.getY() <= this.y
                        && playerShip.getX() >= this.x - 13
                        && playerShip.getX() <= this.x + 13) {
                    this.currentAnimationName = "STAND_UP";
                } else if (playerShip.getY() >= this.y
                        && playerShip.getX() >= this.x - 13
                        && playerShip.getX() <= this.x + 13) {
                    this.currentAnimationName = "STAND_DOWN";
                } else if (playerShip.getX() > this.x) {
                    this.currentAnimationName = "STAND_RIGHT";
                } else if (playerShip.getX() < this.x) {
                    this.currentAnimationName = "STAND_LEFT";
                }
                break;
        }
    }

    @Override
    void handleHit() {
        health--;
        if (health == 0) {
            aster.collectEnemy(this);
        }
    }

    @Override
    void handleShipShooting() {
        switch (currentAnimationName) {
            case "STAND_RIGHT":
            case "STAND_LEFT":
                if (y > playerShip.getY() - 5 && y < playerShip.getY() + 5) {
                    if (!shot) {
                        fire();
                        shot = true;
                    }
                } else {
                    shot = false;
                }
                break;
            case "STAND_UP":
            case "STAND_DOWN":
                if (x > playerShip.getX() - 5 && x < playerShip.getX() + 5) {
                    if (!shot) {
                        fire();
                        shot = true;
                    }
                } else {
                    shot = false;
                }
                break;
            default:
                break;
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
