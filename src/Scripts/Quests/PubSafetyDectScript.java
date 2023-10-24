package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class PubSafetyDectScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showPortrait("PubSafetyDectPortrait.png", 4);
        showTextbox();

        addTextToTextboxQueue("Keep your eyes out for criminals kid");
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
        hidePortrait();
    }

    @Override
    protected ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()){
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }

}
