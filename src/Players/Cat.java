package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;

import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations
public class Cat extends Player {

    public Cat(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("riley.png"), 24, 24), x, y, "STAND_RIGHT");
        walkSpeed = 2.3f;
    }

    public void update() {
        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
        //Standing animations
            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 200)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 5)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .build(),
            });

            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 5)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
            });

            put("STAND_DOWN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0),200)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 5)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .build(),
            });

            put("STAND_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1))
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .build()
            });

            //Walking animations

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .build(),
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                            .withScale(3)
                            .withBounds(6, 12, 12, 7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
            });

            put("WALK_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                        .withScale(3)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
            });
            put("WALK_UP", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
            });

            //biking animation
            put("BIKE_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 3), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 3), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3 ), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build(),
            });
            put("BIKE_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 3), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 3), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
            });
            put("BIKE_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 3), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 3), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build(),
            });
            put("BIKE_UP", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(3, 1), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 2), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 1), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 2), 10)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build(),
            });
            put("EMOTE", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 7)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 4), 12)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 5)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                //has left ground
                new FrameBuilder(spriteSheet.getSprite(1, 4), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 4), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 4), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 5), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 5), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 5), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 5), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 4), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 4), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 4), 3)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 7)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                //has landed and started spinning
                new FrameBuilder(spriteSheet.getSprite(0, 2), 4)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 4)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 2), 4)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                //facing back
                new FrameBuilder(spriteSheet.getSprite(0, 1), 4)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                //turning to left
                new FrameBuilder(spriteSheet.getSprite(2, 2), 4)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 4)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 4)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build(),
                //NOW STRIKE A POSE
                new FrameBuilder(spriteSheet.getSprite(0, 0), 4)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 0), 15)
                        .withScale(3)
                        .withBounds(6, 12, 12, 7)
                        .build(),
            });
        }};
    }
}
