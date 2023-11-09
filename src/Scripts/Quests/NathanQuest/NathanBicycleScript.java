package Scripts.Quests.NathanQuest;

import Level.Script;
import Level.ScriptState;

public class NathanBicycleScript extends Script {

    @Override
    protected void setup() {
        //locks player and sets up text for them
        lockPlayer();
        showTextbox();

        if (!isFlagSet("winRace")){
            addTextToTextboxQueue("This bike does not belong to you.");
        }
        else {
            addTextToTextboxQueue("...");
            addTextToTextboxQueue("(No one is using this bike now that the shoeless\nboy is gone)");
            addTextToTextboxQueue("*Looks around to see if anyone is watching*");
            addTextToTextboxQueue("YOU GOT THE BIKE!!!\nHold E before moving to equip it.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (isFlagSet("winRace")){
            setFlag("bikeActive");
            //setBikeActive();
        }
    }

    @Override
    protected ScriptState execute() {
        //executes setup() function
        start();
        //goes through text
        if (!isTextboxQueueEmpty()){
            return ScriptState.RUNNING;
        }
        //executes cleanup() function
        //if player has one the race, bike will disapear
        if (isFlagSet("winRace")){
            entity.setIsHidden(true);
        }
        end();
        return ScriptState.COMPLETED;
    }

}
