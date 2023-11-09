package Asteroid;

import java.awt.Color;
import java.util.ArrayList;

import Engine.GraphicsHandler;
import Screens.AsteroidScreen;
import SpriteFont.SpriteFont;

public class Asteroid {
    protected ArrayList<Bullet> playerBullets;
    protected ArrayList<Bullet> enemyBullets;
    protected ArrayList<Bullet> bulletCollection;
    protected ArrayList<Enemy> enemies;
    protected ArrayList<Enemy> enemyCollection;
    protected PlayerShip playerShip;

    protected SpriteFont healthIndic;

    protected AsteroidScreen screen;

    protected int waveCounter = 0;
    protected int waveTic;
    protected boolean wave1;
    protected boolean wave2;
    protected boolean wave3;

    public Asteroid(AsteroidScreen screen) {
        this.playerBullets = new ArrayList<Bullet>();
        this.enemyBullets = new ArrayList<Bullet>();
        this.bulletCollection = new ArrayList<Bullet>();
        this.enemies = new ArrayList<Enemy>();
        this.enemyCollection = new ArrayList<Enemy>();
        this.screen = screen;
        initialize();
    }

    public void initialize() {
        playerShip = new PlayerShip(400, 300, this);
        healthIndic = new SpriteFont("Health: " + playerShip.getHealth(), 600, 80, "Comic Sans", 20, Color.BLACK);
        // enemies.add(new Enemy(400, 200, playerShip, this));
        wave1 = false;
        wave2 = false;
        wave3 = false;
    }

    public void update() {
        playerShip.update();
        healthIndic = new SpriteFont("Health: " + playerShip.getHealth(), 600, 80, "Comic Sans", 20, Color.BLACK);
        System.out.println(playerShip.getHealth());
        if (playerShip.getHealth() <= 0){
            System.out.println("player dead");
            screen.setAsteroidState(AsteroidState.DEAD);
        }
        if (!enemies.isEmpty()) {
            for (Enemy enemy : enemies) {
                enemy.update();
            }
        }
        if (enemyCollection.size() > 0) {
            enemyDump();
        }
        if (!playerBullets.isEmpty()) {
            for (Bullet bullet : playerBullets) {
                bullet.update();
            }
        }
        if (!enemyBullets.isEmpty()) {
            for (Bullet bullet : enemyBullets) {
                bullet.update();
            }
        }
        if (bulletCollection.size() > 0) {
            bulletDump();
        }

        handleWave();

    }

    public void handleWave() {
        waveTic++;
        if (enemies.isEmpty()) {
            waveCounter++;
        }
        switch (waveCounter) {
            case 1:
                wave1();
                break;
            case 2:
                wave2();
                break;
            case 3:
                wave3();
                break;
            case 4:
                wave4();
                break;
            case 5:
                screen.setAsteroidState(AsteroidState.WIN);
                break;
        }
    }

    // screen dimensions
    // x is 24 - 759
    // y is 111 - 563

    public void wave1() {
        if (enemies.isEmpty()) {
            for (int enemyCount = 0; enemyCount < 5; enemyCount++) {
                enemies.add(new Enemy(50, enemyCount * 86 + 131, "STAND_RIGHT", playerShip, this));
                enemies.add(new Enemy(709, enemyCount * 86 + 161, "STAND_LEFT", playerShip, this));
            }
        }
        if (waveTic % 100 < 50) {
            for (Enemy enemy : enemies) {
                if (enemy.getCurrentAnimationName().equals("STAND_RIGHT")){
                    enemy.moveY(1);
                } else {
                    enemy.moveY(-1);
                }
            }
        } else {
            for (Enemy enemy : enemies) {
                if (enemy.getCurrentAnimationName().equals("STAND_RIGHT")){
                    enemy.moveY(-1);
                } else {
                    enemy.moveY(1);
                }
            }
        }
    }

    public void wave2() {
        if (enemies.isEmpty()) {
            for (int enemyCount = 0; enemyCount < 7; enemyCount++) {
                enemies.add(new Enemy(enemyCount * 102 + 74, 131, "STAND_DOWN", playerShip, this));
                enemies.add(new Enemy(enemyCount * 102 + 74, 533, "STAND_UP", playerShip, this));
            }
        }

            if (waveTic % 100 < 50) {
                for (Enemy enemy : enemies) {
                    if (enemy.getCurrentAnimationName().equals("STAND_DOWN")){
                        enemy.moveX(1);
                    } else{
                        enemy.moveX(-1);
                    }
                }

            } else {
                for (Enemy enemy : enemies) {
                    if (enemy.getCurrentAnimationName().equals("STAND_DOWN")){
                        enemy.moveX(-1);
                    } else{
                        enemy.moveX(1);
                    }
                }
            }
    }

    public void wave3(){
        if (enemies.isEmpty()){
            for (int enemyCount = 0; enemyCount < 7; enemyCount++){
                enemies.add(new Enemy(enemyCount * 80 + 104, 131, "STAND_DOWN", playerShip, this));
            }
            for (int enemyCount = 0; enemyCount < 5; enemyCount++){
                enemies.add(new Enemy(709, enemyCount * 80 + 160, "STAND_LEFT", playerShip, this));
            }
        }

        if (waveTic % 100 < 50){
            for (Enemy enemy : enemies){
                if (enemy.getCurrentAnimationName().equals("STAND_DOWN")){
                    enemy.moveX(1);
                } else {
                    enemy.moveY(1);
                }
            }
        } else {
            for (Enemy enemy : enemies){
                if (enemy.getCurrentAnimationName().equals("STAND_DOWN")){
                    enemy.moveX(-1);
                } else {
                    enemy.moveY(-1);
                }
            }
        }
    }

    public void wave4(){
        if (enemies.isEmpty()){
            for (int enemyCount = 0; enemyCount < 7; enemyCount++){
                enemies.add(new Enemy(enemyCount * 80 + 124, 533, "STAND_UP", playerShip, this));
            }
            for (int enemyCount = 0; enemyCount < 7; enemyCount++){
                enemies.add(new Enemy(54, enemyCount * 60 + 121, "STAND_RIGHT", playerShip, this));
            }
        }

        if (waveTic % 100 < 50){
            for (Enemy enemy : enemies){
                if (enemy.getCurrentAnimationName().equals("STAND_UP")){
                    enemy.moveX(1);
                } else {
                    enemy.moveY(1);
                }
            }
        } else {
            for (Enemy enemy : enemies){
                if (enemy.getCurrentAnimationName().equals("STAND_UP")){
                    enemy.moveX(-1);
                } else {
                    enemy.moveY(-1);
                }
            }
        }
    }

    public void bulletDump() {
        for (Bullet bullet : bulletCollection) {
            getBullets(bullet.getShip()).remove(bullet);
            updateBullets(bullet);
        }
        bulletCollection.clear();
    }

    public void enemyDump() {
        for (Enemy enemy : enemyCollection) {
            enemies.remove(enemy);
        }
        enemyCollection.clear();
    }

    public void collectBullet(Bullet bullet) {
        bulletCollection.add(getBullets(bullet.getShip()).get(bullet.getBulletId()));
    }

    public void collectEnemy(Enemy enemy) {
        enemyCollection.add(enemy);
    }

    public void updateBullets(Bullet bullet) {
        for (int index = bullet.getBulletId(); index < getBullets(bullet.getShip()).size(); index++) {
            getBullets(bullet.getShip()).get(index).setBulletId(index);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        playerShip.draw(graphicsHandler);
        healthIndic.draw(graphicsHandler);
        if (!enemies.isEmpty()) {
            for (Enemy enemy : enemies) {
                enemy.draw(graphicsHandler);
            }
        }
        if (!playerBullets.isEmpty()) {
            for (Bullet bullet : playerBullets) {
                bullet.draw(graphicsHandler);
            }
        }
        if (!enemyBullets.isEmpty()) {
            for (Bullet bullet : enemyBullets) {
                bullet.draw(graphicsHandler);
            }
        }

        
    }

    public ArrayList<Bullet> getBullets(Ship ship) {
        if (ship == playerShip) {
            return playerBullets;
        } else {
            return enemyBullets;
        }
    }

    public void addBullet(Bullet bullet) {
        if (bullet.getShip() == playerShip) {
            playerBullets.add(bullet);
        } else {
            enemyBullets.add(bullet);
        }
    }

    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public ArrayList<Bullet> getPlayerBullets() {
        return playerBullets;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

}
