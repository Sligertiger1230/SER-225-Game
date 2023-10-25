package Scripts.NPCDialogue;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class NPCBoy2Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        // changes what NPC says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToWalrus")) {
            addTextToTextboxQueue( "...");
            addTextToTextboxQueue( "....");
            addTextToTextboxQueue( ".....");
            addTextToTextboxQueue( "Oh hi...");
            addTextToTextboxQueue( "Sorry I was just thinking about the sea and\ncool sea facts.");
            addTextToTextboxQueue( "The ocean is insane man, theres just so much cool\nstuff in there");
            addTextToTextboxQueue( "Just think about how cool a walrus would be,\nI want to see one.");
            addTextToTextboxQueue( "If I was walking around campus and saw a\nwalrus... I don't even know dude.");
        }
        else {
            addTextToTextboxQueue( "You saw a... a... a...");
            addTextToTextboxQueue( "A WALRUS?!?!?!?");
            addTextToTextboxQueue( "This is the best day of my life...\nI gotta go shake his hand.");
        }
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
