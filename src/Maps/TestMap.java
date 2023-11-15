package Maps;

import EnhancedMapTiles.PushableRock;
import Level.EnhancedMapTile;
import Level.FlagManager;
import Level.Map;
import Level.NPC;
// import Level.SoundManager;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.Walrus;
import NPCs.WalrusFish;
import NPCs.WalrusPurpFish;
import NPCs.Webby;
import NPCs.JavaJohn;
import NPCs.JavaJohnGlasses;
import NPCs.NPCBoy1;
import NPCs.NPCBoy2;
import NPCs.NPCGirl1;
import NPCs.NPCSwimmer;
import NPCs.Nathan;
import NPCs.NathanBicycle;
import NPCs.PubSDectLooker;
import Scripts.SimpleTextScript;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.NPCDialogue.NPCBoy1Script;
import Scripts.NPCDialogue.NPCBoy2Script;
import Scripts.NPCDialogue.NPCGirl1Script;
import Scripts.NPCDialogue.NPCSwimmerScript;
import NPCs.PubSafetyDect;
import Scripts.Quests.*;
import Scripts.Quests.NathanQuest.NathanBicycleScript;
import Scripts.Quests.NathanQuest.NathanScript;
import Scripts.TestMap.DinoScript;
//import Scripts.TestMap.TeleportScript;
import Scripts.TestMap.TreeScript;
import Scripts.TestMap.WalrusPurpFishScript;
import Scripts.TestMap.WalrusRedFishScript;
import Scripts.TestMap.WalrusScript;
import Tilesets.CommonTileset;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {

    private int currentChoice;

    public TestMap() {
        super("test_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
        this.mapInt = 0;
        this.idSwitch = 0;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(0, getMapTile(67, 12).getLocation().subtractY(40));
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        if (!getFlagManager().isFlagSet("isJavaJohnFloating")) {
            JavaJohn javaJohn = new JavaJohn(2, getMapTile(37, 7).getLocation());
            javaJohn.setInteractScript(new JavaJohnScript());
            npcs.add(javaJohn);
        }

        // adds the glasses of java john's that need finding

        JavaJohnGlasses javaJohnGlasses = new JavaJohnGlasses(3, getMapTile(97, 39).getLocation());
        javaJohnGlasses.setInteractScript(new JavaJohnGlassesScript());
        if (getFlagManager().isFlagSet("hasTalkedToJavaJohn") && !getFlagManager().isFlagSet("hasPickedUpGlasses")) {
            javaJohnGlasses.setIsHidden(false);
        } else {
            javaJohnGlasses.setIsHidden(true);
        }
        npcs.add(javaJohnGlasses);

        PubSafetyDect pubSafetyDect = new PubSafetyDect(4, getMapTile(45, 47).getLocation());
        pubSafetyDect.setInteractScript(new PubSafetyDectScript());
        if (getFlagManager().isFlagSet("hasBookerBeenDemoted")) {
            pubSafetyDect.setIsHidden(true);
        }
        npcs.add(pubSafetyDect);

        WalrusFish walrusFish = new WalrusFish(5, getMapTile(5, 32).getLocation());
        walrusFish.setInteractScript(new WalrusRedFishScript());

        WalrusPurpFish walrusPurpFish = new WalrusPurpFish(6, getMapTile(4, 30).getLocation());
        walrusPurpFish.setInteractScript(new WalrusPurpFishScript());

        walrusFish.setIsHidden(true);
        walrusPurpFish.setIsHidden(true);
        if (!getFlagManager().isFlagSet("hasPickedUpFish")) {
            if (getFlagManager().isFlagSet("redFish")) {
                walrusFish.setIsHidden(false);
            } else if (getFlagManager().isFlagSet("purpleFish")) {
                walrusPurpFish.setIsHidden(false);
            }
        }

        npcs.add(walrusPurpFish);
        npcs.add(walrusFish);

        // adds Nathan's bike
        NathanBicycle nathanBike = new NathanBicycle(7, getMapTile(4, 32).getLocation());
        nathanBike.setInteractScript(new NathanBicycleScript());
        npcs.add(nathanBike);

        // adds Nathan
        Nathan nathan = new Nathan(8, getMapTile(6, 33).getLocation());
        nathan.setInteractScript(new NathanScript());
        npcs.add(nathan);

        // adds an npc boy (brunette white shirt)
        NPCBoy1 npcBoy1 = new NPCBoy1(9, getMapTile(20, 6).getLocation());
        npcBoy1.setInteractScript(new NPCBoy1Script());
        npcs.add(npcBoy1);

        // adds an npc girl (blonde with green shirt)
        NPCGirl1 npcGirl1 = new NPCGirl1(10, getMapTile(52, 31).getLocation());
        npcGirl1.setInteractScript(new NPCGirl1Script());
        npcs.add(npcGirl1);

        // adds an npc boy (red shirt hat)
        NPCBoy2 npcBoy2 = new NPCBoy2(11, getMapTile(4, 45).getLocation());
        npcBoy2.setInteractScript(new NPCBoy2Script());
        npcs.add(npcBoy2);

        // adds an npc swimmer
        NPCSwimmer npcSwimmer = new NPCSwimmer(12, getMapTile(85, 52).getLocation());
        npcSwimmer.setInteractScript(new NPCSwimmerScript());
        npcs.add(npcSwimmer);

        PubSDectLooker pubSDectLooker = new PubSDectLooker(13, getMapTile(95, 14).getLocation());
        pubSDectLooker.setInteractScript(new PubSDectLookerScript());
        if (getFlagManager().isFlagSet("hasBookerBeenDemoted")) {
            pubSDectLooker.setIsHidden(true);
        }
        npcs.add(pubSDectLooker);

        return npcs;

    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        triggers.add(new Trigger(2256, 1968, 196, 10, new PubSafetyDectScript(), "hasEncounteredPubSafetyDect"));
        triggers.add(new Trigger(1776, 2304, 10, 196, new PubSafetyDectScript(), "hasEncounteredPubSafetyDect"));

        // base game triggers
        // This is the code to display a textbox once a user moves in the test map for
        // the first time
        /*
         * triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(),
         * "hasLostBall"));
         * triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(),
         * "hasLostBall"));
         * triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(),
         * "hasLostBall"));
         */
        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(2, 6).setInteractScript(new TreeScript());

        // getMapTile(1, 1).setInteractScript(new TeleportScript(32, 23));

        // getMapTile(32, 25).setInteractScript(new TeleportScript(2, 2));

        getMapTile(100, 59).setInteractScript(new ChangeMapScript(1));

        getMapTile(122, 40).setInteractScript(new ChangeMapScript(2));
    }
}