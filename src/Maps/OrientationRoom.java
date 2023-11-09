package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Judy;
import Scripts.OrientationRoom.JudyScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class OrientationRoom extends Map{
    public OrientationRoom() {
        super("OrientationRoom.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(15, 10).getLocation(); 

        this.mapInt = 2;
        this.idSwitch = 2;
    }
    
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Judy judy = new Judy(0, getMapTile(10, 10).getLocation().subtractY(40));
        judy.setInteractScript(new JudyScript());
        npcs.add(judy);

        return npcs;

    }
    
}
