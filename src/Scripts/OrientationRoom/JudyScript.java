package Scripts.OrientationRoom;


import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Scripts.CCEClassroom.ChangeMapScript;

public class JudyScript extends Script {


    @Override
    protected void setup() {
        lockPlayer();
        showPortrait("Judy.png", 4);
        showTextbox();

        if (!isFlagSet("hasStartedGame")) {
            setFlag("hasStartedGame");

            addTextToTextboxQueue("Hello class of 20XX. Welcome to Quinnipiac University.");
            addTextToTextboxQueue("Your four years at Quinnipiac will be incredibly \nmemorable.");
            addTextToTextboxQueue("Whether for the best or for the worst, I don't care!");
            addTextToTextboxQueue("I'd like to thank you all for your attendance today\n I know we forced you to be here but");
            addTextToTextboxQueue("it's part of my script!");
            addTextToTextboxQueue("Anyway, we will now begin with the four hour\n presentations of information you will");
            addTextToTextboxQueue("Undoubtedly forget after you leave here.");

        }
    }

    @Override
    protected void cleanup() {
        lockPlayer();
        hideTextbox();
        hidePortrait();
    }

    @Override
    public ScriptState execute() {
        start();

        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        // Teleport the player to map 5 after Judy has finished speaking
        new ChangeMapScript(5);

        end();
        return ScriptState.COMPLETED;
    }
}
