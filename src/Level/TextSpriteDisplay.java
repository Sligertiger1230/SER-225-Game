package Level;
// the data type of the sprite that appears when you speak to an npc
import Engine.GraphicsHandler;
import GameObject.Sprite;
import Engine.ImageLoader;
import Engine.Screen;

public class TextSpriteDisplay extends Screen {
    protected boolean isActive;
    protected Sprite portrait;
	protected int spriteWidth;
	protected int spriteHeight;
    protected int spriteX;
    protected int spriteY = 100;

    public TextSpriteDisplay(){
        this.portrait = new Sprite(ImageLoader.load("Walrus.png"), spriteX, spriteY);
    }

    public Sprite getTextSpriteDisplay(){
        return portrait;
    }

    public boolean setIsActive() {
        return true;
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        portrait.draw(graphicsHandler);
    }
}