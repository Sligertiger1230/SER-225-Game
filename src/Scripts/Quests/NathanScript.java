package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Maps.TestMap;
import Utils.Direction;

// trigger script at beginning of game to set that heavy emotional plot
public class NathanScript extends Script<NPC> {

    private int amountMoved;

    @Override
    protected void setup() {
        lockPlayer();
        showPortrait("nathanPortrait.png");
        showTextbox();
        
        if (!isFlagSet("hasTalkedToNathan")){
            addTextToTextboxQueue("Talk to me again if you want me to run away.");
        }
        else {
            addTextToTextboxQueue("I'm going to run away now.");
        }
    }

    @Override
    protected void cleanup() {
        //removes text and lets player walk 
        unlockPlayer();
        hideTextbox();
        hidePortrait();
        
        setFlag("hasTalkedToNathan");
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToNathan")) {
            //setup() function
            start();
            //goes through text
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            //cleanup() function
            end();
        } 
        else {
            //will rise until off screen
            entity.walk(Direction.UP, 2);
            amountMoved += 10;
            //if has gone off screen
            if (amountMoved == 2000) {
                //clean up function
                end();
            } 
            //if hasn't gone off screen, continue running
            else {
                return ScriptState.RUNNING;
            }
        }

        return ScriptState.COMPLETED;
    }
}
