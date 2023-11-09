package Scripts.IceRink;

import Level.Player;
import Level.Script;
import Level.ScriptState;

// trigger script at beginning of game to set that heavy emotional plot
public class SlipperyIce extends Script {
    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        Player.onIce = true;
        player.setLocation(420,690);
        addTextToTextboxQueue("Wow this ice sure is slippery");
        addTextToTextboxQueue("I'm not sure if I will be able to stop on it");
        
    }

    @Override
    protected void cleanup() {
        setFlag("IsOnIce");
        hideTextbox();
        Player.onIce = false;

        unlockPlayer();
    }

    @Override
    public ScriptState execute() {
        if (!isFlagSet("IsOnIce")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            end();
        }
        return ScriptState.COMPLETED;
    }
}
