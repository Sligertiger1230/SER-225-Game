package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class IceRink extends Map{
    public IceRink() {
        super("IceRink.txt", new CommonTileset());
        this.playerStartPosition = new Point(9, 19);

        this.mapInt = 2;
        this.idSwitch = 2;
    }
    
}
