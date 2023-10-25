package Scripts.NPCDialogue;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class NPCBoy3Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        //add text to textbox
        addTextToTextboxQueue( "Good thing I grabbed this coffee.");
        addTextToTextboxQueue( "I was up all last night doing this\nrecursive maze project.");
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
