package Scripts.OrientationRoom;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class JudyScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showPortrait("Judy.png");
        showTextbox();

        if (!isFlagSet("hasStartedGame")) {
            addTextToTextboxQueue( "Hello class of 20XX. Welcome to Quinnipiac University.");
            addTextToTextboxQueue( "Your four years at Quinnipiac will be incredibly \nmemorable.");
            addTextToTextboxQueue( "Whether for the best or for the worst, I don't care! ");
            addTextToTextboxQueue( "I'd like to thank you all for your attendance today\n I know we forced you to be here but");
            addTextToTextboxQueue( "it's part of my script!");
            addTextToTextboxQueue( "Anyway, we will now begin with the four hour\n presentations of information you will");
            addTextToTextboxQueue( "Undoubtedly forget after you leave here.");
            addTextToTextboxQueue( "(Starts talking about how cool campus is\n despite being here once a year)");
            addTextToTextboxQueue( "(Starts talking about how shes going to take \nnap after this)");
            addTextToTextboxQueue( "(You're too zoned out to even register what shes \neven saying at this point)");
            addTextToTextboxQueue( "(You wonder if the food is good here (its not))");
            addTextToTextboxQueue( "(What even is a tuple?)");
            addTextToTextboxQueue( "(You begin to feel sleepy...)");

        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        hidePortrait();

        setFlag("hasStartedGame");
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
