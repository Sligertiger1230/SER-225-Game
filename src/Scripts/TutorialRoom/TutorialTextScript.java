package Scripts.TutorialRoom;


import java.util.ArrayList;

import Engine.Key;
import Engine.Keyboard;
import Level.Script;
import Level.ScriptState;
import Level.Trigger;

public class TutorialTextScript extends Script {
    // preventative sequence variable to stop dialogue from repeating
    private int Sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        String[] selections = { "Play the tutorial", "  Wake up" };
        String[] answers = { "Good choice my boy.", "Farewell sweet boy." };
        
        var currentChoice = getChoice();
        // Classifies what the player choose and sets the flags dependent on choices 
            if (currentChoice == 0) {
                setFlag("choosesTutorial");
            } else if (currentChoice == 1){
                setFlag("skipsTutorial");
            }

        // flag for after player has sprinted 
        if (isFlagSet("isSprinting")){
           addTextToTextboxQueue("Son, I think you're ready for college.");
            addTextToTextboxQueue("Your mother and I are so proud.");
            addTextToTextboxQueue("Use Q to access the quest menu.");
            addTextToTextboxQueue("You'll encounter people who need\nyour help.");
            addTextToTextboxQueue("Be sure to aid them as best you can.");
            addTextToTextboxQueue("Farewell my sweet boy.");
                    // sets flag saying the player has completed the tutorial
                    setFlag("completedTutorial"); 
                    // return to orientation room flag
                    setFlag("returningFromTutorial");
        // else if statement for if the player chooses to skip the tutorial
        } else if(isFlagSet("skipsTutorial")){
            addTextToTextboxQueue("You feel yourself waking up..");
                        setFlag("completedTutorial"); // classifies as completing the tutorial
                        setFlag("returningFromTutorial"); // return to orientation room flag

        // else if statement for if the player has walked forward 
        } else if(isFlagSet("hasWalkedForward")){
            if (Sequence == 1) {
                // increments so sequence is never one again
                Sequence++;
                addTextToTextboxQueue("Great job using your legs there buddy.");
                addTextToTextboxQueue("No need to go any farther. Don't\nwant you getting lost now.");
                addTextToTextboxQueue("Lets move on to something more\nchallenging.");
                addTextToTextboxQueue("You can hold the shift button to sprint.");
                addTextToTextboxQueue("You'll go really fast with this.\ntry it out.");
            }
        
        } else if (isFlagSet("choosesTutorial")){
            if (Sequence == 0) {
                // increments so sequence is never zero again
                Sequence++;
                addTextToTextboxQueue("Lets begin with movement.");
                addTextToTextboxQueue("Use the arrow keys to move around.");
            }
        // starting dialogue for tutorial    
        } else {
            addTextToTextboxQueue("Welcome to the dream realm humble \nQuinnipiac student.");
            addTextToTextboxQueue("It is I, the tutorial master.");
            addTextToTextboxQueue("It is time for you to experience the wonders \nof this world.");
            addTextToTextboxQueue("Use the left and right arrow keys\non the keyboard to make your choice.");
            addTextToTextboxQueue("Play the tutorial?", selections, answers);
        }
    }

    @Override
    protected void cleanup() {
        
        hideTextbox();
        unlockPlayer();

    }
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        // adds tutorial change map trigger
        triggers.add(new Trigger((int)getMapTile(15, 8).getX(), (int)getMapTile(15, 8).getY(), 45, 45, new TutorialChangeMapScript(4)));
        return triggers;
    }
    

    @Override
    public ScriptState execute() {
        if (!isFlagSet("isInTutorialRoom")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
        } if(isFlagSet("returningFromTutorial")) {
            map.setIdSwitch(4);
         } else if(isFlagSet("isSprinting")){
             start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            // switches back to orientation room 
            map.setIdSwitch(4);
            return ScriptState.COMPLETED; 
        // unlocks player and sets up condition for is sprinting
        } else if(isFlagSet("hasWalkedForward")){
            unlockPlayer();
            if (Keyboard.isKeyDown(Key.SHIFT) && (Keyboard.isKeyDown(Key.UP) || Keyboard.isKeyDown(Key.LEFT) || Keyboard.isKeyDown(Key.RIGHT) || Keyboard.isKeyDown(Key.DOWN))){
            setFlag("isSprinting");   
             }
            } else if (isFlagSet(("choosesTutorial"))){
                start();
                if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
        } 
        unlockPlayer();
        // takes in all directions of the arrow keys to trigger has walked forward flag
        if (Keyboard.isKeyDown(Key.DOWN) || (Keyboard.isKeyDown(Key.UP) || (Keyboard.isKeyDown(Key.LEFT) || (Keyboard.isKeyDown(Key.RIGHT))))){
            setFlag("hasWalkedForward");

            }
        }
        end();
        return ScriptState.COMPLETED;
    }
}