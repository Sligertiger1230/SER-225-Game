package Scripts.TutorialRoom;


import java.util.ArrayList;
import java.awt.event.KeyEvent;

import Engine.Key;
import Engine.Keyboard;
import Level.PlayerState;
import Level.Script;
import Level.ScriptState;
import Level.Trigger;
import Scripts.CCEClassroom.ChangeMapScript;
import Utils.Direction;

public class TutorialTextScript extends Script {
    private int Sequence = 0;

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        String[] selections = { "Play the tutorial", "  Wake up" };
        String[] answers = { "Good choice my boy.", "Farewell sweet boy." };
        
        var currentChoice = getChoice();
        System.out.println(getChoice());
            if (currentChoice == 0) {
                setFlag("choosesTutorial");
            } else if (currentChoice == 1){
                setFlag("skipsTutorial");
            }

        
        if (isFlagSet("isSprinting")){
           addTextToTextboxQueue("Son, I think you're ready for college.");
                addTextToTextboxQueue("Your mother and I are so proud.");
                addTextToTextboxQueue("Use Q to access the quest menu.");
                addTextToTextboxQueue("You'll encounter people who need\nyour help.");
                    setFlag("completedTutorial"); 
                    setFlag("returningFromTutorial");

        } else if(isFlagSet("skipsTutorial")){
            addTextToTextboxQueue("You feel yourself waking up..");
                        setFlag("completedTutorial");
                        setFlag("returningFromTutorial");
            
        } else if(isFlagSet("hasWalkedForward")){
            if (Sequence == 1) {
                Sequence++;
                addTextToTextboxQueue("Great job using your legs there buddy.");
                addTextToTextboxQueue("Lets move on to something more\nchallenging.");
                addTextToTextboxQueue("You can hold the shift button to sprint.");
                addTextToTextboxQueue("You'll go really fast with this.\ntry it out.");
            }
        
        } else if (isFlagSet("choosesTutorial")){
            if (Sequence == 0) {
                Sequence++;
                addTextToTextboxQueue("Lets begin with movement.");
                addTextToTextboxQueue("Use the arrow keys to move around.");
                addTextToTextboxQueue("For now, just walk forward using\nthe up arrow.");
            }
               
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
            map.setIdSwitch(4);
            return ScriptState.COMPLETED; 
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
        if (Keyboard.isKeyDown(Key.DOWN) || (Keyboard.isKeyDown(Key.UP) || (Keyboard.isKeyDown(Key.LEFT) || (Keyboard.isKeyDown(Key.RIGHT))))){
            setFlag("hasWalkedForward");

            }
        }
        end();
        return ScriptState.COMPLETED;
    }
}