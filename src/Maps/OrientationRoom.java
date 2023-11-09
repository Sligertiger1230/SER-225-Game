package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Judy;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.OrientationRoom.JudyScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class OrientationRoom extends Map{
    public OrientationRoom() {
        super("OrientationRoom.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(15, 10).getLocation(); 

        this.mapInt = 0;
        this.idSwitch = 0;
    }
    
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Judy judy = new Judy(0, getMapTile(10, 10).getLocation().subtractY(40));
        judy.setInteractScript(new JudyScript());
        npcs.add(judy);

        return npcs;

    }
     public void loadScripts() {
        getMapTile(1, 2).setInteractScript(new ChangeMapScript(1));

        getMapTile(2, 2).setInteractScript(new ChangeMapScript(1));

        getMapTile(3, 2).setInteractScript(new ChangeMapScript(1));

        getMapTile(4, 2).setInteractScript(new ChangeMapScript(1));


     }
    
}
