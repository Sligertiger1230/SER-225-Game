package Screens;

// Changed the engine import to all so that we can use Config (September 27th)
import java.util.ArrayList;
import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.CCEClassroom;
import Maps.DrawQuest;
import Maps.Graduation;
import Maps.OrientationRoom;
import Maps.IceRink;
import Maps.IceRink1;
import Maps.IceRink2;
import Maps.IceRinkNPC;
import Maps.TestMap;
import Maps.TutorialRoom;
import Players.Cat;
import Scripts.StartGraduationScript;
import Utils.Direction;
import Utils.Point;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected int triggerSize;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected TransitionScreen transitionScreen;
    protected static AsteroidScreen asteroidScreen;
    protected FlagManager flagManager;
    protected QuestMenu questMenu;
    protected ArrayList<QuestTrigger> triggers;

    private Sound musicPlayer;
    private Ambience ambiencePlayer;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        this.musicPlayer = new Sound();
        this.ambiencePlayer = new Ambience();
    }

    public void initialize() {
        questMenu = new QuestMenu(this);

        triggers = new ArrayList<QuestTrigger>();

        // setup state
        flagManager = new FlagManager();

        // judy flag
        flagManager.addFlag("hasStartedGame", false);
        flagManager.addFlag("tutorialTime", false);

        // tutorial flags
        flagManager.addFlag("isInTutorialRoom", false);
        flagManager.addFlag("isWalkingForward", false);
        flagManager.addFlag("hasWalkedForward", false);
        flagManager.addFlag("choosesTutorial", false);
        flagManager.addFlag("skipsTutorial", false);
        flagManager.addFlag("returningFromTutorial", false);
        flagManager.addFlag("hasPressedQ", false);
        flagManager.addFlag("completedTutorial", false);
        flagManager.addFlag("isSprinting", false);


        flagManager.addFlag("hasFinishedAllQuests");

        // Walrus Fish quest
        flagManager.addFlag("redFish", false);
        flagManager.addFlag("purpleFish", false);
        flagManager.addFlag("hasPickedUpFish", false);

        // pubSafetyDect flags
        flagManager.addFlag("hasEncounteredPubSafetyDect", false);
        flagManager.addFlag("hasTalkedToPubSDectLooker", false);
        flagManager.addFlag("hasReportedPubSDectLooker", false);
        flagManager.addFlag("hasBookerBeenDemoted", false);

        // java john quest flags. This is the best way to have flags right now
        // will organize them better, but we will always have to instantiate them
        // beforehand
        flagManager.addFlag("hasTalkedToJavaJohn", false);
        flagManager.addFlag("hasPickedUpGlasses", false);
        flagManager.addFlag("hasEncounteredJavaJohnWalk", false);
        flagManager.addFlag("isJavaJohnFloating", false);

        //jaiswal quest flags
        flagManager.addFlag("hasTalkedToJaiswal", false);
        flagManager.addFlag("jaiswalWalking", false);
        flagManager.addFlag("hasTalkedToJaiswalInPuzzle", false);
        flagManager.addFlag("jaiswalWalkingInPuzzle", false);

        // Nathan quest flags
        flagManager.addFlag("hasTalkedToNathan", false);
        flagManager.addFlag("nathanRunning", false);
        flagManager.addFlag("nathanReturn", false);
        flagManager.addFlag("winRace", false);
        flagManager.addFlag("nathanActivelyRunning", false);
        flagManager.addFlag("bikeActive", true);

        // base game flags
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);

        // NPC flags
        flagManager.addFlag("hasTalkedToNPCBoy1", false);
        flagManager.addFlag("hasTalkedToNPCGirl1", false);

        //Ice flags
        flagManager.addFlag("hasTalkedToIceWalrus", false);
        flagManager.addFlag("hasTalkedToIceWalrus2", false);
        flagManager.addFlag("hasTalkedToIceWalrus3", false);
        flagManager.addFlag("Ice1", true);
        flagManager.addFlag("Ice2", true);
        flagManager.addFlag("Ice3", true);
        flagManager.addFlag("completedAllQuests", false);
        //false means it will start one
        flagManager.addFlag("Orientation", false);
        flagManager.addFlag("Graduation", false);

        // define/setup map
        this.map = loadMap(4);
        this.map.setQuestMenu(questMenu);

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        this.player.setFacingDirection(Direction.LEFT);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // setup map scripts to have references to the map and player
        for (MapTile mapTile : map.getMapTiles()) {
            if (mapTile.getInteractScript() != null) {
                mapTile.getInteractScript().setMap(map);
                mapTile.getInteractScript().setPlayer(player);
            }
        }
        for (NPC npc : map.getNPCs()) {
            if (npc.getInteractScript() != null) {
                npc.getInteractScript().setMap(map);
                npc.getInteractScript().setPlayer(player);
            }
        }
        for (EnhancedMapTile enhancedMapTile : map.getEnhancedMapTiles()) {
            if (enhancedMapTile.getInteractScript() != null) {
                enhancedMapTile.getInteractScript().setMap(map);
                enhancedMapTile.getInteractScript().setPlayer(player);
            }
        }

        triggerSize = 0;

        for (Trigger trigger : map.getTriggers()) {
            if (trigger.getTriggerScript() != null) {
                trigger.getTriggerScript().setMap(map);
                trigger.getTriggerScript().setPlayer(player);
            }
        }

        winScreen = new WinScreen(this);
        transitionScreen = new TransitionScreen(this);
        //asteroidScreen = new AsteroidScreen(this);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the
            // platformer level going
            case RUNNING:

                player.update();
                map.update(player);
                if (questMenu.areQuestFinished()){
                    map.addTrigger(player.getX(), player.getY(), 10, 10, new StartGraduationScript(), "completedAllQuests");
                }

                // updateTriggers changes size of map triggers size. so check if previous value
                // stored is the same
                // if its not
                if (map.getUpdatedTriggerSize() != triggerSize) {
                    // go through every new trigger addition
                    for (int index = triggerSize; index < map.getUpdatedTriggerSize(); index++) {
                        if (map.getUpdatedTriggers().get(index).getMapInt() == map.getMapInt()) {
                            map.getUpdatedTriggers().get(index).getTrigger().getTriggerScript().setMap(map);
                            map.getUpdatedTriggers().get(index).getTrigger().getTriggerScript().setPlayer(player);
                        }
                    }
                    triggerSize = map.getUpdatedTriggerSize();
                }
                if (map.getMapInt() != map.getIdSwitch()) {
                    this.playLevelScreenState = PlayLevelScreenState.TRANSITION;
                    this.map = loadMap(map.getIdSwitch());
                    this.map.setFlagManager(flagManager);
                    this.map.setQuestMenu(questMenu);
                    loadMapInfo(this.map);
                    this.player.setMap(this.map);
                    if(player.getWasInCCE() == 1  && map.getIdSwitch() == 0){
                        this.player.setLocation(4800, 2832); 
                        player.setWasInCCE(0);
                    }else if(player.getWasInCCE() == 2  && map.getIdSwitch() == 0){
                        this.player.setLocation(6000, 1728); 
                        player.setWasInCCE(0);
                    }else{
                        Point playerStartPosition = map.getPlayerStartPosition();
                        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);  
                    }           


                }
                break;
            case ASTEROID:
                asteroidScreen.update();
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
            case TRANSITION:
                if (this.transitionScreen == null) {
                    this.transitionScreen = new TransitionScreen(this);
                }

                transitionScreen.update();
                break;

        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
            case ASTEROID:
                musicPlayer.stop();
                asteroidScreen.draw(graphicsHandler);
                break;
            case TRANSITION:
                transitionScreen.draw(graphicsHandler);
                break;
        }
    }

    public Map loadMap(int mapId) {
        Map newMap;
        musicPlayer.stop();
        ambiencePlayer.stop();

        switch (mapId) {
            case 0:
                newMap = new TestMap();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(0);
                musicPlayer.loop();
                ambiencePlayer.setFile(27);
                ambiencePlayer.loop();
                return newMap;
            case 1:
                newMap = new CCEClassroom(this);
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(18);
                musicPlayer.loop();
                player.setWasInCCE(1);
                System.out.println(player.getWasInCCE());
                ambiencePlayer.setFile(28);
                ambiencePlayer.loop();
                return newMap;
            case 2:
                newMap = new IceRinkNPC();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(19);
                player.setWasInCCE(2);
                musicPlayer.loop();
                return newMap;
            case 3:
                newMap = new DrawQuest();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(17);
                musicPlayer.loop();
                return newMap;
            case 4:
                newMap = new OrientationRoom();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(20);
                musicPlayer.loop();
                ambiencePlayer.setFile(28);
                ambiencePlayer.loop();
                return newMap;
            case 5:
                newMap = new TutorialRoom();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(20);
                musicPlayer.loop();
                ambiencePlayer.setFile(28);
                ambiencePlayer.loop();
                return newMap;
            case 7:
                newMap = new IceRink();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(20);
                musicPlayer.loop();
                return newMap;
            case 8:
                newMap = new IceRink2();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(20);
                musicPlayer.loop();
                return newMap;
            case 9:
                newMap = new IceRink1();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(20);
                musicPlayer.loop();
                return newMap;
            case 10:
                newMap = new Graduation();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                musicPlayer.setFile(20);
                musicPlayer.loop();
                return newMap;

            default:
                return null;
        }
    }

    public void loadMapInfo(Map map) {
        // setup map scripts to have references to the map and player
        for (MapTile mapTile : map.getMapTiles()) {
            if (mapTile.getInteractScript() != null) {
                mapTile.getInteractScript().setMap(map);
                mapTile.getInteractScript().setPlayer(player);
            }
        }
        for (NPC npc : map.getNPCs()) {
            if (npc.getInteractScript() != null) {
                npc.getInteractScript().setMap(map);
                npc.getInteractScript().setPlayer(player);
            }
        }
        for (EnhancedMapTile enhancedMapTile : map.getEnhancedMapTiles()) {
            if (enhancedMapTile.getInteractScript() != null) {
                enhancedMapTile.getInteractScript().setMap(map);
                enhancedMapTile.getInteractScript().setPlayer(player);
            }
        }

        for (Trigger trigger : map.getTriggers()) {
            if (trigger.getTriggerScript() != null) {
                trigger.getTriggerScript().setMap(map);
                trigger.getTriggerScript().setPlayer(player);
            }
        }

        for (QuestTrigger trigger : map.getUpdatedTriggers()) {
            if (trigger.getTrigger().getTriggerScript() != null) {
                if (trigger.getMapInt() == map.getMapInt()) {
                    trigger.getTrigger().setMap(map);
                    map.getTriggers().add(trigger.getTrigger());
                    trigger.getTrigger().getTriggerScript().setMap(map);
                    trigger.getTrigger().getTriggerScript().setPlayer(player);
                }
            }
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public Map playLevel() {
        return map;
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    public void returnFromAsteroid() {
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        asteroidScreen = null;
    }
    public void returnFromTutorial(){
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        asteroidScreen = null;
    }

    public void returnFromTransition() {
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        transitionScreen = null;
    }

    public void startTransition() {
        transitionScreen = new TransitionScreen(this);
        playLevelScreenState = PlayLevelScreenState.TRANSITION;

    }

    public void startAsteroid() {
        asteroidScreen = new AsteroidScreen(this);
        playLevelScreenState = PlayLevelScreenState.ASTEROID;
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, ASTEROID, TRANSITION
    }
}
