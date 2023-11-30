package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Direction;
import Utils.Point;

// This class is for the special rock in the map that can be moved around by the player
// when the player walks into it, it will be "pushed" forward in the same direction the player was moving in
public class PushableBlueTile extends EnhancedMapTile {

    private boolean questCompleted = false; // Flag to track completion

    public PushableBlueTile(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("CommonTileset.png"), 16, 16),
                TileType.NOT_PASSABLE);

        System.out.println("PushableBlueTile initial location: " + location);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (player.overlaps(this) && player.getPlayerState() == PlayerState.WALKING) {
            if (player.getCurrentWalkingXDirection() == Direction.LEFT) {
                if (canMoveLeft(player)) {
                    moveXHandleCollision(-1);
                }
            } else if (player.getCurrentWalkingXDirection() == Direction.RIGHT) {
                if (canMoveRight(player)) {
                    moveXHandleCollision(1);
                }
            }
            if (player.getCurrentWalkingYDirection() == Direction.UP) {
                if (canMoveUp(player)) {
                    moveYHandleCollision(-1);
                }
            } else if (player.getCurrentWalkingYDirection() == Direction.DOWN) {
                if (canMoveDown(player)) {
                    moveYHandleCollision(1);
                }
            }
        }

        if (!questCompleted && x >= 706 && x <= 726 && y >= 566 && y <= 586) {
            System.out.println("Blue Complete!");
            questCompleted = true; // Set the flag to indicate quest completion
        }

        // System.out.println("PushableBlueTile current location: " + new Point(x, y));
    }

    private boolean canMoveLeft(Player player) {
        return player.getBoundsX1() <= getBoundsX2() + 1 && player.getBoundsX2() > getBoundsX2() && canMoveX(player);
    }

    private boolean canMoveRight(Player player) {
        return player.getBoundsX2() + 1 >= getBoundsX1() && player.getBoundsX1() < getBoundsX1() && canMoveX(player);
    }

    private boolean canMoveX(Player player) {
        return (player.getBoundsY1() <= getBoundsY2() && player.getBoundsY2() >= getBoundsY2()) ||
                (player.getBoundsY2() >= getBoundsY1() && player.getBoundsY1() <= getBoundsY1()) ||
                (player.getBoundsY2() <= getBoundsY2() && player.getBoundsY1() >= getBoundsY1());
    }

    private boolean canMoveUp(Player player) {
        return player.getBoundsY1() <= getBoundsY2() + 1 && player.getBoundsY2() > getBoundsY2() && canMoveY(player);
    }

    private boolean canMoveDown(Player player) {
        return player.getBoundsY2() + 1 >= getBoundsY1() && player.getBoundsY1() < getBoundsY1() && canMoveY(player);
    }

    private boolean canMoveY(Player player) {
        return (player.getBoundsX1() <= getBoundsX2() && player.getBoundsX2() >= getBoundsX2()) ||
                (player.getBoundsX2() >= getBoundsX1() && player.getBoundsX1() <= getBoundsX1()) ||
                (player.getBoundsX2() <= getBoundsX2() && player.getBoundsX1() >= getBoundsX1());
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(2, 16))
                .withScale(3)
                .build();
        return new GameObject(x, y, frame);
    }
}
