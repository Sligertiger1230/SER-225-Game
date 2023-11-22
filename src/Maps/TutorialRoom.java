package Maps;

import java.util.ArrayList;
import Level.Map;
import Level.Trigger;
import Tilesets.CommonTileset;

public class TutorialRoom extends Map{
    public TutorialRoom() {
        super("TutorialRoom.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(16, 12).getLocation();
        this.mapInt = 6;
        this.idSwitch = 6;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        return triggers;
    }

}