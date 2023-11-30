package Maps;

import java.util.ArrayList;
import Level.Map;
import Level.Trigger;
import Scripts.IceRink.JudyChangeMapScript;
import Scripts.TutorialRoom.TutorialChangeMapScript;
import Scripts.TutorialRoom.TutorialTextScript;
import Tilesets.CommonTileset;
import Level.Script;
import Level.ScriptState;
import Utils.Point;;

public class TutorialRoom extends Map {


    public TutorialRoom() {
        super("TutorialRoom.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(16, 12).getLocation();
        this.mapInt = 5;
        this.idSwitch = 5;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
    ArrayList<Trigger> triggers = new ArrayList<>();
    Point point = getMapTile(16, 12).getLocation();
    triggers.add(new Trigger((int)point.x, (int)point.y, 40, 40, new TutorialTextScript(),
     "isInTutorialRoom"));

    triggers.add(new Trigger((int)getMapTile(15, 8).getX(), (int)getMapTile(15, 8).getY(), 45, 45, new TutorialChangeMapScript(4), "returningFromTutorial")) ;

    return triggers; 
    }
}
