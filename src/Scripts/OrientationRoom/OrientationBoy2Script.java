package Scripts.OrientationRoom;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class OrientationBoy2Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        //add text to textbox
        addTextToTextboxQueue( "Hey bro.");
        addTextToTextboxQueue( "Wanna go to toads?");
        addTextToTextboxQueue( "Me and my orientation group are going there\n this weekend.");
        addTextToTextboxQueue( "It's gonna be fire.");
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