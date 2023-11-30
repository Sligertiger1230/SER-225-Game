package Level;

import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Utils.Direction;

import java.util.HashMap;

// This class is a base class for all npcs in the game -- all npcs should extend from it
public class NPC extends MapEntity {
    protected int id = 0;
    private SpriteSheet spriteSheet;

    public NPC(int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
        this.id = id;
        this.spriteSheet = spriteSheet;
    }

    public NPC(int id, float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
        this.id = id;
    }

    public NPC(int id, float x, float y, Frame[] frames) {
        super(x, y, frames);
        this.id = id;
    }

    public NPC(int id, float x, float y, Frame frame) {
        super(x, y, frame);
        this.id = id;
    }

    public NPC(int id, float x, float y) {
        super(x, y);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void facePlayer(Player player) {
        if (Math.round(getBoundsY1()) + (getBounds().getHeight() / 2) > Math.round(player.getBoundsY2())
                && Math.round(player.getBoundsX1()) + (player.getBounds().getWidth() / 2) > Math.round(getBoundsX1())
                && Math.round(player.getBoundsX1()) + (player.getBounds().getWidth() / 2) < Math.round(getBoundsX2())) {
            this.currentAnimationName = "STAND_UP";
        } else if (Math.round(getBoundsY2()) - (getBounds().getHeight() / 2) < Math.round(player.getBoundsY1())
                && Math.round(player.getBoundsX1()) + (player.getBounds().getWidth() / 2) > Math.round(getBoundsX1())
                && Math.round(player.getBoundsX1()) + (player.getBounds().getWidth() / 2) < Math.round(getBoundsX2())) {
            this.currentAnimationName = "STAND_DOWN";
        } else if (Math.round(getBoundsX1()) + (getBounds().getWidth() / 2) > Math.round(player.getBoundsX2())
                && Math.round(player.getBoundsY1()) + (player.getBounds().getHeight() / 2) > Math.round(getBoundsY1())
                && Math.round(player.getBoundsY1()) + (player.getBounds().getHeight() / 2) < Math
                        .round(getBoundsY2())) {
            this.currentAnimationName = "STAND_LEFT";
        } else if (Math.round(getBoundsX2()) - (getBounds().getHeight() / 2) < Math.round(player.getBoundsX1())
                && Math.round(player.getBoundsY1()) + (player.getBounds().getHeight() / 2) > Math.round(getBoundsY1())
                && Math.round(player.getBoundsY1()) + (player.getBounds().getHeight() / 2) < Math
                        .round(getBoundsY2())) {
            this.currentAnimationName = "STAND_RIGHT";
        }
    }

    public void animate(Player player, int animationID){
        if (animationID == 1){
            this.currentAnimationName = "ANIMATION_1";
        }
        else if (animationID == 2){
            this.currentAnimationName = "ANIMATION_2";
        }
    }

    public void stand(Direction direction) {
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

    public void animate(int animation){
        switch (animation){
            case 0:
                this.currentAnimationName = "ANIMATION_0";
                break;
            case 1:
                this.currentAnimationName = "ANIMATION_1";
                break;
            case 2:
                this.currentAnimationName = "ANIMATION_2";
                break;
        }
    }

    public void walk(Direction direction, float speed) {
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "WALK_RIGHT";
        } else if (direction == Direction.LEFT) {
            this.currentAnimationName = "WALK_LEFT";
        } else if (direction == Direction.UP) {
            this.currentAnimationName = "WALK_UP";
        } else if (direction == Direction.DOWN) {
            this.currentAnimationName = "WALK_DOWN";
        } else {
            if (this.currentAnimationName.contains("RIGHT")) {
                this.currentAnimationName = "WALK_RIGHT";
            } else if (this.currentAnimationName.contains("LEFT")) {
                this.currentAnimationName = "WALK_LEFT";
            } else if (this.currentAnimationName.contains("UP")) {
                this.currentAnimationName = "WALK_UP";
            } else if (this.currentAnimationName.contains("DOWN")) {
                this.currentAnimationName = "WALK_DOWN";
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
        } else if (direction == Direction.UP) {
            moveY(-speed);
        }
    }

    public void update(Player player) {
        super.update();
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
