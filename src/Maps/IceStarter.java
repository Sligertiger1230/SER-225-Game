package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class IceStarter extends Map{
    public IceStarter() {
        super("iceStarter.txt", new CommonTileset());
        //this.playerStartPosition = getMapTile(1, 4).getLocation();
        //this.mapInt = 4;
        //this.idSwitch = 4;
    }
}
