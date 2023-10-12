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

    private float x = 0;
    private float y = 0;
    private boolean hasScaled = false;
    private Map map;
    protected ScreenCoordinator screenCoordinator;

    public ChangeMapScript(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public ChangeMapScript(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
    }

    @Override
    protected ScriptState execute() {
        if (!hasScaled) {
            //Cat.setMap("CCEClassroom");
            x = ((x * 48f) - 15f);
            y = ((y * 48f) - 15f);
            hasScaled = true;

        }
        this.map = new CCEClassroom();
        this.player.setMap(map);
        //screenCoordinator.setGameState(GameState.CCE);
        player.setLocation(x, y); // Teleport the player
        return ScriptState.COMPLETED;
    }
}
