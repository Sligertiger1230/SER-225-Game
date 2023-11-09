package Level;

import java.awt.Color;

import Engine.GraphicsHandler;

public class MiniMap {
    private Camera camera;
    private int miniMapX; // X position of the mini-map on the screen
    private int miniMapY; // Y position of the mini-map on the screen
    private int miniMapWidth; // Width of the mini-map in pixels
    private int miniMapHeight; // Height of the mini-map in pixels

    public MiniMap(Camera camera, int miniMapX, int miniMapY, int miniMapWidth, int miniMapHeight) {
        this.camera = camera;
        this.miniMapX = miniMapX;
        this.miniMapY = miniMapY;
        this.miniMapWidth = miniMapWidth;
        this.miniMapHeight = miniMapHeight;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(miniMapX, miniMapY, miniMapWidth, miniMapHeight, Color.BLACK); // Draw the
                                                                                                           // background
                                                                                                           // of the
                                                                                                           // minimap

        // Calculate the mini-map's scale based on the camera's dimensions
        float scaleX = (float) miniMapWidth / camera.getEndBoundX();
        float scaleY = (float) miniMapHeight / camera.getEndBoundY();

        for (EnhancedMapTile tile : camera.getActiveEnhancedMapTiles()) {
            int miniMapTileX = (int) (miniMapX + tile.getX() * scaleX);
            int miniMapTileY = (int) (miniMapY + tile.getY() * scaleY);
            int miniMapTileWidth = (int) (tile.getWidth() * scaleX);
            int miniMapTileHeight = (int) (tile.getHeight() * scaleY);

            graphicsHandler.drawFilledRectangle(miniMapTileX, miniMapTileY, miniMapTileWidth, miniMapTileHeight,
                    Color.RED); // Draw a red rectangle for active enhanced map tiles
        }

        for (NPC npc : camera.getActiveNPCs()) {
            int miniMapNpcX = (int) (miniMapX + npc.getX() * scaleX);
            int miniMapNpcY = (int) (miniMapY + npc.getY() * scaleY);
            int miniMapNpcWidth = (int) (npc.getWidth() * scaleX);
            int miniMapNpcHeight = (int) (npc.getHeight() * scaleY);

            graphicsHandler.drawFilledRectangle(miniMapNpcX, miniMapNpcY, miniMapNpcWidth, miniMapNpcHeight,
                    Color.BLUE); // Draw a blue rectangle for active NPCs
        }
    }
}
