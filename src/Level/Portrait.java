package Level;

// the data type of the sprite that appears when you speak to an npc
import Engine.GraphicsHandler;
import GameObject.Sprite;
import Engine.ImageLoader;
import Engine.Screen;

import java.awt.image.BufferedImage;

public class Portrait extends Screen {
    protected boolean isActive;
    protected boolean rightSide;
    protected Sprite portrait;
    protected float imageX = 50;
    protected float imageY;
    protected int spriteHeight;
    protected int spriteWidth;
    protected int scale;
    protected BufferedImage image;
    protected String imageName;
    private Map map;

    public Portrait(Map map) {
        this.map = map;
        this.portrait = null;
        this.imageName = null;
        this.scale = 1;
    }

    public BufferedImage getPortrait() {
        return image;
    }

    public int getHeight() {
        return ImageLoader.load(imageName).getHeight(null);
    }

    public void setPortraitImage(String imageName) {
        this.imageName = imageName;
        image = ImageLoader.load(imageName);
        spriteHeight = image.getHeight();
        spriteWidth = image.getWidth();
    }

    public void setPortraitImage(String imageName, int scale) {
        this.imageName = imageName;
        image = ImageLoader.load(imageName);
        spriteHeight = image.getHeight();
        spriteWidth = image.getWidth();
        this.scale = scale;
        this.rightSide = false;
    }

    public void setPortraitImage(String imageName, boolean rightSide){
        this.imageName = imageName;
        image = ImageLoader.load(imageName);
        spriteHeight = image.getHeight();
        spriteWidth = image.getWidth();
        this.scale = 1;
        this.rightSide = rightSide;
    }

    public void setPortraitImage(String imageName, int scale, boolean rightSide) {
        this.imageName = imageName;
        image = ImageLoader.load(imageName);
        spriteHeight = image.getHeight();
        spriteWidth = image.getWidth();
        this.scale = scale;
        this.rightSide = rightSide;
    }

    public void setPortraitIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isPortraitActive() {
        return isActive;
    }

    public void updateImage(Boolean update) {
        if (update) {
            imageY = 460 - (spriteHeight * scale);
        } 
        else {
            imageY = 0 - (spriteHeight * scale);
        }
        if (rightSide)
            imageX = 767 - (spriteWidth * scale);
        portrait = new Sprite(image, imageX, imageY);
        portrait.setScale(scale);
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
        if (!map.getCamera().isAtBottomOfMap()) {
            updateImage(true);
        } else {
            updateImage(false);
        }
        portrait.draw(graphicsHandler);
    }
}