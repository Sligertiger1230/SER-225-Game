package Screens;

// Changed the engine import to all so that we can use Config (September 27th)
import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.CCEClassroom;
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

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();

        //java john quest flags. This is the best way to have flags right now
        //will organize them better, but we will always have to instantiate them beforehand
        flagManager.addFlag("hasTalkedToJavaJohn", false);
        flagManager.addFlag("hasPickedUpGlasses", false);
        flagManager.addFlag("hasEncounteredJavaJohnWalk", false);
        flagManager.addFlag("isJavaJohnFloating", false);

        // base game flags
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);

        // define/setup map
        this.map = new TestMap();
        map.setFlagManager(flagManager);

        // setup player
        this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        // Initializes the setPlayer from the Audio class (September 27th)
        Audio.setPlayer(this.player);
        // Sets the audio for playing the walk sound (September 27th)
        this.player.setWalkSound(Audio.getWalkSound());
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

        triggerSize = map.getTriggersSize();
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
                //updateTriggers changes size of map triggers size. so check if previous value stored is the same
                //if its not
                if (map.getTriggersSize() != triggerSize) {
                    //go through every new trigger addition
                    for (int index = triggerSize; index < map.getTriggersSize(); index++) {
                        //sets trigger script to map
                        map.getTriggers().get(index).getTriggerScript().setMap(map);
                        //sets trigger script to user
                        map.getTriggers().get(index).getTriggerScript().setPlayer(player);
                    }
                    triggerSize = map.getTriggersSize();
                }
                if(map.getMapInt() == 1){
                    this.map = new CCEClassroom();
                    map.setMapInt(0);
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

        // if flag is set at any point during gameplay, game is "won"
        //if (map.getFlagManager().isFlagSet("hasFoundBall")) {
            //playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        //}
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

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
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
