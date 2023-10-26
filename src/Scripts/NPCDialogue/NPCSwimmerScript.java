package Scripts.NPCDialogue;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class NPCSwimmerScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        addTextToTextboxQueue( "This sunburn is killing me... oh hey!");
        addTextToTextboxQueue( "Please don't tell anyone I'm over here\nI'm not supposed to be doing this.");
        addTextToTextboxQueue( "If you're looking for the Computing Comunications\nand Engineering building, its over to the right.");
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
