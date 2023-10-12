package Level;
// the data type of the sprite that appears when you speak to an npc
import Engine.GraphicsHandler;
import GameObject.Sprite;
import Engine.ImageLoader;
import Engine.Screen;

import java.awt.image.BufferedImage;

public class TextSpriteDisplay extends Screen {
    protected boolean isActive;
    protected Sprite portrait;
    protected String fileName;
    protected BufferedImage image;
    protected Map map;
    protected int spriteX;
    protected int spriteY;

    public TextSpriteDisplay(Map map){
        this.map = map;
        this.fileName = null;
        this.image = null;
        this.spriteX = 0;
        this.spriteY = 0;
        this.portrait = null;
    }

    public Sprite getTextSpriteDisplay(){
        return portrait;
    }

    public void changeSprite(String newSprite){
        fileName = newSprite;
        image = ImageLoader.load(newSprite);
        spriteX = image.getWidth();
        spriteY = image.getHeight();
        portrait = new Sprite(ImageLoader.load(fileName), spriteX, spriteY);
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void updateSprite(Boolean isTop){
        image = ImageLoader.load(fileName);
        if (isTop){
            spriteX = 22;
            spriteY = 460 - image.getHeight() * 3;
        }
        else {
            spriteX = 22;
            spriteY = 1800 - image.getHeight() * 3;
        }
        portrait = new Sprite(ImageLoader.load(fileName), spriteX, spriteY);
        portrait.setScale(3);
    }

    public void setPortraitIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isPortraitActive() {
        return isActive;
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
        if (!map.getCamera().isAtBottomOfMap()){
            updateSprite(true);
        }
        else {
            updateSprite(false);
        }
        portrait.draw(graphicsHandler);
    }
}