package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Utils.Direction;

public class PubSafetyDectScript extends Script<NPC> {
    protected int amountMoved = 0;

    @Override
    protected void setup() {
        lockPlayer();
        showPortrait("PubSafetyDectPortrait.png", 4);
        showTextbox();

        if (!isFlagSet("hasEncounteredDect")){
            if (player.getX() > player.getY()){
                entity.setLocation(player.getX(), player.getY() + 400);
            }
            else if (player.getX() < player.getY()){
                entity.setLocation(player.getX() + 100, player.getY());
            }
            addTextToTextboxQueue("HALT SUSPECT!!! Show me your qcard");
            addTextToTextboxQueue(".... you're good. Rather dashing young man you are!");
            addTextToTextboxQueue("Sorry for interrogating you, it's my job.\nFor am I DETECTIVE BOOKER");
            addTextToTextboxQueue("Public safety detective, not a real detective\nWe've had a criminal on campus");
            addTextToTextboxQueue("So I've taken measures to try and\nstop this fiend!");
            addTextToTextboxQueue("If you see any suspicious figures around campus\nholla at your boy");
        }
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        hidePortrait();
    }

    @Override
    protected ScriptState execute() {
        start();
        if (!isFlagSet("hasEncounteredDect")){
            start();
            if (player.getX() > player.getY()){
                entity.walk(Direction.UP, 3);
                amountMoved+=3;
                if (amountMoved != 350){
                    return ScriptState.RUNNING;
                }
            }
            else if (player.getX() < player.getY()){
                entity.walk(Direction.LEFT, 3);
                amountMoved+=3;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }

}
