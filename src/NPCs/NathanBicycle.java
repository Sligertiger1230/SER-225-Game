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

public class NathanBicycle extends NPC {

    public NathanBicycle(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("nathanBicycle.png"), 20, 21), "STAND_LEFT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("STAND_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(3)
                                .withBounds(0, 0, 20, 21)
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
