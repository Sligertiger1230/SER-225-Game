package Scripts.TutorialRoom;



import Level.Script;
import Level.ScriptState;

public class TutorialScript extends Script {
    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();   
        addTextToTextboxQueue("Test");
    }


    @Override
    protected void cleanup() {
        setFlag("isInTutorialRoom");
        hideTextbox();
        unlockPlayer();
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
