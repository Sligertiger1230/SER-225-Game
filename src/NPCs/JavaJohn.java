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

public class JavaJohn extends NPC {

    public JavaJohn(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("javaJohn.png"), 21, 25), "STAND_DOWN");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("STAND_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(2, 0))
                                .withScale(3)
                                .withBounds(1, 11, 19, 16)
                                .build()
                });
                put("STAND_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(2, 0))
                                .withScale(3)
                                .withBounds(1, 11, 19, 16)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .build()
                });
                put("STAND_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0))
                        .withScale(3)
                        .withBounds(1, 11, 19, 16)
                        .build()
                });
                put("STAND_DOWN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,0))
                    .withScale(3)
                    .withBounds(1, 11, 19, 16)
                    .build()
                });
                put("WALK_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 2))
                                .withScale(3)
                                .withBounds(1, 11, 19, 16)
                                .build() 
                });
                put("ANIMATION_0", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 1))
                            .withScale(3)
                            .withBounds(1, 11, 19, 16)
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
