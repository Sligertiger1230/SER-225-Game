package Scripts.NPCDialogue;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class NPCGirl1Script extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        // changes what NPC says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToNPCGirl1")) {
            addTextToTextboxQueue( "I can't believe my roommate would do this to me.");
            addTextToTextboxQueue( "I mean c'mon, poop scented air fresheners...\nhold on...");
            addTextToTextboxQueue( "How long were you standing there listening to me?\npretty weird of you.");
            addTextToTextboxQueue( "Anyway what do you want, I'm gonna be late to\nmy... *inhales*");
            addTextToTextboxQueue( "Alpha, Beta, Gamma, Delta, Phi, Sigma, pi,\n Theta, Iota, Kappa, zeta...");
            addTextToTextboxQueue( "...Xi, Mu, Chi, Epsilon meeting, so you may want to\nget out of my way.");
        }
        else {
            addTextToTextboxQueue( "... where would you even buy poop\nairfresheners...");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if NPC is talked to again after the first time, what he says changes
        setFlag("hasTalkedToNPCGirl1");
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
