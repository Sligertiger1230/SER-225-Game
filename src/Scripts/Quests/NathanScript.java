package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Maps.TestMap;
import Utils.Direction;

// trigger script at beginning of game to set that heavy emotional plot
public class NathanScript extends Script<NPC> {

    private int amountMoved;
    private int sequence = 0;

    @Override
    protected void setup() {
        if(!isFlagSet("nathanRunning")){
            lockPlayer();
            showPortrait("nathanPortrait.png", 3);
            showTextbox();

            if (!isFlagSet("hasTalkedToNathan")){
            addTextToTextboxQueue("I'm literally the fastest on campus.");
            }
            else {
                addTextToTextboxQueue("I'll race you to CCE right now!");
            }
        }
    }

    @Override
    protected void cleanup() {
        //removes text and lets player walk 
        unlockPlayer();
        hideTextbox();
        hidePortrait();
        
        if (!isFlagSet("hasTalkedToNathan")){
            setFlag("hasTalkedToNathan");
        }
        else {
            setFlag("nathanRunning");
        }
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
        else if (isFlagSet("nathanRunning")) {
        //player is not locked, but nathan walks
        //sequence determines whether nathan is moving right or down
            if (sequence == 0){
                entity.walk(Direction.RIGHT, 4);
                amountMoved += 1;
                if (amountMoved >= 1115) {
                    sequence++;
                    amountMoved = 0;
                }
                return ScriptState.RUNNING;
            }
            else {
                entity.walk(Direction.DOWN, 4);
                amountMoved += 1;
                if (amountMoved == 200) {
                    amountMoved = 0;
                    unsetFlag("nathanRunning");
                    entity.stand(Direction.LEFT);
                    end();
                }
                else {
                    return ScriptState.RUNNING;
                }
            }
        }
        else {
            //setup() function
            start();
            //goes through text
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            //cleanup() function
            end();
        }
        return ScriptState.COMPLETED;
    }
}
