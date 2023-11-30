package Level;

import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Utils.Direction;
import Utils.Point;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected float runSpeed = 0;
    protected int interactionRange = 5;
    protected Direction currentWalkingXDirection;
    protected Direction currentWalkingYDirection;
    protected Direction lastWalkingXDirection;
    protected Direction lastWalkingYDirection;

    // values used to handle player movement
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected Direction lastMovementDirection;

    // classes that listen to player events can be added to this list
    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key MOVE_LEFT_KEY = Key.LEFT;
    protected Key MOVE_RIGHT_KEY = Key.RIGHT;
    protected Key MOVE_UP_KEY = Key.UP;
    protected Key MOVE_DOWN_KEY = Key.DOWN;
    protected Key INTERACT_KEY = Key.SPACE;
    protected Key SPRINT_KEY = Key.SHIFT;
    protected Key TOGGLE_BIKE = Key.E;
    protected Key EMOTE = Key.F;

    private ScheduledExecutorService soundExecutor;
    private boolean isPlayingSound;
    private Sound sounds;

    protected int wasInCCE;
    private int delay;

    public static Boolean onIce = false;

    public Boolean isBikeActive = false;

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName);
        facingDirection = Direction.RIGHT;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        this.affectedByTriggers = true;
        soundExecutor = Executors.newSingleThreadScheduledExecutor();
        isPlayingSound = false;
        sounds = new Sound();
    }

    public void update() {
        moveAmountX = 0;
        moveAmountY = 0;

        // if player is currently playing through level (has not won or lost)
        // update player's state and current actions, which includes things like
        // determining how much it should move each frame and if its walking or jumping
        do {
            previousPlayerState = playerState;
            handlePlayerState();
        } while (previousPlayerState != playerState);

        // move player with respect to map collisions based on how much player needs to
        // move this frame
        if (playerState != PlayerState.INTERACTING) {
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
        }

        handlePlayerAnimation();

        updateLockedKeys();

        // update player's animation
        super.update();

        // printPlayerLocation();
    }

    // based on player's current state, call appropriate player state handling
    // method
    protected void handlePlayerState() {
        switch (playerState) {
            case STANDING:
                playerStanding();
                break;
            case WALKING:
                playerWalking();
                break;
            case INTERACTING:
                playerInteracting();
                break;
            case EMOTING:
                playerEmoting();
                break;
        }
    }

    // player STANDING state logic
    protected void playerStanding() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }

        // if a walk key is pressed, player enters WALKING state
        else if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY) || Keyboard.isKeyDown(MOVE_UP_KEY)
                || Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            playerState = PlayerState.WALKING;
        }
        else if (Keyboard.isKeyDown(EMOTE)) {
        //    keyLocker.lockKey(EMOTE);
            playerState = PlayerState.EMOTING;
        }
    }

    protected void playerEmoting() {
        if(Keyboard.isKeyUp(EMOTE)){
            playerState = PlayerState.STANDING;
        }
    }

    // player WALKING state logic
    protected void playerWalking() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }

        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            if ((map.getFlagManager().isFlagSet("bikeActive")) && Keyboard.isKeyDown(TOGGLE_BIKE)) {
                moveAmountX -= walkSpeed * 6;
            } else if (Keyboard.isKeyDown(SPRINT_KEY)) {
                moveAmountX -= walkSpeed * 3;
            } else {
                moveAmountX -= walkSpeed;
            }
            facingDirection = Direction.LEFT;
            currentWalkingXDirection = Direction.LEFT;
            lastWalkingXDirection = Direction.LEFT;
        }

        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            // if shift is held down player will sprint
            if ((map.getFlagManager().isFlagSet("bikeActive")) && Keyboard.isKeyDown(TOGGLE_BIKE)) {
                moveAmountX += walkSpeed * 6;
            } else if (Keyboard.isKeyDown(SPRINT_KEY)) {
                moveAmountX += walkSpeed * 3;
            } else {
                moveAmountX += walkSpeed;
            }
            facingDirection = Direction.RIGHT;
            currentWalkingXDirection = Direction.RIGHT;
            lastWalkingXDirection = Direction.RIGHT;
        } else {
            currentWalkingXDirection = Direction.NONE;
        }

        if (Keyboard.isKeyDown(MOVE_UP_KEY)) {
            // if shift is held down player will sprint
            if ((map.getFlagManager().isFlagSet("bikeActive")) && Keyboard.isKeyDown(TOGGLE_BIKE)) {
                moveAmountY -= walkSpeed * 6;
            }
            else if (Keyboard.isKeyDown(SPRINT_KEY)) {
                moveAmountY -= walkSpeed * 3;
            } else {
                moveAmountY -= walkSpeed;
            }
            facingDirection = Direction.UP;
            currentWalkingYDirection = Direction.UP;
            lastWalkingYDirection = Direction.UP;
        } else if (Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            // if shift is held down player will sprint
            if ((map.getFlagManager().isFlagSet("bikeActive")) && Keyboard.isKeyDown(TOGGLE_BIKE)) {
                moveAmountY += walkSpeed * 6;
            }
            else if (Keyboard.isKeyDown(SPRINT_KEY)) {
                moveAmountY += walkSpeed * 3;
            } else {
                moveAmountY += walkSpeed;
            }
            facingDirection = Direction.DOWN;
            currentWalkingYDirection = Direction.DOWN;
            lastWalkingYDirection = Direction.DOWN;
        } else if (Keyboard.isKeyDown(EMOTE)) {
            playerState = PlayerState.EMOTING;
        } else {
            currentWalkingYDirection = Direction.NONE;
        }

        if ((currentWalkingXDirection == Direction.RIGHT || currentWalkingXDirection == Direction.LEFT)
                && currentWalkingYDirection == Direction.NONE) {
            lastWalkingYDirection = Direction.NONE;
        }

        if ((currentWalkingYDirection == Direction.UP || currentWalkingYDirection == Direction.DOWN)
                && currentWalkingXDirection == Direction.NONE) {
            lastWalkingXDirection = Direction.NONE;
        }

        if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY) && Keyboard.isKeyUp(MOVE_UP_KEY)
                && Keyboard.isKeyUp(MOVE_DOWN_KEY)) {
            playerState = PlayerState.STANDING;
        }
    }

    // player INTERACTING state logic -- intentionally does nothing so player is
    // locked in place while a script is running
    protected void playerInteracting() {
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(INTERACT_KEY) && playerState != PlayerState.INTERACTING) {
            keyLocker.unlockKey(INTERACT_KEY);
        }
        // if (Keyboard.isKeyUp(EMOTE) && playerState != PlayerState.EMOTING) {
        //     keyLocker.unlockKey(EMOTE);
        // }
    }

    // anything extra the player should do based on interactions can be handled here
    protected void handlePlayerAnimation() {
        if (playerState == PlayerState.STANDING) {
            // sets animation to a STAND animation based on which way player is facing
            //this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_UP" : "STAND_DOWN";
            if (facingDirection == Direction.RIGHT){
                this.currentAnimationName = "STAND_RIGHT";
            }
            else if (facingDirection == Direction.LEFT){
                this.currentAnimationName = "STAND_LEFT";
            }
            else if (facingDirection == Direction.UP){
                this.currentAnimationName = "STAND_UP";
            }
            else if (facingDirection == Direction.DOWN){
                this.currentAnimationName = "STAND_DOWN";
            }
        } else if (playerState == PlayerState.WALKING) {
            // sets animation to a WALK animation based on which way player is facing
            //this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
            if (facingDirection == Direction.RIGHT){
                if ((map.getFlagManager().isFlagSet("bikeActive")) && Keyboard.isKeyDown(TOGGLE_BIKE)) {
                    this.currentAnimationName = "BIKE_RIGHT";
                }
                else {
                    this.currentAnimationName = "WALK_RIGHT";
                }
            }
            else if (facingDirection == Direction.LEFT){
                if ((map.getFlagManager().isFlagSet("bikeActive")) && Keyboard.isKeyDown(TOGGLE_BIKE)) {
                    this.currentAnimationName = "BIKE_LEFT";
                }
                else {
                    this.currentAnimationName = "WALK_LEFT";
                }
            }
            else if (facingDirection == Direction.UP){
                if ((map.getFlagManager().isFlagSet("bikeActive")) && Keyboard.isKeyDown(TOGGLE_BIKE)) {
                    this.currentAnimationName = "BIKE_UP";
                }
                else {
                    this.currentAnimationName = "WALK_UP";
                }
            }
            else {
                if ((map.getFlagManager().isFlagSet("bikeActive")) && Keyboard.isKeyDown(TOGGLE_BIKE)) {
                    this.currentAnimationName = "BIKE_DOWN";
                }
                else {
                    this.currentAnimationName = "WALK_DOWN";
                }
            }
        } else if (playerState == PlayerState.INTERACTING) {
            // sets animation to STAND when player is interacting
            // player can be told to stand or walk during Script by using the "stand" and
            // "walk" methods
            if (facingDirection == Direction.RIGHT){
                this.currentAnimationName = "STAND_RIGHT";
            }
            else if (facingDirection == Direction.LEFT){
                this.currentAnimationName = "STAND_LEFT";
            }
            else if (facingDirection == Direction.UP){
                this.currentAnimationName = "STAND_UP";
            }
            else if (facingDirection == Direction.DOWN){
                this.currentAnimationName = "STAND_DOWN";
            }
        } else if (playerState == PlayerState.EMOTING){
            //plays the emote if the player is set to EMOTING
            this.currentAnimationName = "EMOTE";
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        if(onIce && walkSpeed == 0){
            playerState = playerState.STANDING;
        }else if(onIce){
            setPlayerState(PlayerState.INTERACTING);
            setCurrentAnimationName(getFacingDirection() == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT");
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        if(onIce && walkSpeed == 0){
            playerState = playerState.STANDING;

        }else if(onIce){
            setPlayerState(PlayerState.INTERACTING);
            setCurrentAnimationName(getFacingDirection() == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT");
        }
    }

    // other entities can call this method to hurt the player
    public void hurtPlayer(MapEntity mapEntity) {

    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public void addListener(PlayerListener listener) {
        listeners.add(listener);
    }

    public Rectangle getInteractionRange() {
        return new Rectangle(
                getBounds().getX1() - interactionRange,
                getBounds().getY1() - interactionRange,
                getBounds().getWidth() + (interactionRange * 2),
                getBounds().getHeight() + (interactionRange * 2));
    }

    public Key getInteractKey() {
        return INTERACT_KEY;
    }

    public Direction getCurrentWalkingXDirection() {
        return currentWalkingXDirection;
    }

    public Direction getCurrentWalkingYDirection() {
        return currentWalkingYDirection;
    }

    public Direction getLastWalkingXDirection() {
        return lastWalkingXDirection;
    }

    public int getWasInCCE(){
        return wasInCCE;
    }

    public void setWasInCCE(int wasInCCE){
        this.wasInCCE = wasInCCE;
    }


    public Direction getLastWalkingYDirection() {
        return lastWalkingYDirection;
    }

    public void stand(Direction direction) {
        facingDirection = direction;
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        } else if (direction == Direction.LEFT) {
            this.currentAnimationName = "STAND_LEFT";
        } else if (direction == Direction.UP) {
            this.currentAnimationName = "STAND_UP";
        } else if (direction == Direction.DOWN) {
            this.currentAnimationName = "STAND_DOWN";
        }
    }

    public void walk(Direction direction, float speed) {
        facingDirection = direction;
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "WALK_RIGHT";
        } else if (direction == Direction.LEFT) {
            this.currentAnimationName = "WALK_LEFT";
        } else {
            if (this.currentAnimationName.contains("RIGHT")) {
                this.currentAnimationName = "WALK_RIGHT";
            } else {
                this.currentAnimationName = "WALK_LEFT";
            }
        }
        if (direction == Direction.UP) {
            moveY(-speed);
        } else if (direction == Direction.DOWN) {
            moveY(speed);
        } else if (direction == Direction.LEFT) {
            moveX(-speed);
        } else if (direction == Direction.RIGHT) {
            moveX(speed);
        }
    }

    public void printPlayerLocation() {
    int xCoordinate = (int) getX() / 48;
    int yCoordinate = (int) getY() / 48;
    System.out.println("Player Location: X = " + xCoordinate + ", Y = " +
    yCoordinate);
    }
    
}