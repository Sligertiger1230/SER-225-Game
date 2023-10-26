package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class Nathan extends NPC {

    public Nathan(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("nathan.png"), 19, 23), "STAND_LEFT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                //s
                put("STAND_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(3)
                                .withBounds(0, 0, 18, 22)
                                .build()
                });
                put("WALK_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 2), 8)
                                .withScale(3)
                                .withBounds(1, 1, 19, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                                .withScale(3)
                                .withBounds(1, 1, 19, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                                .withScale(3)
                                .withBounds(1, 1, 19, 22)
                                .build()
                });
                put("WALK_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                                .withScale(3)
                                .withBounds(1, 1, 19, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                                .withScale(3)
                                .withBounds(1, 1, 19, 22)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build(),
                });
            }
        };

    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

}
