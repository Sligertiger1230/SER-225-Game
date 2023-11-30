package Scripts.IceRink;

import Level.Script;
import Level.ScriptState;




public class JudyChangeMapScript extends Script {
    private int idSwitch;

    public JudyChangeMapScript(int idSwitch) {
        this.idSwitch = idSwitch;
    }


    @Override
    protected void setup() {
        showTextbox();
        lockPlayer();

        showPortrait("Judy.png", 4);
        addTextToTextboxQueue("Hello class of 20XX. Welcome to Quinnipiac University.");
        addTextToTextboxQueue("Your four years at Quinnipiac will be incredibly \nmemorable.");
        addTextToTextboxQueue("Whether for the best or for the worst, I don't care!");
        addTextToTextboxQueue("I'd like to thank you all for your attendance today\n I know we forced you to be here but");
        addTextToTextboxQueue("it's part of my script!");
        addTextToTextboxQueue("Anyway, we will now begin with the four hour\n presentations of information you will");
        addTextToTextboxQueue("Undoubtedly forget after you leave here.");
        addTextToTextboxQueue("blah blah blah blah blah");
        addTextToTextboxQueue("*Wow this presentation is so\nboring*");
        addTextToTextboxQueue("*You're not absorbing any of\n this information*");
        addTextToTextboxQueue("*You begin to feel sleepy...*");

    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        map.setIdSwitch(idSwitch);
    }

    @Override
    protected ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}