package Scripts.Quests;

import Level.Script;
import Level.ScriptState;

public class JavaJohnWalkScript extends Script {

    @Override
    protected void setup() {
        //locks player and sets up text for them
        lockPlayer();
        showTextbox();

        addTextToTextboxQueue("Wow this walk sure is far...");
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        //sets flag fot trigger so can't be activated again
        setFlag("hasEncounteredJavaJohnWalk");
    }

    @Override
    protected ScriptState execute() {
        //if the user has not walked over this trigger yet
        if (!isFlagSet("hasEncounteredJavaJohnWalk")) {
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
