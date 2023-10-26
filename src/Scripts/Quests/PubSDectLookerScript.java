package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Utils.Direction;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;

public class PubSDectLookerScript extends Script<NPC> {
    protected boolean myseteriouStranger = true;
    protected boolean bottom;
    protected boolean right;
    protected boolean showPortraits = true;
    protected String direction;
    protected boolean outfitChange = false;;
    protected int amountMoved;
    protected int amountMovedLookerRight;
    protected int amountMovedLooker;
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key spaceKey = Key.SPACE;

    @Override
    protected void setup() {
        lockPlayer();

        if (!isFlagSet("hasEncounteredPubSafetyDect")) {
            showTextbox();
            showPortrait("MysteriousStrangerPortrait.png", 4);
            addTextToTextboxQueue("Hehe don't worry about me. Just another student\nminding my own business.");
        } else if (isFlagSet("hasEncounteredPubSafetyDect") && !isFlagSet("hasReportedPubSDectLooker")) {
            showTextbox();
            showPortrait("MysteriousStrangerPortrait.png", 4);
            addTextToTextboxQueue("Hey kid, interested in some legal meal points?\nNo? Alright, see ya kid");
        } else if (isFlagSet("hasReportedPubSDectLooker")) {
            amountMoved = 0;
            amountMovedLooker = 0;
            amountMovedLookerRight = 0;
            if (Math.round(entity.getBoundsY2()) - (entity.getBounds().getHeight() / 2) < Math
                    .round(player.getBoundsY1())
                    && Math.round(player.getBoundsX1()) + (player.getBounds().getWidth() / 2) > Math
                            .round(entity.getBoundsX1())
                    && Math.round(player.getBoundsX1()) + (player.getBounds().getWidth() / 2) < Math
                            .round(entity.getBoundsX2())) {
                bottom = true;
                right = false;
                direction = "bottom";
                getNPC(4).setLocation(entity.getX() + 48, entity.getY() + 480);
            } else if (Math.round(entity.getBoundsX2()) - (entity.getBounds().getHeight() / 2) < Math
                    .round(player.getBoundsX1())
                    && Math.round(player.getBoundsY1()) + (player.getBounds().getHeight() / 2) > Math
                            .round(entity.getBoundsY1())
                    && Math.round(player.getBoundsY1()) + (player.getBounds().getHeight() / 2) < Math
                            .round(entity.getBoundsY2())) {
                bottom = false;
                right = true;
                direction = "right";
                getNPC(4).setLocation(entity.getX(), entity.getY() + 480);
            }
            addTextToTextboxQueue("Detective Booker: STOP FIEND!! I \nSINGLEHANDEDLY CAUGHT YOU!");
            addTextToTextboxQueue("Mysterious Stranger: No Booker, you're the one who's \ncaught.");
        }
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        hidePortraitConversation();
        unlockPlayer();

        if (isFlagSet("hasEncounteredPubSafetyDect") && !isFlagSet("hasTalkedToPubSDectLooker")) {
            setFlag("hasTalkedToPubSDectLooker");
            nextStep("World's Best Detective");
        } else if (isFlagSet("hasEncounteredPubSafetyDect")
                && isFlagSet("hasTalkedToPubSDectLooker")
                && isFlagSet("hasReportedPubSDectLooker")
                && !isFlagSet("hasBookerBeenDemoted")) {
            setFlag("hasBookerBeenDemoted");
            nextStep("World's Best Detective");
        }
    }

    @Override
    protected ScriptState execute() {
        start();
        if (!isFlagSet("hasEncounteredPubSafetyDect")) {
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        } else if (isFlagSet("hasEncounteredPubSafetyDect") && !isFlagSet("hasReportedPubSDectLooker")) {
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        } else if (isFlagSet("hasReportedPubSDectLooker")) {
            if (bottom) {
                getNPC(4).walk(Direction.UP, 2);
                amountMoved += 2;
                if (amountMoved <= 480) {
                    return ScriptState.RUNNING;
                }
                bottom = false;
                getNPC(4).stand(Direction.LEFT);
            } else if (right) {
                getNPC(4).walk(Direction.UP, 2);
                amountMoved += 2;
                if (amountMoved <= 432) {
                    return ScriptState.RUNNING;
                }
                right = false;
                getNPC(4).stand(Direction.UP);
            }
            if (showPortraits) {
                showConversationPortraits("PubSafetyDectPortrait.png", 4, "MysteriousStrangerPortrait.png", 4);
                showTextbox();
            }
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            } else {
                showPortraits = false;
            }
            if (!outfitChange) {
                hideTextbox();
                hidePortraitConversation();
                showConversationPortraits("PubSafetyDectPortrait.png", 4, "DectLookerPortrait.png", 3);
                amountMoved = 0;
                outfitRevealSequence();
            }
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            if (direction.equals("bottom")) {
                getNPC(4).walk(Direction.DOWN, 2);
                amountMoved += 2;
                if (amountMovedLooker == 0) {
                    entity.walk(Direction.RIGHT, 2);
                    amountMovedLookerRight += 2;
                    if (amountMovedLookerRight <= 48) {
                        return ScriptState.RUNNING;
                    } else {
                        entity.walk(Direction.DOWN, 2);
                        amountMovedLooker += 2;
                    }
                }
                entity.walk(Direction.DOWN, 2);
                amountMovedLooker += 2;
                if (amountMoved >= 480) {
                    getNPC(4).setIsHidden(true);
                }
                if (amountMovedLooker <= 480) {
                    return ScriptState.RUNNING;
                } else {
                    entity.setIsHidden(true);
                }
            } else if (direction.equals("right")) {
                getNPC(4).walk(Direction.DOWN, 2);
                entity.walk(Direction.DOWN, 2);
                amountMoved += 2;
                if (amountMoved <= 480) {
                    return ScriptState.RUNNING;
                } else {
                    entity.setIsHidden(true);
                    getNPC(4).setIsHidden(true);
                }
            }
            end();
            return ScriptState.COMPLETED;
        }
        return ScriptState.COMPLETED;
    }

    public void outfitRevealSequence() {
        if (direction.equals("bottom")) {
            entity.stand(Direction.RIGHT);
        } else if (direction.equals("right")) {
            entity.stand(Direction.DOWN);
        }
        addTextToTextboxQueue(
                "Detective Looker: Booker, you failed your detective \ntest! I pretended to be a criminal so that you would ");
        addTextToTextboxQueue(
                "track me down and arrest me. But you sent a KID\nafter me. A KID BOOKER A KID YOU SENT! I'm not");
        addTextToTextboxQueue("even that far. You can see me across the quad.");
        addTextToTextboxQueue("Detective Booker: ...");
        addTextToTextboxQueue("...");
        addTextToTextboxQueue("...when you put it that way");
        addTextToTextboxQueue("Detective Looker: We're going back to the\nstation to strip you of your badge");
        addTextToTextboxQueue("Detective Booker: Okayyyyyy");

        showTextbox();
        outfitChange = true;
    }

}
