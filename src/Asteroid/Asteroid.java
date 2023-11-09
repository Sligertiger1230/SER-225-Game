package Asteroid;

import java.util.ArrayList;

import Engine.GraphicsHandler;

public class Asteroid {
    protected ArrayList<Bullet> playerBullets;
    protected ArrayList<Bullet> enemyBullets;
    protected ArrayList<Bullet> bulletCollection;
    protected ArrayList<Enemy> enemies;
    protected PlayerShip playerShip;

    public Asteroid() {
        this.playerBullets = new ArrayList<Bullet>();
        this.enemyBullets = new ArrayList<Bullet>();
        this.bulletCollection = new ArrayList<Bullet>();
        this.enemies = new ArrayList<Enemy>();
        initialize();
    }

    public void initialize() {
        playerShip = new PlayerShip(400, 300, this);
        enemies.add(new Enemy(400, 200, playerShip, this));
    }

    public void update() {
        playerShip.update();
        if (!enemies.isEmpty()){
            for (Enemy enemy : enemies){
                enemy.update();
            }
        }
        if (!playerBullets.isEmpty()){
            for (Bullet bullet : playerBullets){
                bullet.update();
            }
        }
        if (!enemyBullets.isEmpty()){
            for (Bullet bullet : enemyBullets){
                bullet.update();
            }
        }
        if (bulletCollection.size() > 0){
            bulletDump();
        }

    }

    public void bulletDump(){
        for (Bullet bullet : bulletCollection){
            getBullets(bullet.getShip()).remove(bullet);
            updateBullets(bullet);
        }
        bulletCollection.clear();
    }

    public void collectBullet(Bullet bullet){
        bulletCollection.add(getBullets(bullet.getShip()).get(bullet.getBulletId()));
    }

    public void updateBullets(Bullet bullet){
        for (int index = bullet.getBulletId(); index < getBullets(bullet.getShip()).size(); index++){
            getBullets(bullet.getShip()).get(index).setBulletId(index);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        playerShip.draw(graphicsHandler);
        if (!enemies.isEmpty()) {
            for (Enemy enemy : enemies) {
                enemy.draw(graphicsHandler);
            }
        }
        if (!playerBullets.isEmpty()){
            for (Bullet bullet : playerBullets){
                bullet.draw(graphicsHandler);
            }
        }
        if (!enemyBullets.isEmpty()){
            for (Bullet bullet : enemyBullets){
                bullet.draw(graphicsHandler);
            }
        }
    }

    public ArrayList<Bullet> getBullets(Ship ship){
        if (ship == playerShip){
            return playerBullets;
        } else{
            return enemyBullets;
        }
    }

    public void addBullet(Bullet bullet){
        if (bullet.getShip() == playerShip){
            playerBullets.add(bullet);
        } else{
            enemyBullets.add(bullet);
        }
    }

    public ArrayList<Bullet> getEnemyBullets(){
        return enemyBullets;
    }

    public ArrayList<Bullet> getPlayerBullets(){
        return playerBullets;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

}
