package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.ScreenManager;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite banner;
    private Sprite background;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());
        banner = new Sprite(ImageLoader.load("gameLogo.png"), (ScreenManager.getScreenWidth() / 2) - 325, 30);
        background = new Sprite(ImageLoader.load("QuinnipiacPixel.png"), 0, 0);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        background.draw(graphicsHandler);
        banner.draw(graphicsHandler);
    }
}
