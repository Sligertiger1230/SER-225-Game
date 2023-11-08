package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class OrientationRoom extends Map{
    public OrientationRoom() {
        super("OrientationRoom.txt", new CommonTileset());
        this.playerStartPosition = new Point(9, 19);

        this.mapInt = 2;
        this.idSwitch = 2;
    }
    
}
