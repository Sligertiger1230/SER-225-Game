package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Maps.TestMap;
import Utils.Direction;

// trigger script at beginning of game to set that heavy emotional plot
public class JaiswalDrawQuestCCE extends Script<NPC> {

    private int amountMoved;
    private int sequence = 0;

    @Override
    protected void setup() {
        if (!isFlagSet("jaiswalWalking")) {
            lockPlayer();
            showTextbox();

            if (!isFlagSet("hasTalkedToJaiswal")) {
                addTextToTextboxQueue("Why hello, young student!");
                addTextToTextboxQueue("My name is Professor Jaiswal, a professor for the\nComputer Science degree.");
                addTextToTextboxQueue("Recently, I have been looking for some help with one\nof my projects.");
                addTextToTextboxQueue("I wish to make a cool diagram for a logo.");
                addTextToTextboxQueue("Do you think you could help me? It won't take too\nlong.");
                addTextToTextboxQueue("Great! Let's get going out the door\non the right.");
            }
        }
    }

    @Override
    protected void cleanup() {
        // removes text and lets player walk
        unlockPlayer();
        hideTextbox();
        hidePortrait();

        if (!isFlagSet("hasTalkedToJaiswal")) {
            createStepList();
            addStep("Follow Jaiswal into the quest room");
            addStep("Push the pieces in");
            createTriggerList();
            addQuest("Artist in the Making");
            setFlag("hasTalkedToJaiswal");
            setFlag("jaiswalWalking");
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToJaiswal")) {
            // setup() function
            start();
            // goes through text
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            // cleanup() function
            end();
        }
        if (isFlagSet("jaiswalWalking")) {
            // player is not locked, but nathan walks
            // sequence determines whether nathan is moving right or down
            if (sequence == 0) {
                entity.walk(Direction.RIGHT, 2);
                amountMoved += 1;
                if (amountMoved >= 153) {
                    sequence++;
                    amountMoved = 0;
                }
                return ScriptState.RUNNING;
            } else {
                entity.walk(Direction.UP, 2);
                amountMoved += 1;
                if (amountMoved == 17) {
                    amountMoved = 0;
                    unsetFlag("jaiswalWalking");
                    entity.stand(Direction.LEFT);
                    entity.setIsHidden(true);
                    end();
                } else {
                    return ScriptState.RUNNING;
                }
            }
        } else {
            // setup() function
            start();
            // goes through text
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            // cleanup() function
            end();
        }
        return ScriptState.COMPLETED;
    }
}