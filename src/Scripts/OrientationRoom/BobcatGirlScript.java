package Scripts.OrientationRoom;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class BobcatGirlScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        //add text to textbox
        addTextToTextboxQueue( "I came here for the greek life.");
        addTextToTextboxQueue( "I'm soooo excited to meet my future sisters!");
        addTextToTextboxQueue( "I'm majoring in Biology. What's your major?");
        addTextToTextboxQueue( "...........");
        addTextToTextboxQueue( "...........");
        addTextToTextboxQueue( "...........");
        addTextToTextboxQueue( "...........");
        addTextToTextboxQueue( "...........");
        addTextToTextboxQueue( "Ok don't respond then freak.");
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
