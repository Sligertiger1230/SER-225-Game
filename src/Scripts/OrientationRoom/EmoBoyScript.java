package Scripts.OrientationRoom;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class EmoBoyScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        //add text to textbox
        addTextToTextboxQueue( "I actually got into Harvard, but I couldn't afford it.");
        addTextToTextboxQueue( "I think I made the wrong choice.");
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
    }

    @Override
    public ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}
