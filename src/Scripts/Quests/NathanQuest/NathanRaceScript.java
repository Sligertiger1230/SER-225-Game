package Scripts.Quests.NathanQuest;

import Level.Script;
import Level.ScriptState;

public class NathanRaceScript extends Script {

    @Override
    protected void setup() {
        //locks player and sets up text for them
        lockPlayer();
        showPortrait("nathanPortrait.png", 3);
        showTextbox();

        addTextToTextboxQueue("I've done it, I've beaten you");
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        hidePortrait();
        //sets flag fot trigger so can't be activated again
        setFlag("nathanRaceFinish");
    }

    @Override
    protected ScriptState execute() {
        //if the user has not walked over this trigger yet
        if (!isFlagSet("nathanRaceFinish")) {
            //executes setup() function
            start();
            //goes through text
            if (!isTextboxQueueEmpty()){
                return ScriptState.RUNNING;
            }
            //executes cleanup() function
            end();
        }
        return ScriptState.COMPLETED;
    }

}
