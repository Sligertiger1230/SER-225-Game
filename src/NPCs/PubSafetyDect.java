package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.util.HashMap;

public class PubSafetyDect extends NPC {
    public PubSafetyDect(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("PubSafetyDectDS.png"), 19, 29), "STAND_DOWN");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("STAND_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build()
                });
                put("STAND_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(2, 0))
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build()
                });
                put("STAND_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 0))
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build()
                });
                put("STAND_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 0))
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build()
                });
                put("WALK_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(2, 1), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(2, 1), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build()
                });
                put("WALK_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build()
                });
                put("WALK_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .build()
                });
                put("WALK_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                .withScale(3)
                                .withBounds(3, 10, 15, 12)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build()
                });
            }
        };
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
