package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class DrawQuest extends Map {
    public DrawQuest() {
        super("DrawQuest.txt", new CommonTileset());
        this.playerStartPosition = new Point(1, 11);
    }
}
