package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Utils.Direction;

public class PubSafetyDectScript extends Script<NPC> {
    protected int amountMoved = 0;
    protected boolean encounteredAbove = false;
    protected boolean encounteredLeft = false;
    protected boolean walkingAway = false;

    @Override
    protected void setup() {
        lockPlayer();

        if (!isFlagSet("hasEncounteredDect")) {
            if (player.getX() > player.getY()) {
                getNPC(4).setLocation(player.getX(), player.getY() + 240);
                encounteredAbove = true;
            } else if (player.getX() < player.getY()) {
                getNPC(4).setLocation(player.getX() + 240, player.getY());
                encounteredLeft = true;
            }
            addTextToTextboxQueue("HALT SUSPECT!!! *sniffs* Are you a student? You\ndon't smell like one. Show me your qcard!");
            addTextToTextboxQueue(".... you're good. Rather dashing young man you are!\nSorry for interrogating you, it's my job. For I am...");
            addTextToTextboxQueue("DETECTIVE BOOKER!!!!!!!!! Well... public safety \ndetective, not a real detective. But I'm training to be a");
            addTextToTextboxQueue("real detective. So, I apoligize for the sudden\ninterrogation, I did an stench assessment and");
            addTextToTextboxQueue("determined you as a possible threat. But nope, you\njust smell bad. Anyways, normally I wouldn't stop you ");
            addTextToTextboxQueue("like I did, but we've had a criminal on campus. So I've\nbeen taking extra measures to try and stop this fiend!");
            addTextToTextboxQueue("If you see any suspicious figures around campus\nthen holla at your boy");
        }
    }

    @Override
    protected void cleanup() {
        // hideTextbox();
        unlockPlayer();
        // hidePortrait();

        if (!isFlagSet("hasEncounteredDect")) {
            getNPC(4).stand(Direction.DOWN);
            setFlag("hasEncounteredDect");
        }
    }

    @Override
    protected ScriptState execute() {
        if (!isFlagSet("hasEncounteredDect")) {
            start();
            if (encounteredAbove) {
                getNPC(4).walk(Direction.UP, 2);
                amountMoved += 2;
                if (amountMoved <= 192) {
                    return ScriptState.RUNNING;
                } else {
                    encounteredAbove = false;
                }
            } else if (encounteredLeft) {
                getNPC(4).walk(Direction.LEFT, 2);
                amountMoved += 3;
                if (amountMoved != 192) {
                    return ScriptState.RUNNING;
                } else {
                    encounteredLeft = false;
                }
            }
            if (!walkingAway) {
                amountMoved = 0;
                getNPC(4).facePlayer(player);
                showTextbox();
                showPortrait("PubSafetyDectPortrait.png", 4);
            }
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            hideTextbox();
            hidePortrait();
            if (isTextboxQueueEmpty()) {
                getNPC(4).walk(Direction.DOWN, 2);
                amountMoved += 2;
                if (amountMoved <= 240) {
                    walkingAway = true;
                    return ScriptState.RUNNING;
                }
            }
            end();
            getNPC(4).setLocation(getMapTile(45, 47).getLocation().x, getMapTile(45, 47).getLocation().y);
        }
        return ScriptState.COMPLETED;
    }

}
