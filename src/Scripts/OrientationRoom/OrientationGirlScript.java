package Scripts.OrientationRoom;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class OrientationGirlScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        // add text to textbox
        addTextToTextboxQueue("Who are you?");
        addTextToTextboxQueue("Why are you talking to me.");
        addTextToTextboxQueue("...");
        addTextToTextboxQueue(".....");
        addTextToTextboxQueue(".......");
        addTextToTextboxQueue("You're kinda ugly.");
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
