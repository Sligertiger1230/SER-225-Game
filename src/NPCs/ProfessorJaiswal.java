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

public class ProfessorJaiswal extends NPC {

        public ProfessorJaiswal(int id, Point location) {
                super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("drJ.png"), 62, 68),
                                "STAND_DOWN");
        }

        @Override
        public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
                return new HashMap<String, Frame[]>() {
                        {
                                put("STAND_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(0, 0))
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build()
                                });
                                put("STAND_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(1, 0))
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 72, 62, 138)
                                                                .build()
                                });
                                put("STAND_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(2, 0))
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 141, 62, 208)
                                                                .build()
                                });
                                put("STAND_UP", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(0, 0))
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 211, 62, 280)
                                                                .build()
                                });
                                put("WALK_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 62)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(0, 3), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                });
                                put("WALK_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 62)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                });
                                put("WALK_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(2, 1), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 62)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(2, 3), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                });
                                put("WALK_UP", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(3, 1), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 62)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(3, 2), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(3, 3), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(3, 2), 14)
                                                                .withScale((float) 1.5)
                                                                .withBounds(1, 1, 62, 68)
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
