package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// trigger script at beginning of game to set that heavy emotional plot
public class JavaJohnScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();

        if (!isFlagSet("hasTalkedToJavaJohn")) {
            // addQuest("Help Java John", "Find his glasses", getQuestTriggers());
            addTextToTextboxQueue("Zzz... tap it.. zzz...");
            addTextToTextboxQueue(
                    "Tap i-- huh! Oh, you there! The cafeteria needs me,\nbut I lost my glasses somewhere on the quad.");
            addTextToTextboxQueue(
                    "I refuse to move until I can see. Wouldn't wanna bump \ninto a wild bobcat, those things can kill you!");
            addTextToTextboxQueue("So, could you help me find them?");
        } else {
            addTextToTextboxQueue("AHH. Sorry thought you were a bobcat.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        setFlag("hasTalkedToJavaJohn");
    }

    @Override
    public ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}
