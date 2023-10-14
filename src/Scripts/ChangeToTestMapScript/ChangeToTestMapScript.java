package Scripts.ChangeToTestMapScript;

import Game.GameState;
import Level.Camera;
import Level.Map;
import Level.Script;
import Level.ScriptState;
import Maps.CCEClassroom;
import Players.Cat;
import Game.GameState;
import Game.ScreenCoordinator;



public class ChangeToTestMapScript extends Script {


    public ChangeToTestMapScript() {
    }


    @Override
    protected void setup() {

    }

    @Override
    protected void cleanup() {

    }

    @Override
    protected ScriptState execute() {
        map.setMapInt(2);
        return ScriptState.COMPLETED;
    }
}
