package Scripts.OrientationRoom;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class IrishKidScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        //add text to textbox
        addTextToTextboxQueue( "I'm an international student from Ireland.");
        addTextToTextboxQueue( "I heard all these good things about America, and I\n really wanted to come.");
        addTextToTextboxQueue( "Connecticut is... interesting so far.");
        addTextToTextboxQueue( "I hope the food here is good at least.");


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
