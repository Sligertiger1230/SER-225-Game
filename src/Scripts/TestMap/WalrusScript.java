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
     // classes that listen to player events can be added to this list
     protected ArrayList<PlayerListener> listeners = new ArrayList<>();

     // define keys
     protected KeyLocker keyLocker = new KeyLocker();
     protected Key Choice1_KEY = Key.UP;
     protected Key Choice2_KEY = Key.DOWN;

    @Override
    protected void setup() {

       // this.addKeyListener(Keyboard.getKeyListener());
        lockPlayer();
        showPortrait("WalrusPortrait.png");
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToWalrus")){
            addTextToTextboxQueue( "Hi Cat!");
            addTextToTextboxQueue( "...oh, you lost your ball?");
            addTextToTextboxQueue("oops");
        }
        else {
            addTextToTextboxQueue( "I sure love doing walrus things!");
             choiceAddTextToTextboxQueue("1", "2");
        }
        entity.facePlayer(player);
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
