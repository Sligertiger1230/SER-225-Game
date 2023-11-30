package Scripts.CCEClassroom;

import Game.GameState;
import Level.Camera;
import Level.Map;
import Level.Script;
import Level.ScriptState;
import Maps.CCEClassroom;
import Players.Cat;
import Utils.Stopwatch;
import Game.GameState;
import Game.ScreenCoordinator;



public class ChangeMapScriptFromCCE extends Script {
    private int idSwitch;

    public ChangeMapScriptFromCCE(int idSwitch) {
        this.idSwitch = idSwitch;
    }


    @Override
    protected void setup() {
    }

    @Override
    protected void cleanup() {

    }

    @Override
    protected ScriptState execute() {
        map.setIdSwitch(idSwitch);
        player.setLocation(100,100);
        return ScriptState.COMPLETED;
    }
}
