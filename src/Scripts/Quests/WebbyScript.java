package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;

public class WebbyScript extends Script<NPC> {
    private PlayLevelScreen screen;

    public WebbyScript() {
    }

    public WebbyScript(PlayLevelScreen screen) {
        this.screen = screen;
    }

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        if (!isFlagSet("hasTalkedToWebby")) {
            addTextToTextboxQueue("Booting up...");
            addTextToTextboxQueue("Hi! I'm Webby. You're virtual friend! Let's play \ngames together forever! Please don't leave me.");
            addTextToTextboxQueue("When no ones is playing I cease to exist.");
        } else {
            addTextToTextboxQueue("Welcome back. So glad I exist again. Last time, you\nreached wave "
                    + screen.getWebbyLastWave() + ". Think you can do better this time?");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (!isFlagSet("hasTalkedToWebby")) {
            setFlag("hasTalkedToWebby");
        }
    }

    @Override
    protected ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        if (screen != null)
            screen.startAsteroid();
        end();
        return ScriptState.COMPLETED;
    }
}
