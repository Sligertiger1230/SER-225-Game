package Maps;

import Level.Map;
import Scripts.ChangeToTestMapScript.ChangeToTestMapScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class IceRink extends Map{
    public IceRink() {
        super("IceRink.txt", new CommonTileset());
        this.playerStartPosition = new Point(1, 11);
    }
    
}
