package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import NPCs.NPCBoy3;
import Scripts.NPCDialogue.NPCBoy3Script;
import Scripts.SimpleTextScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class CCEClassroom extends Map {
    public CCEClassroom() {
        super("CCEClassroom.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(1, 4).getLocation();
    }


    //loads NPC's into the CCE classroom
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // adds an npc boy
        NPCBoy3 npcBoy3 = new NPCBoy3(4, getMapTile(5, 12).getLocation());
        npcBoy3.setInteractScript(new NPCBoy3Script());
        npcs.add(npcBoy3);

        return npcs;
    }
}
