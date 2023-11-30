package Asteroid;

import java.awt.Color;
import java.util.ArrayList;

import Asteroid.Ship.AimingDirection;
import Engine.GraphicsHandler;
import Screens.AsteroidScreen;
import SpriteFont.SpriteFont;

public class Asteroid {
    protected ArrayList<Bullet> playerBullets;
    protected ArrayList<Bullet> enemyBullets;
    protected ArrayList<Bullet> bulletCollection;
    protected ArrayList<Enemy> enemies;
    protected ArrayList<Enemy> group1;
    protected ArrayList<Enemy> group2;
    protected ArrayList<Enemy> enemyCollection;
    protected PlayerShip playerShip;

    protected EnemySpawner spawner1;
    protected EnemySpawner spawner2;

    protected SpriteFont healthIndic;

    protected AsteroidScreen screen;

    protected boolean[][] gridMap;

    protected int waveCounter = 0;
    protected int waveTic;

    public Asteroid(AsteroidScreen screen) {
        this.playerBullets = new ArrayList<Bullet>();
        this.enemyBullets = new ArrayList<Bullet>();
        this.bulletCollection = new ArrayList<Bullet>();
        this.enemies = new ArrayList<Enemy>();
        this.group1 = new ArrayList<Enemy>();
        this.group2 = new ArrayList<Enemy>();
        this.enemyCollection = new ArrayList<Enemy>();
        this.screen = screen;
        gridMap = new boolean[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                gridMap[x][y] = false;
            }
        }
        initialize();
    }

    public void initialize() {
        playerShip = new PlayerShip(400, 300, this);
        healthIndic = new SpriteFont("Health: " + playerShip.getHealth(), 600, 80, "Comic Sans", 20, Color.BLACK);
    }

    public void update() {
        playerShip.update();
        healthIndic = new SpriteFont("Health: " + playerShip.getHealth(), 600, 80, "Comic Sans", 20, Color.BLACK);
        if (playerShip.getHealth() <= 0) {
            screen.setAsteroidState(AsteroidState.DEAD);
        }
        if (!enemies.isEmpty()) {
            for (Enemy enemy : enemies) {
                enemy.update();
            }
        }
        if (!group1.isEmpty()) {
            for (Enemy enemy : group1) {
                enemy.update();
            }
        }
        if (!group2.isEmpty()) {
            for (Enemy enemy : group2) {
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
        if (group1.isEmpty() && group2.isEmpty()) {
            waveCounter++;
            spawner1 = new EnemySpawner(this, gridMap, group1, playerShip);
            spawner2 = new EnemySpawner(this, gridMap, group2, playerShip);
            
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    gridMap[x][y] = false;
                }
                waveTic = 0;
            }
        }
        spawner1.update(group1, waveTic);
        spawner2.update(group2, waveTic);
    }

    // screen dimensions
    // x is 24 - 759
    // y is 111 - 563

    public void bulletDump() {
        for (Bullet bullet : bulletCollection) {
            getBullets(bullet.getShip()).remove(bullet);
            updateBullets(bullet);
        }
        bulletCollection.clear();
    }

    public void enemyDump() {
        for (Enemy enemy : enemyCollection) {
            if (group1.contains(enemy)) {
                group1.remove(enemy);
            } else {
                group2.remove(enemy);
            }
            // enemies.remove(enemy);
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
        if (!group1.isEmpty()) {
            for (Enemy enemy : group1) {
                enemy.draw(graphicsHandler);
            }
        }
        if (!group2.isEmpty()) {
            for (Enemy enemy : group2) {
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
