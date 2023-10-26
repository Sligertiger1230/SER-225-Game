package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Maps.TestMap;
import Utils.Direction;

// trigger script at beginning of game to set that heavy emotional plot
public class JaiswalDrawQuestDrawRoom extends Script<NPC> {

    private int amountMoved;
    private int sequence = 0;

    @Override
    protected void setup() {
        if (!isFlagSet("nathanRunning")) {
            lockPlayer();
            showTextbox();

            if (!isFlagSet("hasTalkedToNathan")) {
                addTextToTextboxQueue("Alright, we have arrived!");
                addTextToTextboxQueue(
                        "My favorite superhero is Superman, and I want to\nmake my logo the emblem of his.");
                addTextToTextboxQueue("The problem is that some of the tiles are missing.");
                addTextToTextboxQueue(
                        "If you can match the tiles to where the blank spaces\nare, that would be amazing!");
                addTextToTextboxQueue(
                        "Just push the tiles by walking up to them and moving\nthem in any direction you need.");
                addTextToTextboxQueue(
                        "Once you are done head to the bottom right and hit the\nsign to complete the quest!");
            } else {
                addTextToTextboxQueue("Good luck!");
            }
        }
    }

    @Override
    protected void cleanup() {
        // removes text and lets player walk
        unlockPlayer();
        hideTextbox();
        hidePortrait();

        if (!isFlagSet("hasTalkedToNathan")) {
            setFlag("hasTalkedToNathan");
        } else {
            setFlag("nathanRunning");
        }
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("hasTalkedToNathan")) {
            // setup() function
            start();
            // goes through text
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            // cleanup() function
            end();
        } else if (isFlagSet("nathanRunning")) {
            // player is not locked, but nathan walks
            // sequence determines whether nathan is moving right or down
            if (sequence == 0) {
                entity.walk(Direction.UP, 2);
                amountMoved += 1;
                if (amountMoved >= 100) {
                    sequence++;
                    amountMoved = 0;
                }
                return ScriptState.RUNNING;
            } else {
                entity.walk(Direction.UP, 2);
                amountMoved += 1;
                if (amountMoved == 17) {
                    amountMoved = 0;
                    unsetFlag("nathanRunning");
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