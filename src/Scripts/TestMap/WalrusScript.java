package Scripts.TestMap;

import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Level.NPC;
import Level.PlayerListener;
import Level.Script;
import Level.ScriptState;
import Scripts.Quests.JavaJohnWalkScript;

import java.security.Principal;
import java.util.ArrayList;

// script for talking to walrus npc
public class WalrusScript extends Script<NPC> {
     private static final String Choice = null;

    // classes that listen to player events can be added to this list
     protected ArrayList<PlayerListener> listeners = new ArrayList<>();

     String[] selections = { "Red Fish", "  Purple Fish"};
	 String [] answers = {"I think I dropped it right after I left the pond", "I think I left it by the Cafeteria"};

     // define keys
     protected KeyLocker keyLocker = new KeyLocker();
     protected Key Choice1_KEY = Key.UP;
     protected Key Choice2_KEY = Key.DOWN;

    @Override
    protected void setup() {
        lockPlayer();
        showPortrait("WalrusPortrait.png");
        showTextbox();

        if(isFlagSet("hasPickedUpRed") || isFlagSet("hasPickUpPurp") ){
            System.out.println("hi");
            addTextToTextboxQueue( "Great, you found him.");
            addTextToTextboxQueue( "Dinner?? \n Why would I eat a Fish?");
            addTextToTextboxQueue( "I'm a vegitarian");
        } else {
            createStepList();
            //steps are the objectives that appear under the quest name in menu
            //wherever you type nextQuest(String questName), as long as the quest name exists and is instantiated within memory, 
            //it will advance to the next step. If there is no next step then quest is completed
            //two new steps added
            addStep("Find fish on the quad");
            addStep("Take the fish back \nto Walrus");
            // this.addKeyListener(Keyboard.getKeyListener());
            createTriggerList();
            //adds demonstration trigger
            //REMINDER: triggers need script files to correlate to, don't add triggers without planninf
            //addTrigger(3000, 1550, 10, 200, new JavaJohnWalkScript(), "hasEncounteredJavaJohnWalk", 0);
            addQuest("Help walrus Get a fish");
            lockPlayer();
            setChoice(2);
            addTextToTextboxQueue( "Hi Riley!");
            addTextToTextboxQueue( "I seem to have a problem,\nI just caught 2 fish from the pond");
            addTextToTextboxQueue("Can you go get me one of them", selections, answers);
        }

    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        hidePortrait();
        var currentChoice = getChoice();
        if(currentChoice == 0){
                setFlag("RedFish");
                getNPC(5).setIsHidden(false);
            }
            else if(currentChoice == 1){
                setFlag("PurpleFish");
                getNPC(6).setIsHidden(false);
            }

        // set flag so that if walrus is talked to again after the first time, what he says changes
        setFlag("hasTalkedToWalrus");
    }

    @Override
    public ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}
