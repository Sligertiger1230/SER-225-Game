package Scripts.TestMap;

import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Level.NPC;
import Level.PlayerListener;
import Level.Script;
import Level.ScriptState;
import java.util.ArrayList;

// script for talking to walrus npc
public class WalrusScript extends Script<NPC> {
     private static final String Choice = null;

    // classes that listen to player events can be added to this list
     protected ArrayList<PlayerListener> listeners = new ArrayList<>();

     String[] selections = { "Red Fish", "  Purple Fish"};
	 String [] answers = {"I think I dropped it right after I left the pond", "I think I left it by the Cafe"};

     // define keys
     protected KeyLocker keyLocker = new KeyLocker();
     protected Key Choice1_KEY = Key.UP;
     protected Key Choice2_KEY = Key.DOWN;

    @Override
    protected void setup() {

       // this.addKeyListener(Keyboard.getKeyListener());
        lockPlayer();
        setChoice(2);
        showPortrait("WalrusPortrait.png");
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToWalrus")){
            addTextToTextboxQueue( "Hi Riley!");
            addTextToTextboxQueue( "I seem to have a problem \nI just caught 2 fish from the pond");
            addTextToTextboxQueue("Can you go get me one of them", selections, answers);
            var CurrentChoice = getChoice();
            if(CurrentChoice == 1){
                setFlag("RedFish");
            }
            if(CurrentChoice == 2){
                setFlag("PurpleFish");
            }
        }
        else{
            var CurrentChoice = getChoice();
            if(CurrentChoice == 1){
                addTextToTextboxQueue( "Go find my fish!!!!\nThe Red One");
            }
            if(CurrentChoice == 2){
                addTextToTextboxQueue( "Go find my fish!!!!\nThe Purple One");
            }
        }
        
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        hidePortrait();

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
