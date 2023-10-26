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

    String[] selections = { "Red Fish", "  Purple Fish" };
    String[] answers = { "I think I dropped it right after I left the pond", "I think I left it by the Cafeteria" };

     String[] selections2 = { "1", "  2" };
    String[] answers2 = { "Making sure it can work with diff scripts \n 1, for the same npc", "Making sure it can work with diff scripts \n" + //
            " 2, for the same npc" };

     String[] selections3 = { "3", "  4" };
    String[] answers3 = { "Making sure it can work with diff scripts \n 3, for the same npc", "Making sure it can work with diff scripts \n" + //
            " 4, for the same npc" };

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key Choice1_KEY = Key.UP;
    protected Key Choice2_KEY = Key.DOWN;

    @Override
    protected void setup() {
        lockPlayer();
        showPortrait("WalrusPortrait.png");
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToWalrus")){
            createStepList();
            // steps are the objectives that appear under the quest name in menu
            // wherever you type nextQuest(String questName), as long as the quest name
            // exists and is instantiated within memory,
            // it will advance to the next step. If there is no next step then quest is
            // completed
            // two new steps added
            addStep("Find fish on the quad");
            addStep("Take the fish back \nto Walrus");
            // this.addKeyListener(Keyboard.getKeyListener());
            createTriggerList();
            // adds demonstration trigger
            // REMINDER: triggers need script files to correlate to, don't add triggers
            // without planninf
            // addTrigger(3000, 1550, 10, 200, new JavaJohnWalkScript(),
            // "hasEncounteredJavaJohnWalk", 0);
            addQuest("Help walrus Get a fish");
            lockPlayer();
            setChoice(2);
            addTextToTextboxQueue("Hi Riley!");
            addTextToTextboxQueue("I seem to have a problem,\nI just caught 2 fish from the pond");
            addTextToTextboxQueue("Can you go get me one of them", selections, answers);
        } else if (isFlagSet("hasTalkedToWalrus") && !isFlagSet("hasPickedUpFish")) {
            addTextToTextboxQueue("Well, where's my fish?");
        } else if (isFlagSet("hasPickedUpFish")) {
            if (isFlagSet("redFish")) {
                addTextToTextboxQueue("Great, you found him.");
                addTextToTextboxQueue("Dinner?? \n Why would I eat a Fish?");
                addTextToTextboxQueue("I'm a vegitarian");
                nextStep("Help walrus Get a fish");
                addTextToTextboxQueue("Make sure the textbox works again", selections2, answers2);
                addTextToTextboxQueue("Goodbye my Friend \n I must go Catch More Fish");
                addTextToTextboxQueue("For My aquarium");
            } else if (isFlagSet("purpleFish")) {
                addTextToTextboxQueue("Great, you found her.");
                addTextToTextboxQueue("Dinner?? \n Why would I eat a Fish?");
                addTextToTextboxQueue("I'm a vegitarian");
                nextStep("Help walrus Get a fish");
                addTextToTextboxQueue("Make sure the textbox works again", selections3, answers3);
                addTextToTextboxQueue("Goodbye my Friend \n I must go Catch More Fish");
                addTextToTextboxQueue("For My aquarium");
            }
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        hidePortrait();
        var currentChoice = getChoice();
        if (currentChoice == 0) {
            setFlag("redFish");
            getNPC(5).setIsHidden(false);
        } else if (currentChoice == 1) {
            setFlag("purpleFish");
            getNPC(6).setIsHidden(false);
        }
        if (isFlagSet("hasPickedUpFish")) {
                getNPC(0).setIsHidden(true);
            }

        // set flag so that if walrus is talked to again after the first time, what he
        // says changes
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
