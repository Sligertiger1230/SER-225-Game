package Scripts.TestMap;

import Level.Script;
import Level.ScriptState;
import Maps.CCEClassroom;
import Players.Cat;;

public class TeleportScript extends Script {

    private float x = 0;
    private float y = 0;
    private boolean hasScaled = false;

    public TeleportScript(float x, float y) {
        this.x = x;
        this.y = y;
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
            // Cat.setMap("CCEClassroom");
            x = ((x * 48f) - 15f);
            y = ((y * 48f) - 15f);
            hasScaled = true;
        }

        player.setLocation(x, y); // Teleport the player
        return ScriptState.COMPLETED;
    }
}
