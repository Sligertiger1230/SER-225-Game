package Maps;

import EnhancedMapTiles.PushableRock;
import Level.Audio;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.Walrus;
import NPCs.JavaJohn;
import Scripts.SimpleTextScript;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.Quests.placeHolderScript;
import Scripts.TestMap.DinoScript;
//import Scripts.TestMap.JavaJohnScript;
import Scripts.TestMap.LostBallScript;
import Scripts.TestMap.TeleportScript;
import Scripts.TestMap.TreeScript;
import Scripts.TestMap.WalrusScript;
import Tilesets.CommonTileset;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
        // Playing background music (September 29th)
        Audio.playBackgroundMusic();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        enhancedMapTiles.add(pushableRock);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        // adds javaJohn!
        JavaJohn javaJohn = new JavaJohn(3, getMapTile(37, 7).getLocation());
        javaJohn.setExistenceFlag("hasTalkedToJavaJohn");
        //javaJohn.setInteractScript(new JavaJohnScript());
        npcs.add(javaJohn);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        // triggers for demonstration quest
        triggers.add(new Trigger(100, 40, 20, 20, new placeHolderScript(), "placeholder1"));
        triggers.add(new Trigger(20, 40, 20, 20, new placeHolderScript(), "placeholder2"));
        // base game triggers
        triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        return triggers;
    }

    // updates the triggers
    @Override
    public ArrayList<Trigger> updateTriggers() {
        ArrayList<Trigger> newTriggers = new ArrayList<>();

        // searches each quest menu quest with index
        for (int index = 0; index < getQuestMenu().getQuests().size(); index++) {
            // if a quest in quest menu is still a new quest
            if (getQuestMenu().isNewQuestStatus(index)) {
                // go through each trigger in the quest
                for (int triggerIndex = 0; triggerIndex < getQuestMenu().getTriggerList(index).size(); triggerIndex++) {
                    // adds the trigger to newTriggers
                    newTriggers.add(getQuestMenu().getTriggerList(index).get(triggerIndex));
                }
                // sets the quest just added to false
                getQuestMenu().setNewQuestStatus(index, false);
            }
        }

        return newTriggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        getMapTile(2, 6).setInteractScript(new TreeScript());

        getMapTile(1, 1).setInteractScript(new TeleportScript(32, 23));

        getMapTile(32, 25).setInteractScript(new TeleportScript(2, 2));

         getMapTile(100, 59).setInteractScript(new ChangeMapScript(2, 2));
    }
}
