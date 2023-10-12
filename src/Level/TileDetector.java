package Level;

import Utils.Point;

public class TileDetector {
    private Map map;

    public TileDetector(Map map) {
        this.map = map;
    }

    public TileType detectTileTypeAtPosition(float x, float y) {
        Point tileIndices = map.getTileIndexByPosition(x, y);
        
        MapTile currentTile = map.getMapTile((int) tileIndices.x, (int) tileIndices.y);

        if (currentTile != null) {
            return currentTile.getTileType();
        }

        return TileType.NOT_PASSABLE;
    }
}
