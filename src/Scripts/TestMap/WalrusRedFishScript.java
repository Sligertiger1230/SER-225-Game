package Scripts.TestMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// trigger script at beginning of game to set that heavy emotional plot
public class WalrusRedFishScript extends Script<NPC> {

    @Override
    protected void setup() {
        //shows player text about aqcuiring glasses
        lockPlayer();
        showTextbox();
        //advances from the first step to second step of JavaJohnScript.java quest
        nextStep("Help walrus Get a fish");
        addTextToTextboxQueue("A fish in a jar \nMaybe its his Pet");
    }

    @Override
    protected void cleanup() {
        //allows player to move again
        unlockPlayer();
        hideTextbox();
        //glasses are hidden, to give the illusion of being picked up
        entity.setIsHidden(true);
        //sets flag so java john can react as if you have glasses
        setFlag("hasPickedUpFish");
    }

    @Override
    public ScriptState execute() {
        //executes setup() function
        start();
        //goes through text
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        //executes cleanup() function
        end();
        return ScriptState.COMPLETED;
    }
}