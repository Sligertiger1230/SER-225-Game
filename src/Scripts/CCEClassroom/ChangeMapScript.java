package Scripts.CCEClassroom;

import Game.GameState;
import Level.Camera;
import Level.Map;
import Level.Script;
import Level.ScriptState;
import Maps.CCEClassroom;
import Players.Cat;
import Game.GameState;
import Game.ScreenCoordinator;



public class ChangeMapScript extends Script {


    public ChangeMapScript() {
    }


    @Override
    protected void setup() {

    }

    @Override
    protected void cleanup() {

    }

    @Override
    protected ScriptState execute() {
        map.setIdSwitch(1);
        return ScriptState.COMPLETED;
    }
}
