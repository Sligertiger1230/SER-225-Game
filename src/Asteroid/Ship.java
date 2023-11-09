package Asteroid;

import java.util.ArrayList;
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

    protected Key AIM_LEFT_KEY = Key.LEFT;
    protected Key AIM_RIGHT_KEY = Key.RIGHT;
    protected Key AIM_UP_KEY = Key.UP;
    protected Key AIM_DOWN_KEY = Key.DOWN;
    protected Key SHOOT = Key.SPACE;

    protected float moveSpeed = 3;
    protected float moveAmountX, moveAmountY;
    protected float lastMovedAmountX, lastMovedAmountY;

    protected ArrayList<Bullet> bullets;
    protected ArrayList<Bullet> bulletCollection;
    protected boolean shot = false;

    public Ship(float x, float y, SpriteSheet ship, String startAnim, Asteroid aster) {
        super(ship, x, y, startAnim);
        this.currentAnimationName = startAnim;
        this.aster = aster;
        this.bullets = new ArrayList<Bullet>();
        this.bulletCollection = new ArrayList<Bullet>();
    }

    public void update() {
        moveAmountX = 0; 
        moveAmountY = 0;

        handleShipMovement();

        handleShipDirection();
        handleShipShooting();
        if (x + moveAmountX > 24 && getBoundsX2() + moveAmountX < 759)
            moveX(moveAmountX);
        if (y + moveAmountY > 111 && getBoundsY2() + moveAmountY < 563)
            moveY(moveAmountY);


        
        super.update();
    }

    

    public void draw(GraphicsHandler graphicsHandler){
        this.currentFrame.draw(graphicsHandler);
        if (!bullets.isEmpty()){
            for (Bullet bullet : bullets){
                bullet.draw(graphicsHandler);
            }
        }
    }

    public Bullet getBullet(int bulletId){
        return bullets.get(bulletId);
    }

    public ArrayList<Bullet> getBullets(){
        return bullets;
    }

    abstract void handleShipShooting();

    abstract void handleShipDirection();          

    abstract void handleShipMovement();
    
    public void fire(){
        aster.addBullet(new Bullet(this, aster.getBullets(this).size(), aster));
    }
}
