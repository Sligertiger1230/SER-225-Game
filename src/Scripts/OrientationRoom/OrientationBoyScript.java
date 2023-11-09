package Scripts.OrientationRoom;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class OrientationBoyScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        //add text to textbox
        addTextToTextboxQueue( "Get away from me ugly.");
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