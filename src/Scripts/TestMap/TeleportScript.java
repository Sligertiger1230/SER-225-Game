package Scripts.TestMap;

import Level.Script;
import Level.ScriptState;

public class TeleportScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
    }

    @Override
    protected ScriptState execute() {
        player.setX(1464);
        player.setY(520);
        player.moveDown(100);
        return ScriptState.COMPLETED;
    }
}
