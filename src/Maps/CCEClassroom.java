package Maps;

import Level.Map;
import Scripts.ChangeToTestMapScript.ChangeToTestMapScript;
import Tilesets.CommonTileset;

public class CCEClassroom extends Map{
    public CCEClassroom() {
        super("CCEClassroom.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(1, 3).getLocation();
    }

    public void loadScripts(){
         getMapTile(8, 5).setInteractScript(new ChangeToTestMapScript());
    }
}
