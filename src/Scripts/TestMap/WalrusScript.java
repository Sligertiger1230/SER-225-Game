package Scripts.TestMap;

import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Level.NPC;
import Level.PlayerListener;
import Level.Script;
import Level.ScriptState;
import Scripts.Quests.JavaJohnWalkScript;

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
        showPortrait("WalrusPortrait.png");
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToWalrus")){
            addTextToTextboxQueue( "Hi Riley!");
            addTextToTextboxQueue( "I seem to have a problem,\nI just caught 2 fish from the pond");
            addTextToTextboxQueue("Can you go get me one of them", selections, answers);
        }
        else{
            if(isFlagSet("hasPickedUpPurpl")){
                addTextToTextboxQueue( "Great, you found him.");
                addTextToTextboxQueue( "Dinner?? \n Why would I eat a Fish?");
                addTextToTextboxQueue( "I'm a vegitarian");
            }
            else if(isFlagSet("hasPickedUpRed")){
                addTextToTextboxQueue( "Great, you found her.");
                addTextToTextboxQueue( "Dinner?? \n Why would I eat a Fish?");
                addTextToTextboxQueue( "I'm a vegitarian");
            }
            /*else if (isFlagSet("RedFish")) {
                // text reminding player to continue quest
                addTextToTextboxQueue("You Still haven't seen it?,\nIt's by the Picnic tables");
            }
            else if (isFlagSet("PurpleFish")) {
                // text reminding player to continue quest
                addTextToTextboxQueue("You Still haven't seen it?,\nIt's by the Enterance to thet Engineering buildings.");
                addTextToTextboxQueue("One of those Engineers must've taken it");
            }*/
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
                getNPC(6).setIsHidden(false);
            }
            else if(currentChoice == 1){
                setFlag("PurpleFish");
                getNPC(5).setIsHidden(false);
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
