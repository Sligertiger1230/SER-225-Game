package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import Level.FlagManager;
import NPCs.Walrus;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.IceRink.IceChangeMapScript;
import Scripts.IceRink.Slide;
import Scripts.IceRink.SlipperyIce;
import Scripts.Quests.IceRinkBobcatQuest;
import Tilesets.CommonTileset;

public class IceRinkNPC extends Map{
     public IceRinkNPC() {
        super("IceRinkNPC.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(1, 4).getLocation();
        this.mapInt = 2;
        this.idSwitch = 2;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        Walrus walrus = new Walrus(0, getMapTile(8, 8).getLocation().subtractY(40));
        walrus.setInteractScript(new IceRinkBobcatQuest());


        npcs.add(walrus);
        return npcs;
    }

    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        triggers.add(new Trigger(336, 768, 192, 24, new ChangeMapScript(9), "Ice1"));
        triggers.add(new Trigger(336, 768, 192, 24, new ChangeMapScript(8), "Ice2"));
        triggers.add(new Trigger(336, 768, 192, 24, new ChangeMapScript(7), "Ice3"));
        return triggers;
    }
}
