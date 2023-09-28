package Scripts.TestMap;

import Level.Script;
import Level.ScriptState;

public class placeHolderScript extends Script {
    private String[] steps = new String[2];

    @Override
    protected void setup() {
        //shows step values
        steps[0] = "Hit trigger one";
        steps[1] = "Hit trigger two";
    }

    @Override
    protected void cleanup() {
    }

    @Override
    protected ScriptState execute() {
        start();
        // if placeholder1 flag hasn't been set
        if (!isFlagSet("placeholder1")) {
            //updates the current step by one for each quest
            for (int index = 0; index < 5; index++) {
                getQuestMenu().getQuests().get(index).setCurrStep(steps[1]);
            }
            //sets flag so doesnt run again
            setFlag("placeholder1");
            return ScriptState.RUNNING;
        }
        // if placeholder2 flag hasn't been set
        else if (!isFlagSet("placeholder2") && isFlagSet("placeholder1")) {
            //marks all quests as complete
            for (int index = 0; index < 5; index++){
                getQuestMenu().getQuests().get(index).setQuestStatus(true);
            }
            //sets flag so doesnt run again
            setFlag("placeholder2");
            return ScriptState.RUNNING;
        }
        return ScriptState.COMPLETED;
    }

}
