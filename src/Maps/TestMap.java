package Maps;

import EnhancedMapTiles.PushableRock;
import Level.Audio;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.Walrus;
import Players.Cat;
import NPCs.JavaJohn;
import NPCs.JavaJohnGlasses;
import NPCs.NPCBoy1;
import NPCs.NPCBoy2;
import NPCs.NPCGirl1;
import NPCs.NPCSwimmer;
import NPCs.Nathan;
import Scripts.SimpleTextScript;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.NPCDialogue.NPCBoy1Script;
import Scripts.NPCDialogue.NPCBoy2Script;
import Scripts.NPCDialogue.NPCGirl1Script;
import Scripts.NPCDialogue.NPCSwimmerScript;
import Scripts.Quests.*;
import Scripts.TestMap.DinoScript;
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

        Walrus walrus = new Walrus(0, getMapTile(67, 12).getLocation().subtractY(40));
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        Dinosaur dinosaur = new Dinosaur(1, getMapTile(13, 4).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        // adds javaJohn!
        JavaJohn javaJohn = new JavaJohn(2, getMapTile(37, 7).getLocation());
        javaJohn.setInteractScript(new JavaJohnScript());
        npcs.add(javaJohn);

        // adds the glasses of java john's that need finding
        JavaJohnGlasses javaJohnGlasses = new JavaJohnGlasses(3, getMapTile(97, 39).getLocation());
        javaJohnGlasses.setInteractScript(new JavaJohnGlassesScript());
        javaJohnGlasses.setIsHidden(true);
        npcs.add(javaJohnGlasses);

        // adds Nathan
        Nathan nathan = new Nathan(2, getMapTile(6, 33).getLocation());
        nathan.setInteractScript(new NathanScript());
        npcs.add(nathan);

        // adds an npc boy (brunette white shirt)
        NPCBoy1 npcBoy1 = new NPCBoy1(4, getMapTile(20, 6).getLocation());
        npcBoy1.setInteractScript(new NPCBoy1Script());
        npcs.add(npcBoy1);
        
        // adds an npc girl (blonde with green shirt)
        NPCGirl1 npcGirl1 = new NPCGirl1(4, getMapTile(52, 32).getLocation());
        npcGirl1.setInteractScript(new NPCGirl1Script());
        npcs.add(npcGirl1);

        // adds an npc boy (red shirt hat)
        NPCBoy2 npcBoy2 = new NPCBoy2(4, getMapTile(4, 33).getLocation());
        npcBoy2.setInteractScript(new NPCBoy2Script());
        npcs.add(npcBoy2);

        // adds an npc swimmer
        NPCSwimmer npcSwimmer = new NPCSwimmer(4, getMapTile(85, 52).getLocation());
        npcSwimmer.setInteractScript(new NPCSwimmerScript());
        npcs.add(npcSwimmer);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // base game triggers
        triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        getMapTile(2, 6).setInteractScript(new TreeScript());

        getMapTile(1, 1).setInteractScript(new TeleportScript(32, 23));

        getMapTile(32, 25).setInteractScript(new TeleportScript(2, 2));

        getMapTile(100, 59).setInteractScript(new ChangeMapScript());
    }
}
