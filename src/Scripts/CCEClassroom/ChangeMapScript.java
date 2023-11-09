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
import Level.Sound;

public class ChangeMapScript extends Script {
    private int idSwitch;
    private Sound sound = new Sound();

    public ChangeMapScript(int idSwitch) {
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
        sound.setFile(23);
        sound.play();
        map.setIdSwitch(idSwitch);
        return ScriptState.COMPLETED;
    }
}
