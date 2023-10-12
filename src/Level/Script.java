package Level;

import Utils.Direction;

import java.util.ArrayList;

import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Maps.CCEClassroom;
import Screens.CCEClassroomScreen;

// This class is a base class for all scripts in the game -- all scripts should extend from it
// Scripts can be used to interact with map entities
// Each script defines a set of instructions that will be carried out by the game when it is set to active
// Some examples include interact scripts (such as talking to an NPC) and trigger scripts (scripts that activate when the player walks on them)
public abstract class Script<T extends MapEntity> {
    // this is set to true if script is currently being executed
    protected boolean isActive = false;

    // set to true if script is being executed
    protected boolean isPortraitActive = false;

    // if true, script should perform "setup" logic
    protected boolean start = true;

    // references to the map entity the script is attached to
    // use generic type if you need to use this reference
    protected T entity;

    // reference to the map instance which can be used in any script
    protected Map map;

    // reference to the player instance which can be used in any script
    protected Player player;

    // holds steps
    protected ArrayList<String> stepList;

    // holds quests
    protected ArrayList<Trigger> questTriggers;

    protected int frameDelayCounter = 0;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public T getEntity() {
        return entity;
    }

    public void setMapEntity(T entity) {
        this.entity = entity;
    }

    // "setup" logic for a script to prepare for execution update cycle
    protected abstract void setup();

    // "cleanup" logic for a script to be carried out after execution update cycle
    // ends
    protected abstract void cleanup();

    // the "meat" of the script, it's the logic to be carried out when the script
    // becomes active
    // when script is finished, it should return the COMPLETED Script State
    // if script is still running, it should return the RUNNING Script State
    protected abstract ScriptState execute();

    public void update() {
        // Runs an execute cycle of the Script
        ScriptState scriptState = execute();

        if (frameDelayCounter > 0) {
            frameDelayCounter--;
        }

        // If Script is completed, set it to inactive to allow game to carry on
        if (scriptState == ScriptState.COMPLETED) {
            this.isActive = false;
            map.setActiveInteractScript(null);
        }
    }

    // call setup logic once on script start
    protected void start() {
        if (start) {
            setup();
            start = false;
        }
    }

    // call cleanup logic
    // reset start in case more setup logic is to be carried out in the case of
    // multistep scripts
    protected void end() {
        cleanup();
        start = true;
    }

    // if is active is true, game will execute script
    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    // prevents player from being able to do anything (such as move) while script is
    // being executed
    // useful to prevent player from moving away while interacting with something,
    // etc
    protected void lockPlayer() {
        player.setPlayerState(PlayerState.INTERACTING);
        player.setCurrentAnimationName(player.getFacingDirection() == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT");
    }

    protected void movePlayer(float x, float y) {
        player.setX(x);
        player.setY(y);
    }

    // allow player to go back to its usual game state (being able to move, talk to
    // things, etc)
    // typically used right before script is finished to give control back to the
    // player
    protected void unlockPlayer() {
        player.setPlayerState(PlayerState.STANDING);
    }

    // textbox is shown on screen
    protected void showTextbox() {
        map.getTextbox().setIsActive(true);
    }

    // show character portrait
    protected void showPortrait(String spriteName) {
        map.getTextSpriteDisplay().changeSprite(spriteName);
        map.getTextSpriteDisplay().setPortraitIsActive(true);
    }

    // adds text to be shown in textbox
    protected void addTextToTextboxQueue(String text) {
        map.getTextbox().addText(text);
    }

    // adds a series of text to be shown in textbox
    protected void addTextToTextboxQueue(String[] text) {
        map.getTextbox().addText(text);
    }

    // checks if textbox has already shown all text in its queue
    protected boolean isTextboxQueueEmpty() {
        return map.getTextbox().isTextQueueEmpty();
    }

    // remove textbox from screen
    protected void hideTextbox() {
        map.getTextbox().setIsActive(false);
    }

    // remove portrait from screen
    protected void hideTextSpriteDisplay() {
        map.getTextSpriteDisplay().setPortraitIsActive(false);
    }

    // gets an npc instance by its id value
    protected NPC getNPC(int npcId) {
        for (NPC npc : map.getNPCs()) {
            if (npc.getId() == npcId) {
                return npc;
            }
        }
        return null;
    }

    // force an npc instance to face the player
    // npc chosen based on its id value
    protected void npcFacePlayer(int npcId) {
        NPC npc = getNPC(npcId);
        if (npc != null) {
            npc.facePlayer(player);
        }
    }

    // force an npc to walk in a specified direction at a specified speed
    // npc chosen based on its id value
    protected void npcWalk(int npcId, Direction direction, float speed) {
        NPC npc = getNPC(npcId);
        if (npc != null) {
            npc.walk(direction, speed);
        }
    }

    // force an npc to enter a specified animation
    // npc chosen based on its id value
    protected void npcSetAnimation(int npcId, String animationName) {
        NPC npc = getNPC(npcId);
        if (npc != null) {
            npc.setCurrentAnimationName(animationName);
        }
    }

    // force an npc to enter a specified frame of their current animation
    // npc chosen based on its id value
    protected void npcSetAnimationFrameIndex(int npcId, int frameIndex) {
        NPC npc = getNPC(npcId);
        if (npc != null) {
            npc.setCurrentAnimationFrameIndex(frameIndex);
        }
    }

    // checks if a certain flag has been set or not
    protected boolean isFlagSet(String flagName) {
        return map.getFlagManager().isFlagSet(flagName);
    }

    // sets a flag to true
    protected void setFlag(String flagName) {
        map.getFlagManager().setFlag(flagName);
    }

    // sets a flag to falase
    protected void unsetFlag(String flagName) {
        map.getFlagManager().unsetFlag(flagName);
    }

    // sets amount of frames to wait before moving on
    protected void setWaitTime(int frames) {
        frameDelayCounter = frames;
    }

    // checks if wait time is completed (use in conjunction with setWaitTime)
    protected boolean isWaitTimeUp() {
        return frameDelayCounter == 0;
    }

    // gets a specified map tile instance by index from the map
    protected MapTile getMapTile(int x, int y) {
        return map.getMapTile(x, y);
    }

    // changes a specified map tile instance by index from the map to the provided
    // map tile
    protected void setMapTile(int x, int y, MapTile mapTile) {
        mapTile.setMap(map);
        map.setMapTile(x, y, mapTile);
    }

    // checks if player is currently below the entity attached to this script
    protected boolean isPlayerBelowEntity() {
        Rectangle entityBounds = entity.getCalibratedBounds();
        return player.getBounds().getY1() > entityBounds.getY2();
    }

    // returns instantiated questMenu from Map.java
    protected QuestMenu getQuestMenu() {
        return map.getQuestMenu();
    }

    // adds quest to instantiated questMenu in Map.java
    protected void addQuest(String questName) {
        map.addQuest(new Quest(questName, stepList, questTriggers));
    }

    // creates list to hold steps for quest
    protected void createStepList() {
        stepList = new ArrayList<String>();
    }

    // adds a new step to the quest
    protected void addStep(String newStep) {
        stepList.add(newStep);
    }

    // moves the current step onto the next one
    protected void nextStep(String questName) {
        getQuestMenu().searchQuest(questName).nextStep();
    }

    // creates trigger list to be used on quest
    protected void createTriggerList() {
        questTriggers = new ArrayList<Trigger>();
    }

    // adds trigger to trigger list
    protected void addTrigger(int x, int y, int width, int height, Script triggerScript, String existenceFlag) {
        questTriggers.add(new Trigger(x, y, width, height, triggerScript, existenceFlag));
    }

    // gets trigger list
    protected ArrayList<Trigger> getQuestTriggers() {
        return questTriggers;
    }

}
