package Asteroid;

import Engine.GraphicsHandler;
import Engine.Key;
import GameObject.GameObject;
import GameObject.SpriteSheet;

abstract class Ship extends GameObject {
    private Asteroid aster;

    protected Key MOVE_LEFT_KEY = Key.A;
    protected Key MOVE_RIGHT_KEY = Key.D;
    protected Key MOVE_UP_KEY = Key.W;
    protected Key MOVE_DOWN_KEY = Key.S;

    protected AimingDirection aimingDirection;
    protected Key AIM_LEFT_KEY = Key.LEFT;
    protected Key AIM_RIGHT_KEY = Key.RIGHT;
    protected Key AIM_UP_KEY = Key.UP;
    protected Key AIM_DOWN_KEY = Key.DOWN;
    protected Key SHOOT = Key.SPACE;

    protected float moveSpeed = 3;
    protected float moveAmountX, moveAmountY;
    protected float lastMovedAmountX, lastMovedAmountY;

    protected int health;

    protected boolean shot = false;
    protected boolean hit = false;

    public Ship(float x, float y, SpriteSheet ship, String startAnim, Asteroid aster, int health) {
        super(ship, x, y, startAnim);
        this.currentAnimationName = startAnim;
        this.aster = aster;
        this.health = health;
        handleAimingDirection();
    }

    public void update() {
        handleShipMovement();
        if (checkCollision()) {
            if (!hit) {
                handleHit();
                hit = true;
            }
        } else {
            hit = false;
            handleShipDirection();
        }
        handleShipShooting();
        if (x + moveAmountX > 24 && getBoundsX2() + moveAmountX < 759)
            moveX(moveAmountX);
        if (y + moveAmountY > 111 && getBoundsY2() + moveAmountY < 563)
            moveY(moveAmountY);

        super.update();
    }

    public void handleAimingDirection() {
        switch (currentAnimationName) {
            case "STAND_UP":
                aimingDirection = AimingDirection.UP;
                break;
            case "STAND_DOWN":
                aimingDirection = AimingDirection.DOWN;
                break;
            case "STAND_LEFT":
                aimingDirection = AimingDirection.LEFT;
                break;
            case "STAND_RIGHT":
                aimingDirection = AimingDirection.RIGHT;
                break;
        }
    }

    public boolean checkRight(GameObject object) {
        float objectLeftEdge = object.getX() - object.getWidth() / 2;
        float playerRightEdge = this.getX() + this.getWidth() / 2;
        float xDiff = objectLeftEdge - playerRightEdge;
        if (xDiff <= 0 && xDiff >= -this.getWidth() / 2 * 2) {
            return true;
        }
        return false;
    }

    public boolean checkLeft(GameObject object) {
        float objectRightEdge = object.getX() + object.getWidth() / 2;
        float playerLeftEdge = this.getX() - this.getWidth() / 2;
        float xDiff = objectRightEdge - playerLeftEdge;
        if (xDiff >= 0 && xDiff <= this.getWidth() / 2 * 2) {

            return true;
        }
        return false;
    }

    public boolean isCollidingX(GameObject object) {
        if (checkRight(object) || checkLeft(object)) {
            return true;
        }
        return false;
    }

    public boolean isCollidingY(GameObject object) {
        if (checkUp(object) || checkDown(object)) {
            return true;
        }
        return false;
    }

    public boolean checkUp(GameObject object) {
        float objectBottomEdge = object.getY() + object.getWidth() / 2;
        float playerTopEdge = this.getY() - this.getWidth() / 2;
        float yDiff = objectBottomEdge - playerTopEdge;
        if (yDiff >= 0 && yDiff <= this.getHeight() / 2 * 2) {
            return true;
        }
        return false;
    }

    public boolean checkDown(GameObject object) {
        float objectTopEdge = object.getY() - object.getWidth() / 2;
        float playerBottomEdge = this.getY() + this.getWidth() / 2;
        float yDiff = objectTopEdge - playerBottomEdge;
        if (yDiff <= 0 && yDiff >= -this.getHeight() / 2 * 2) {
            return true;
        }
        return false;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        this.currentFrame.draw(graphicsHandler);
    }

    abstract boolean checkCollision();

    abstract void handleHit();

    abstract void handleShipShooting();

    abstract void handleShipDirection();

    abstract void handleShipMovement();

    public void fire() {
        aster.addBullet(new Bullet(this, aster.getBullets(this).size(), aster));
    }

    public int getHealth() {
        return health;
    }

    public AimingDirection getAimingDirection() {
        return aimingDirection;
    }

    enum AimingDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
