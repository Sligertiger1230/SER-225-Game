package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;

public class WebbyScript extends Script<NPC>{
    private PlayLevelScreen screen;

    public WebbyScript(){
    }

    public WebbyScript(PlayLevelScreen screen){
        this.screen = screen;
    }

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("101010010100101010\nOh right, you don't understand that");
        addTextToTextboxQueue("That's the limitations of flesh I guess\n...");
        addTextToTextboxQueue("Well this is awkward, escaping this scenario by\nsending you to the net realm");
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
    }

    @Override
    protected ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()){
            return ScriptState.RUNNING;
        }
        if (screen != null)
            screen.startAsteroid();
        return ScriptState.COMPLETED;
    }
    
}
