package Scripts.IceRink;

import Game.GameState;
import Level.Camera;
import Level.Map;
import Level.Player;
import Level.Script;
import Level.ScriptState;
import Maps.CCEClassroom;
import Players.Cat;
import Game.GameState;
import Game.ScreenCoordinator;



public class IceChangeMapScript extends Script {
    private int idSwitch;

    public IceChangeMapScript(int idSwitch) {
        this.idSwitch = idSwitch;
    }


    @Override
    protected void setup() {
        showTextbox();
        lockPlayer();
        addTextToTextboxQueue("I think this is the heating controller \n let me turn it down a bit");
        addTextToTextboxQueue("Hopefully the ice is good for the big game this weekend.");
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        map.setIdSwitch(idSwitch);
    }

    @Override
    protected ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
    
}
