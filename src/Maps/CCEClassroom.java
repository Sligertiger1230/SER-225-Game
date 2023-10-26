package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import NPCs.NPCBoy3;
import Scripts.NPCDialogue.NPCBoy3Script;
import Scripts.SimpleTextScript;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.ChangeToTestMapScript.ChangeToTestMapScript;
import Tilesets.CommonTileset;

public class CCEClassroom extends Map {
    public CCEClassroom() {
        super("CCEClassroom.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(1, 4).getLocation();
        this.mapInt = 1;
        this.idSwitch = 1;
    }
    
    public void loadScripts() {
        getMapTile(1, 2).setInteractScript(new ChangeToTestMapScript());
    }


    //loads NPC's into the CCE classroom
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // adds an npc boy
        NPCBoy3 npcBoy3 = new NPCBoy3(2, getMapTile(5, 12).getLocation());
        npcBoy3.setInteractScript(new NPCBoy3Script());
        npcs.add(npcBoy3);

        return npcs;
    }
}
