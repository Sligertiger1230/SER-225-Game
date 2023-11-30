package Scripts;

import Level.Script;
import Level.ScriptState;

public class StartGraduationScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        addTextToTextboxQueue("*DING DONG!* Attention all students! You all have\ngraduated at the same time");
        addTextToTextboxQueue("*Please report to the Gym that we have \n repurposed to host graduation");

        createStepList();
        addStep("Walk to the Orientation room");
        createTriggerList();
        addQuest("Graduate!!");

    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        setFlag("completedAllQuests");
        setFlag("Orientation");
        unsetFlag("Graduation");
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
