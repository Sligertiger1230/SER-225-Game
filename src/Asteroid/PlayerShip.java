package Asteroid;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;

public class PlayerShip extends Ship {
    static SpriteSheet ship = new SpriteSheet(ImageLoader.load("Ship.png"), 13, 13);
    static String startingAnim = "STAND_UP";

    protected Asteroid aster;
    protected boolean invuln = false;
    protected int invulnCounter = 0;
    protected boolean isPlayerDead = false;

    public PlayerShip(float x, float y, Asteroid aster) {
        super(x, y, ship, startingAnim, aster, 15);
        this.aster = aster;
    }

    @Override
    public void update() {
        if (invuln) {
            handleInvulnDirection();
            invulnCounter++;
            if (invulnCounter == 20) {
                invuln = false;
                invulnCounter = 0;
            }
        }

        moveAmountX = 0;
        moveAmountY = 0;

        super.update();
    }

    @Override
    public void handleShipShooting() {
        if (Keyboard.isKeyDown(SHOOT) && !shot) {
            fire();
            shot = true;
        }
        if (Keyboard.isKeyUp(SHOOT)) {
            shot = false;
        }
    }

    @Override
    public void handleShipDirection() {
        if (Keyboard.isKeyDown(AIM_UP_KEY)) {
            this.currentAnimationName = "STAND_UP";
            aimingDirection = AimingDirection.UP;
        } else if (Keyboard.isKeyDown(AIM_DOWN_KEY)) {
            this.currentAnimationName = "STAND_DOWN";
            aimingDirection = AimingDirection.DOWN;
        } else if (Keyboard.isKeyDown(AIM_LEFT_KEY)) {
            this.currentAnimationName = "STAND_LEFT";
            aimingDirection = AimingDirection.LEFT;
        } else if (Keyboard.isKeyDown(AIM_RIGHT_KEY)) {
            this.currentAnimationName = "STAND_RIGHT";
            aimingDirection = AimingDirection.RIGHT;
        } else {
            this.currentAnimationName = "STAND_UP";
            aimingDirection = AimingDirection.UP;
        }
    }

    @Override
    public void handleShipMovement() {
        if (Keyboard.isKeyDown(MOVE_UP_KEY)) {
            moveAmountY = -moveSpeed;
        } else if (Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            moveAmountY = moveSpeed;
        }
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountX = -moveSpeed;
        } else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            moveAmountX = moveSpeed;
        }
    }

    @Override
    public void handleHit() {
        invuln = true;
        health--;
        if (health == 0) {
            isPlayerDead = true;
        } else {
            handleInvulnDirection();
        }

    }

    public void handleInvulnDirection() {
        switch (aimingDirection) {
            case UP:
                this.currentAnimationName = "HURT_UP";
                break;
            case DOWN:
                this.currentAnimationName = "HURT_DOWN";
                break;
            case LEFT:
                this.currentAnimationName = "HURT_LEFT";
                break;
            case RIGHT:
                this.currentAnimationName = "HURT_RIGHT";
                break;
        }
    }

    @Override
    public boolean checkCollision() {
        if (!aster.getEnemies().isEmpty()) {
            for (Enemy enemy : aster.getEnemies()) {
                if (isCollidingY(enemy) && isCollidingX(enemy)) {
                    return true;
                }
            }
        }
        if (!aster.getEnemyBullets().isEmpty()) {
            for (Bullet bullet : aster.getEnemyBullets()) {
                if (isCollidingY(bullet) && isCollidingX(bullet)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getCurrentAnimationName() {
        if (currentAnimationName.startsWith("HURT")) {
            switch (aimingDirection) {
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
        return currentAnimationName;
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
                put("HURT_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 1))
                                .withScale(2)
                                .withBounds(0, 0, 13, 13)
                                .build()
                });
                put("HURT_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 1))
                                .withScale(2)
                                .withBounds(0, 0, 13, 15)
                                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                                .build()
                });
                put("HURT_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 1))
                                .withScale(2)
                                .withBounds(0, 0, 13, 13)
                                .build()
                });
                put("HURT_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 1))
                                .withScale(2)
                                .withBounds(0, 0, 13, 13)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build()
                });
            }
        };
    }
}
