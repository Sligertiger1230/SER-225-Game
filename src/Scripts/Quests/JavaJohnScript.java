package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Maps.TestMap;
import Utils.Direction;

// trigger script at beginning of game to set that heavy emotional plot
public class JavaJohnScript extends Script<NPC> {

    private int amountMoved;

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        // if player have picked up glasses, shows them this text
        if (isFlagSet("hasPickedUpGlasses")) {
            addTextToTextboxQueue("Oh thank god, you found them finally.\nTime to try them on!");
            addTextToTextboxQueue("Well, not so bad if I say so myself.");
            addTextToTextboxQueue("Thank you kind boy. I will watch over you");
        } else {
            // if player has talked to john before
            if (isFlagSet("hasTalkedToJavaJohn")) {
                // text reminding player to continue quest
                addTextToTextboxQueue("AHH!! Sorry thought you were a bobcat...\nWhat are ya still doing here!");
                addTextToTextboxQueue("Go find my glasses on the quad!");
            } else {
                // introduction to quest text
                addTextToTextboxQueue("Zzz... tap it.. zzz...");
                addTextToTextboxQueue(
                        "Tap i-- huh! Oh, you there! The cafeteria needs me,\nbut I lost my glasses somewhere on the quad.");
                addTextToTextboxQueue(
                        "I refuse to move until I can see. Wouldn't wanna bump \ninto a wild bobcat, those things can kill you!");
                addTextToTextboxQueue("So, could you help me find them?");
                //IMPORTANT IMPORTANT
                //sets the glasses NPC so that it is visible, REQUIRED
                getNPC(3).setIsHidden(false);
                //creates an arrayList that holds any step you add, using addStep(String newStep);
                //how convenient huh
                createStepList();
                //steps are the objectives that appear under the quest name in menu
                //wherever you type nextQuest(String questName), as long as the quest name exists and is instantiated within memory, 
                //it will advance to the next step. If there is no next step then quest is completed
                //two new steps added
                addStep("Find glasses on the quad");
                addStep("Take the glasses back \nto Java John");
                //creates arrayList of triggers to hold any triggers wanted for the quest
                //add triggers using addTrigger, HOW CONVENIENT HUH?
                createTriggerList();
                //adds demonstration trigger
                //REMINDER: triggers need script files to correlate to, don't add triggers without planninf
                addTrigger(3000, 1550, 10, 200, new JavaJohnWalkScript(), "hasEncounteredJavaJohnWalk");
                addQuest("Help Java John get his glasses");
            }
        }
    }

    @Override
    protected void cleanup() {
        //removes text and lets player walk 
        unlockPlayer();
        hideTextbox();
        //if the player finished talking to java john for the first time
        if (!isFlagSet("hasTalkedToJavaJohn")) {
            //set flag so john will react differently when talked to again
            setFlag("hasTalkedToJavaJohn");
        }
        //if user has picked up glasses
        if (isFlagSet("hasPickedUpGlasses")) {
            //hides user since they reached off screen
            entity.setIsHidden(true);
            //updates next step, since there is no next step quest just completes
            nextStep("Help Java John get his glasses");
        }
    }

    @Override
    public ScriptState execute() {
        //runs this code if you haven't talked to java john
        if (!isFlagSet("hasTalkedToJavaJohn")) {
            //setup() function
            start();
            //goes through text
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            //cleanup() function
            end();
        } 
        //runs if you have picked up glasses and brought them back to Java john
        else if (isFlagSet("hasPickedUpGlasses")) {
            //setup function
            start();
            //for now it's just the standing right sprite, but this is java john with glasses :)
            entity.stand(Direction.RIGHT);
            //if there is text run
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            } 
            //onces player has gone through text
            else {
                //activate the flag for java john floating into the air
                setFlag("isJavaJohnFloating");
            }
        } 
        //if you already talked to java john once and haven't gotten the glasses
        else if (isFlagSet("hasTalkedToJavaJohn")) {
            //setup function
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            //cleanup function
            end();
        }
        //if the flag for john floating has been activated
        if (isFlagSet("isJavaJohnFloating")) {
            //starts moving upward. his sprite "walking" up is... special to say the least
            //will rise until off screen
            entity.walk(Direction.UP, 2);
            amountMoved += 10;
            //if has gone off screen
            if (amountMoved == 2000) {
                //clean up function
                end();
            } 
            //if hasn't gone off screen, continue running
            else {
                return ScriptState.RUNNING;
            }
        }
        return ScriptState.COMPLETED;
    }
}
