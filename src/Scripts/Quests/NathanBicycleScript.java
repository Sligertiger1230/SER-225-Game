package Scripts.Quests;

import Level.Script;
import Level.ScriptState;

public class NathanBicycleScript extends Script {

    @Override
    protected void setup() {
        //locks player and sets up text for them
        lockPlayer();
        showTextbox();

        addTextToTextboxQueue("This bike does not belong to you.");
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
    }

    @Override
    protected ScriptState execute() {
        //executes setup() function
        start();
        //goes through text
        if (!isTextboxQueueEmpty()){
            return ScriptState.RUNNING;
        }
        //executes cleanup() function
        end();
        return ScriptState.COMPLETED;
    }

}
