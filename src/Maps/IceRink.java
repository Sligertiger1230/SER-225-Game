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
        this.playerStartPosition = getMapTile(1, 4).getLocation();
        this.mapInt = 2;
        this.idSwitch = 2;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        triggers.add(new Trigger(50, 225, 100, 25, new SlipperyIce(), "IsOnIce"));
        triggers.add(new Trigger(50, 500, 846, 1000, new Slide(), "IsOnSlidingIce"));
        triggers.add(new Trigger(432, 1152, 24, 24, new IceChangeMapScript(1), "NotOnICE"));
        return triggers;
    }

}
