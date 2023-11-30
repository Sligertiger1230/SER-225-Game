package Scripts.NPCDialogue;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class NPCBoy1Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        // changes what NPC says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToNPCBoy1")) {
            addTextToTextboxQueue( "Oh hey...");
            addTextToTextboxQueue( "You see that guy near the doors over there?");
            addTextToTextboxQueue( "You're the main character, go help him.");
        }
        else {
            nextStep("test");
            addTextToTextboxQueue( "Okay if you want to stick around I guess\nwe'll be friends now.");
            addTextToTextboxQueue( "We should hang out sometime, I think we could\n maybe even study together.");
            addTextToTextboxQueue( "I'm getting ahead of myself, I don't even know if\nwe have the same classes");
            addTextToTextboxQueue( "Heck, I don't even know your name, I'm Geter Priffin,\nI'm a health science major");
            addTextToTextboxQueue( "(more overbearing small talk)...");
            addTextToTextboxQueue( "Let's get food sometime this week, I love Domino's\nwe could share a lava cake as friends.");
            addTextToTextboxQueue( "We could do other friend activities. Like playing\nwith broken glass.");
            addTextToTextboxQueue( "...");
            addTextToTextboxQueue( "Please don't walk away.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if NPC is talked to again after the first time, what he says changes
        setFlag("hasTalkedToNPCBoy1");
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
