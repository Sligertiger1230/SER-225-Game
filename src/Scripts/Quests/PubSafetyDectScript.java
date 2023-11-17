package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Utils.Direction;

public class PubSafetyDectScript extends Script<NPC> {
    protected int amountMoved = 0;
    protected int amountToMove;
    protected boolean encounteredAbove = false;
    protected boolean encounteredLeft = false;
    protected boolean encounteredRight = false;
    protected boolean walkingAway = false;

    @Override
    protected void setup() {
        lockPlayer();

        if (!isFlagSet("hasEncounteredPubSafetyDect")) {
            if (player.getX() < 1872 || player.getX() > 2688 || player.getY() < 2100) {
                if (player.getX() > player.getY() && player.getX() < 2500) {
                    getNPC(4).setLocation(player.getX(), player.getY() + 240);
                    encounteredAbove = true;
                } else if (player.getX() > 2500) {
                    getNPC(4).setLocation(player.getX() - 450, player.getY());
                    encounteredRight = true;
                } else if (player.getX() < player.getY()) {
                    getNPC(4).setLocation(player.getX() + 450, player.getY());
                    encounteredLeft = true;
                }
                addTextToTextboxQueue(
                        "HALT SUSPECT!!! *sniffs* Are you a student? You\ndon't smell like one. Show me your qcard!");
                addTextToTextboxQueue("...");
                addTextToTextboxQueue(
                        ".... you check out. I apoligize, I was just doing my\nduties as public safety detective. For I am...");
                addTextToTextboxQueue(
                        "DETECTIVE BOOKER!!!!!!!!! Technically I'm not a real\ndetective. But I'm training to be a real detective!");
                addTextToTextboxQueue(
                        "I simply did a protocol stench assessment and\ndetermined you as a possible threat. But nope, you");
                addTextToTextboxQueue(
                        "just smell bad. Anyways, I normally wouldn't stop you\nlike I did, but we've had a criminal on campus. So I've");
                addTextToTextboxQueue(
                        "been taking extra measures to try and stop this fiend!\nIf you see any suspicious figures around campus then");
                addTextToTextboxQueue("holla at your boy.");
            } else {
                getNPC(4).facePlayer(player);
                showTextbox();
                showPortrait("PubSafetyDectPortrait.png", 4);
                addTextToTextboxQueue(
                        "Detective Booker, at your service. Technically Public\nSafety Detective, I'm not a real one. I'm in training ");
                addTextToTextboxQueue(
                        "don't tell anyone. Although, my teacher said I could\nbecome a real detective if I find a criminal who was");
                addTextToTextboxQueue("reported around here, holla at your boy if you see\nanyone suspicious.");
            }
        }

        else {
            getNPC(4).facePlayer(player);
            showTextbox();
            showPortrait("PubSafetyDectPortrait.png", 4);
            if (isFlagSet("hasTalkedToPubSDectLooker") && !isFlagSet("hasReportedPubSDectLooker")) {
                addTextToTextboxQueue("You saying you found the criminal before me?!!!\nWell lead me to him then!");
            } else if (isFlagSet("hasTalkedToPubSDectLooker") && isFlagSet("hasReportedPubSDectLooker")) {
                addTextToTextboxQueue("Well? Lead me to him");
            } else {
                addTextToTextboxQueue(
                        "I heard from my boss, that CCTV saw him around\nEchlin center. I'm starting from the farthest to the");
                addTextToTextboxQueue("closet parts to the crime scene. An elite strategy.");
            }
        }
    }

    @Override
    protected void cleanup() {
        // hideTextbox();
        unlockPlayer();
        // hidePortrait();

        if (!isFlagSet("hasEncounteredPubSafetyDect")) {
            getNPC(4).stand(Direction.DOWN);
            setFlag("hasEncounteredPubSafetyDect");
            createStepList();
            createTriggerList();
            addStep("Find the criminal on campus");
            addStep("Report the criminal to Booker");
            addStep("Lead Booker to the criminal"); 
            addQuest("World's Best Detective");
        }
        if (isFlagSet("hasTalkedToPubSDectLooker") && !isFlagSet("hasReportedPubSDectLooker")) {
            setFlag("hasReportedPubSDectLooker");
            nextStep("World's Best Detective");
        }
    }

    @Override
    protected ScriptState execute() {
        start();
        if (!isFlagSet("hasEncounteredPubSafetyDec")) {
            if (player.getX() < 1872 || player.getY() < 2100 || player.getX() > 2688) {
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
                    amountMoved += 2;
                    if (amountMoved != 402) {
                        return ScriptState.RUNNING;
                    } else {
                        encounteredLeft = false;
                    }
                } else if (encounteredRight) {
                    getNPC(4).walk(Direction.RIGHT, 2);
                    amountMoved+=2;
                    if (amountMoved <= 402) {
                        return ScriptState.RUNNING;
                    } else {
                        encounteredRight = false;
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
                if (player.getX() > player.getY() && player.getX() < 2500) {
                    getNPC(4).walk(Direction.DOWN, 2);
                    amountToMove = 240;
                } else  if (player.getX() > 2500) {
                    getNPC(4).walk(Direction.LEFT, 2);
                    amountToMove = 402;
                } else {
                    getNPC(4).walk(Direction.RIGHT, 2);
                    amountToMove = 336;
                }
                amountMoved += 2;
                if (amountMoved <= amountToMove) {
                    walkingAway = true;
                    return ScriptState.RUNNING;
                }
                getNPC(4).setLocation(getMapTile(45, 46).getLocation().x, getMapTile(45, 46).getLocation().y);
                end();
                return ScriptState.COMPLETED;
            } else {
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                hideTextbox();
                hidePortrait();
                end();
                return ScriptState.COMPLETED;
            }
        } else {
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
    }

}
