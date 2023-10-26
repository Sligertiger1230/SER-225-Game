package Screens;

// Changed the engine import to all so that we can use Config (September 27th)
import java.util.ArrayList;
import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.CCEClassroom;
import Maps.IceRink;
import Maps.TestMap;
import Players.Cat;
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
    protected FlagManager flagManager;
    protected QuestMenu questMenu;
    protected ArrayList<QuestTrigger> triggers;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        questMenu = new QuestMenu();

        triggers = new ArrayList<QuestTrigger>();

        // setup state
        flagManager = new FlagManager();

        //Walrus Fish quest
        flagManager.addFlag("RedFish", false);
        flagManager.addFlag("PurpleFish", false);


        //pubSafetyDect flags
        flagManager.addFlag("hasEncounteredPubSafetyDect", false);
        flagManager.addFlag("hasTalkedToPubSDectLooker", false);
        flagManager.addFlag("hasReportedPubSDectLooker", false);

        // java john quest flags. This is the best way to have flags right now
        // will organize them better, but we will always have to instantiate them
        // beforehand
        flagManager.addFlag("hasTalkedToJavaJohn", false);
        flagManager.addFlag("hasPickedUpGlasses", false);
        flagManager.addFlag("hasEncounteredJavaJohnWalk", false);
        flagManager.addFlag("isJavaJohnFloating", false);

        //Nathan quest flags
        flagManager.addFlag("hasTalkedToNathan", false);
        flagManager.addFlag("nathanRunning", false);

        // base game flags
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);

        //NPC flags
        flagManager.addFlag("hasTalkedToNPCBoy1", false);
        flagManager.addFlag("hasTalkedToNPCGirl1", false);

        // define/setup map
        this.map = loadMap(0);
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
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the
            // platformer level going
            case RUNNING:

                player.update();
                map.update(player);

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
                    this.map = loadMap(map.getIdSwitch());
                    this.map.setFlagManager(flagManager);
                    this.map.setQuestMenu(questMenu);
                    loadMapInfo(this.map);
                    this.player.setMap(this.map);
                    Point playerStartPosition = map.getPlayerStartPosition();
                    this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
                }
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
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
        }
    }

    public Map loadMap(int mapId) {
        Map newMap;
        switch (mapId) {
            case 0:
                newMap = new TestMap();
                newMap.setFlagManager(flagManager);
                newMap.setNPCs();
                newMap.setQuestMenu(questMenu);
                return newMap;
            case 1:
                newMap = new CCEClassroom();
                //newMap.setFlagManager(flagManager);
                //newMap.setNPCs();
                //newMap.setQuestMenu(questMenu);
                return newMap;
            case 2:
                newMap = new IceRink();
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

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }
}
