package Maps;

import EnhancedMapTiles.PushableRock;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
// import Level.SoundManager;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.Walrus;
import NPCs.WalrusFish;
import NPCs.WalrusPurpFish;
import Players.Cat;
import NPCs.JavaJohn;
import NPCs.JavaJohnGlasses;
import Scripts.SimpleTextScript;
import Scripts.CCEClassroom.ChangeMapScript;
import Scripts.CCEClassroom.ChangeToIceRinkScript;
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

        PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        enhancedMapTiles.add(pushableRock);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(0, getMapTile(4, 28).getLocation().subtractY(40));
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        Dinosaur dinosaur = new Dinosaur(1, getMapTile(13, 4).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        if (!getFlagManager().isFlagSet("isJavaJohnFloating")) {
            JavaJohn javaJohn = new JavaJohn(2, getMapTile(37, 7).getLocation());
            javaJohn.setInteractScript(new JavaJohnScript());
            npcs.add(javaJohn);
        }

        // adds the glasses of java john's that need finding

        JavaJohnGlasses javaJohnGlasses = new JavaJohnGlasses(3, getMapTile(97, 39).getLocation());
        javaJohnGlasses.setInteractScript(new JavaJohnGlassesScript());
        if (getFlagManager().isFlagSet("hasTalkedToJavaJohn")) {
            javaJohnGlasses.setIsHidden(false);
        } else {
            javaJohnGlasses.setIsHidden(true);
        }
        npcs.add(javaJohnGlasses);
        

        WalrusFish walrusFish = new WalrusFish(4, getMapTile(98, 39).getLocation());
        javaJohnGlasses.setInteractScript(new JavaJohnGlassesScript());
        
        WalrusPurpFish walrusPurpFish = new WalrusPurpFish(5, getMapTile(96, 39).getLocation());
        javaJohnGlasses.setInteractScript(new JavaJohnGlassesScript());

        if(getFlagManager().isFlagSet("RedFish")){
            walrusFish.setIsHidden(false);
            walrusPurpFish.setIsHidden(true);
        }
        else if(getFlagManager().isFlagSet("PurpleFish")){
            walrusPurpFish.setIsHidden(false);
            walrusFish.setIsHidden(true);
        }
        else{
            walrusFish.setIsHidden(true);
            walrusPurpFish.setIsHidden(true);
        }
        npcs.add(walrusFish);
        npcs.add(walrusPurpFish);




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

        // getMapTile(1, 1).setInteractScript(new TeleportScript(32, 23));

        // getMapTile(32, 25).setInteractScript(new TeleportScript(2, 2));

        getMapTile(100, 59).setInteractScript(new ChangeMapScript());

        getMapTile(25, 16).setInteractScript(new ChangeToIceRinkScript());
    }
}