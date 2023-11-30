package Scripts.TutorialRoom;

import Level.Script;
import Level.ScriptState;



public class TutorialChangeMapScript extends Script {
    private int idSwitch;

    public TutorialChangeMapScript(int idSwitch) {
        this.idSwitch = idSwitch;
    }


    @Override
    protected void setup() {

    
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        lockPlayer();
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