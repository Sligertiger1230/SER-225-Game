package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Utils.Direction;

public class JaiswalDrawQuest extends Script<NPC> {

    private int amountMoved;

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        if (!isFlagSet("hasTalkedToJaiswal")) {
            addTextToTextboxQueue("Why hello, young student!");
            addTextToTextboxQueue("My name is Professor Jaiswal, a professor for the\nComputer Science degree.");
            addTextToTextboxQueue("Recently, I have been looking for some help with one\nof my projects.");
            addTextToTextboxQueue("I wish to make a cool diagram for a logo.");
            addTextToTextboxQueue("It would be great if you would help me!");
            addTextToTextboxQueue("You will? Thank you so much. Follow me.");
            createStepList();
            addStep("Help Dr. J make a logo.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (!isFlagSet("hasTalkedToJaiswal")) {
            setFlag("hasTalkedToJaiswal");
        }
        if (isFlagSet("hasTalkedToJaiswal")) {
            nextStep("Exit CCE 030.");
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToJaiswal")) {
            // setup()
            start();
            // goes through text
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            // cleanup()
            end();
        } else {
            setFlag("isJaiswalMoving");
        }
        if (isFlagSet("isJaiswalMoving")) {
            entity.walk(Direction.DOWN, 2);
            amountMoved += 10;
            if (amountMoved == 2000) {
                end();
            } else {
                return ScriptState.RUNNING;
            }
        }
        return ScriptState.COMPLETED;
    }
}
