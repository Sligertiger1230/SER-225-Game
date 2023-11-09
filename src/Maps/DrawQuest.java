package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.*;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import NPCs.ProfessorJaiswal;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.Quests.JaiswalDrawQuestDrawRoom;
import Tilesets.CommonTileset;
import Utils.Point;

public class DrawQuest extends Map {

    private int currentChoice;

    public DrawQuest() {
        super("DrawQuest.txt", new CommonTileset());
        this.playerStartPosition = new Point(2, 2);
        this.idSwitch = 3;  
        this.mapInt = 3;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRedTile pushableRedTile = new PushableRedTile(getMapTile(4, 3).getLocation());
        enhancedMapTiles.add(pushableRedTile);

        PushableYellowTile pushableYellowTile = new PushableYellowTile(getMapTile(6, 3).getLocation());
        enhancedMapTiles.add(pushableYellowTile);

        PushableBlueTile pushableBlueTile = new PushableBlueTile(getMapTile(8, 3).getLocation());
        enhancedMapTiles.add(pushableBlueTile);

        return enhancedMapTiles;
    }

    public ArrayList<NPC> loadNPCs() {
        // adds professor jaiswal
        ProfessorJaiswal drJ = new ProfessorJaiswal(0, getMapTile(9, 1).getLocation());
        drJ.setInteractScript(new JaiswalDrawQuestDrawRoom());
        npcs.add(drJ);

        return npcs;
    }

    @Override
    public void loadScripts() {
        getMapTile(21, 22).setInteractScript(new ChangeMapScript(1));
    }
}