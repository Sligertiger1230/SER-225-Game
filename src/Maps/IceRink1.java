package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.IceRink.IceChangeMapScript;
import Scripts.IceRink.Slide;
import Scripts.IceRink.SlipperyIce;
import Scripts.TestMap.TreeScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class IceRink1 extends Map{
     public IceRink1() {
        super("IceRink1.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(9, 15).getLocation();
        this.mapInt = 9;
        this.idSwitch = 9;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        triggers.add(new Trigger(50, 225, 100, 25, new SlipperyIce(), "IsOnIce"));
        triggers.add(new Trigger(50, 500, 846, 400, new Slide(), "IsOnSlidingIce"));
        return triggers;
    }

    @Override
    public void loadScripts() {

        getMapTile(9, 30).setInteractScript(new IceChangeMapScript(2));
    }
}
