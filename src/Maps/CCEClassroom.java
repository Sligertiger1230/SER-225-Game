package Maps;

import Level.Map;
import Scripts.SimpleTextScript;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.ChangeToTestMapScript.ChangeToTestMapScript;
import Scripts.TestMap.TreeScript;
import Tilesets.CommonTileset;

public class CCEClassroom extends Map{
    public CCEClassroom() {
        super("CCEClassroom.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(1, 4).getLocation();
        this.mapInt = 1;
        this.idSwitch = 1;
    }
    
    public void loadScripts() {
        getMapTile(1, 2).setInteractScript(new ChangeToTestMapScript());
    }
}
