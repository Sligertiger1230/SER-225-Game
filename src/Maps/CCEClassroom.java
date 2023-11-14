package Maps;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Level.MiniMap;
import Level.Map;
import Level.NPC;
import NPCs.NPCBoy3;
import NPCs.ProfessorJaiswal;
import NPCs.Webby;
import Scripts.NPCDialogue.NPCBoy3Script;
import Scripts.Quests.JaiswalDrawQuestCCE;
import Scripts.Quests.WebbyScript;
import Scripts.CCEClassroom.ChangeMapScript;
import Tilesets.CommonTileset;

public class CCEClassroom extends Map {
    private MiniMap miniMap;

    public CCEClassroom() {
        super("CCEClassroom.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(1, 4).getLocation();
        this.mapInt = 1;
        this.idSwitch = 1;

        miniMap = new MiniMap(camera, endBoundY, endBoundX, width, height);
    }

    // loads NPC's into the CCE classroom
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // adds an npc boy
        NPCBoy3 npcBoy3 = new NPCBoy3(2, getMapTile(5, 12).getLocation());
        npcBoy3.setInteractScript(new NPCBoy3Script());
        npcs.add(npcBoy3);

        // adds professor jaiswal
        ProfessorJaiswal drJ = new ProfessorJaiswal(0, getMapTile(8, 3).getLocation());
        drJ.setInteractScript(new JaiswalDrawQuestCCE());
        npcs.add(drJ);

        Webby webby = new Webby(1, getMapTile(8, 13).getLocation());
        webby.setInteractScript(new WebbyScript());
        npcs.add(webby);

        return npcs;
    }

    public void loadScripts() {
        getMapTile(1, 2).setInteractScript(new ChangeMapScript(0));

        getMapTile(15, 2).setInteractScript(new ChangeMapScript(3));
    }

    public void draw(GraphicsHandler graphicsHandler) {
        miniMap.draw(graphicsHandler);
    }
}
