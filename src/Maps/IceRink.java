package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.IceRink.IceChangeMapScript;
import Scripts.IceRink.Slide;
import Scripts.IceRink.SlipperyIce;
import Scripts.Quests.PubSafetyDectScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class IceRink extends Map{
    public IceRink() {
        super("IceRink.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(9, 15).getLocation();
        this.mapInt = 7;
        this.idSwitch = 7;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        triggers.add(new Trigger(50, 225, 100, 25, new SlipperyIce(), "IsOnIce"));
        triggers.add(new Trigger(50, 500, 846, 400, new Slide(), "IsOnSlidingIce"));
        return triggers;
    }

    public void loadScripts() {

        getMapTile(9, 30).setInteractScript(new IceChangeMapScript(2));
    }

}
