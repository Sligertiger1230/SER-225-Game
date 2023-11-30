package Scripts;

import Level.Script;
import Level.ScriptState;

public class StartGraduationScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        
        addTextToTextboxQueue("*DING DONG!* Attention all students! You all have graduated\nat the same time");
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        setFlag("completedAllQuests");
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
