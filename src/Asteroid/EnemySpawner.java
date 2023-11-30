package Asteroid;

import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner {
    private Asteroid aster;
    private static Random rand = new Random();
    private ArrayList<Enemy> enemies;
    private PlayerShip ship;

    private boolean[][] gridMap;
    private boolean isFinishedSwinging;
    private int swingTic;

    private Direction enemyDir;
    private Direction swingDir;

    private float startX;
    private float startY;

    public EnemySpawner(Asteroid aster, boolean[][] gridMap, ArrayList<Enemy> enemies, PlayerShip ship) {
        this.aster = aster;
        this.gridMap = gridMap;
        this.isFinishedSwinging = false;
        this.startX = 0;
        this.startY = 0;
        this.swingTic = 0;
        this.enemies = enemies;
        this.ship = ship;
        initialize();
        spawn();
    }

    public EnemySpawner(Asteroid aster, ArrayList<Enemy> enemies, PlayerShip ship) {
        this.aster = aster;
        this.enemies = enemies;
        this.ship = ship;
        this.enemyDir = Direction.CENTER;
        spawn();
    }

    // screen dimensions
    // x is 24 - 759
    // y is 111 - 563
    // the sides are 452
    // (452 isnt that divisible so change it to 450 for sanity)
    // top and bottom are 735
    // (735 simplified to 730)
    // split sides and corners with unique sizes
    // each corner has a length of 45 and a width of 73
    // sides have a length of 360
    // top and bottom have a length of 584

    // left wall/right wall enemy final swing locations
    // first enemy location is y 157
    // last enemy location is y 504
    // distance is 347

    // up wall/bottom wall enemy final swing locations
    // first enemy location is 97
    // last enemy location is 669
    // distance is 572

    // speed
    // units per tic
    // how many tics to swing
    // left wall/right wall

    public void initialize() {
        // an enemy spawn wave needs three things
        // a wall to spawn on
        // a direction to swing from
        // and a direction to move back and forth from

        // return all open walls
        ArrayList<Direction> openWalls = getOpenDirections();
        // pick random wall
        enemyDir = findDir(openWalls);
        gridMap[enemyDir.getX()][enemyDir.getY()] = true;

        // pick random swing in direction
        swingDir = enemySwing();
    }

    public void spawn() {
        int enemyCount = 7;
        switch (enemyDir) {
            case LEFT:
                startX = 37;
                if (swingDir == Direction.UP) {
                    // start y is 98
                    // x is 37
                    for (int index = 0; index <= enemyCount; index++) {
                        int currY = 98 - (index * 50);
                        // System.out.println("current X: " + startX + " current Y: " + currY);
                        enemies.add(new Enemy(startX, currY, "STAND_RIGHT", ship, aster));
                    }
                } else if (swingDir == Direction.DOWN) {
                    // start y is 563
                    // x is 37
                    for (int index = 0; index <= enemyCount; index++) {
                        int currY = 563 + (index * 50);
                        // System.out.println("current X: " + startX + " current Y: " + currY);
                        enemies.add(new Enemy(startX, currY, "STAND_RIGHT", ship, aster));
                    }
                }
                break;
            case RIGHT:
                startX = 731;
                if (swingDir == Direction.UP) {
                    // start y is 98
                    // x is 37
                    for (int index = 0; index <= enemyCount; index++) {
                        int currY = 98 - (index * 50);
                        // System.out.println("current X: " + startX + " current Y: " + currY);
                        enemies.add(new Enemy(startX, currY, "STAND_LEFT", ship, aster));
                    }
                } else if (swingDir == Direction.DOWN) {
                    // start y is 563
                    // x is 37
                    for (int index = 0; index <= enemyCount; index++) {
                        int currY = 563 + (index * 50);
                        // System.out.println("current X: " + startX + " current Y: " + currY);
                        enemies.add(new Enemy(startX, currY, "STAND_LEFT", ship, aster));
                    }
                }
                break;
            case UP:
                startY = 124;
                if (swingDir == Direction.LEFT) {
                    // start x is 11
                    for (int index = 0; index <= enemyCount; index++) {
                        int currX = 11 - (index * 50);
                        // System.out.println("current X: " + currX + " current Y: " + startY);
                        enemies.add(new Enemy(currX, startY, "STAND_DOWN", ship, aster));
                    }
                } else if (swingDir == Direction.RIGHT) {
                    // start x is 759
                    for (int index = 0; index <= enemyCount; index++) {
                        int currX = 759 + (index * 50);
                        // System.out.println("current X: " + currX + " current Y: " + startY);
                        enemies.add(new Enemy(currX, startY, "STAND_DOWN", ship, aster));
                    }
                }
                break;
            case DOWN:
                startY = 537;
                if (swingDir == Direction.LEFT) {
                    // start x is 11
                    for (int index = 0; index <= enemyCount; index++) {
                        int currX = 11 - (index * 50);
                        // System.out.println("current X: " + currX + " current Y: " + startY);
                        enemies.add(new Enemy(currX, startY, "STAND_UP", ship, aster));
                    }
                } else if (swingDir == Direction.RIGHT) {
                    // start x is 759
                    for (int index = 0; index <= enemyCount; index++) {
                        int currX = 759 + (index * 50);
                        // System.out.println("current X: " + currX + " current Y: " + startY);
                        enemies.add(new Enemy(currX, startY, "STAND_UP", ship, aster));
                    }
                }
                break;
            case CENTER:
                for (int index = 0; index < 4; index++) {
                    double angle = 90 * index;
                    String startingAnimation;
                    if (angle < 45) {
                        startingAnimation = "STAND_LEFT";
                    } else if (angle < 135) {
                        startingAnimation = "STAND_DOWN";
                    } else if (angle < 225) {
                        startingAnimation = "STAND_RIGHT";
                    } else if (angle < 315) {
                        startingAnimation = "STAND_UP";
                    } else {
                        startingAnimation = "STAND_LEFT";
                    }

                    double circleX = 200 * Math.cos(Math.toRadians(angle)) + 389;
                    double circleY = 200 * Math.sin(Math.toRadians(angle)) + 336;

                    enemies.add(new Enemy((float) circleX, (float) circleY, startingAnimation, ship, aster));
                }
                break;
        }
    }

    public void update(ArrayList<Enemy> enemyGroup, int waveTic) {
        if (!isFinishedSwinging) {
            swingTic++;
            for (Enemy enemy : enemyGroup) {
                switch (enemyDir) {
                    case LEFT:
                    case RIGHT:
                        if (swingDir == Direction.UP) {
                            enemy.moveY(14);
                        } else if (swingDir == Direction.DOWN) {
                            enemy.moveY(-14);
                        }
                        if (swingTic == 29) {
                            isFinishedSwinging = true;
                        }
                        break;
                    case UP:
                    case DOWN:
                        if (swingDir == Direction.LEFT) {
                            enemy.moveX(14);
                        } else if (swingDir == Direction.RIGHT) {
                            enemy.moveX(-14);
                        }
                        if (swingTic == 38) {
                            isFinishedSwinging = true;
                        }
                        break;
                    case CENTER:
                        isFinishedSwinging = false;
                        break;
                }
            }

        } else {
            for (Enemy enemy : enemyGroup) {
                switch (enemyDir) {
                    case LEFT:
                    case RIGHT:
                        if (waveTic % 40 < 20) {
                            enemy.moveY(2);
                        } else {
                            enemy.moveY(-2);
                        }
                        break;
                    case UP:
                    case DOWN:
                        if (waveTic % 40 < 20) {
                            enemy.moveX(2);
                        } else {
                            enemy.moveX(-2);
                        }
                        break;
                    case CENTER:
                        break;
                }
            }
        }
    }

    

    public Direction enemySwing() {
        int randSwingIndex = rand.nextInt(2);
        System.out.println();
        switch (enemyDir) {
            case LEFT:
            case RIGHT:
                if (randSwingIndex == 0) {
                    if (gridMap[enemyDir.getX()][enemyDir.getY() - 1]) {
                        gridMap[enemyDir.getX()][enemyDir.getY() + 1] = true;
                        return Direction.DOWN;
                    } else {
                        gridMap[enemyDir.getX()][enemyDir.getY() - 1] = true;
                        return Direction.UP;
                    }
                } else {
                    if (gridMap[enemyDir.getX()][enemyDir.getY() + 1]) {
                        gridMap[enemyDir.getX()][enemyDir.getY() - 1] = true;
                        return Direction.UP;
                    } else {
                        gridMap[enemyDir.getX()][enemyDir.getY() + 1] = true;
                        return Direction.DOWN;
                    }
                }
            case UP:
            case DOWN:
                if (randSwingIndex == 0) {
                    if (gridMap[enemyDir.getX() - 1][enemyDir.getY()]) {
                        gridMap[enemyDir.getX() + 1][enemyDir.getY()] = true;
                        return Direction.RIGHT;
                    } else {
                        gridMap[enemyDir.getX() - 1][enemyDir.getY()] = true;
                        return Direction.LEFT;
                    }
                } else {
                    if (gridMap[enemyDir.getX() + 1][enemyDir.getY()]) {
                        gridMap[enemyDir.getX() - 1][enemyDir.getY()] = true;
                        return Direction.LEFT;
                    } else {
                        gridMap[enemyDir.getX() + 1][enemyDir.getY()] = true;
                        return Direction.RIGHT;
                    }
                }
            default:
                return null;
        }
    }

    public ArrayList<Direction> getOpenDirections() {
        ArrayList<Direction> openWalls = new ArrayList<Direction>();
        Direction[] directions = Direction.values();
        for (Direction direction : directions) {
            if (!gridMap[direction.getX()][direction.getY()] && direction != Direction.CENTER) {
                openWalls.add(direction);
            }
        }
        return openWalls;
    }

    public Direction findDir(ArrayList<Direction> openWalls) {
        int randIndex = rand.nextInt(openWalls.size());
        Direction dirFound = openWalls.get(randIndex);
        return dirFound;
    }

    public enum Direction {
        LEFT(0, 1),
        RIGHT(2, 1),
        UP(1, 0),
        DOWN(1, 2),
        CENTER(1, 1);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
