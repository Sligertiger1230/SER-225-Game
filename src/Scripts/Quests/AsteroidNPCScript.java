package Scripts.Quests;

import Asteroid.AsteroidState;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;

public class AsteroidNPCScript extends Script<NPC> {
    PlayLevelScreen screen;

    public AsteroidNPCScript() {

    }

    public AsteroidNPCScript(PlayLevelScreen screen) {
        this.screen = screen;
    }

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        showPortrait("asteroidNPCState.png", 3);
        entity.facePlayer(player);

        if (!isFlagSet("hasTalkedToAsteroidNPC")) {
            addTextToTextboxQueue(
                    "Oh... hey. Welcome to the IT department... or what's\nleft of it. They downsized us for new fertilizer on the");
            addTextToTextboxQueue(
                    "quad. I'm the only thing left that stands in between the \nelectronics here, and the deadly viruses that attack it.");
            addTextToTextboxQueue(
                    "All my comrades have died in cyber wars. I'm\nsurrounded by the souls of the fallen. Oh I'm rambling");
            addTextToTextboxQueue(
                    "again. I'm sorry I'm getting old it happens. You know\nwhat kid. You have a glimmer in your eye. Come talk");
            addTextToTextboxQueue("to me sometime if you think you're even half as good\nof a pilot as I am.");
        } else if (!isFlagSet("hasCompleted5Waves")) {
            if (screen.getAsteroidNPCLastState() == null) {
                addTextToTextboxQueue(
                        "Alright pilot-in-training. I have a test for you,\nif you can beat 5 waves of viruses I'll promote you");
                addTextToTextboxQueue("to private. Ready? Doesn't matter.");
            } else {
                switch (screen.getAsteroidNPCLastState()) {
                    case DEAD:
                        addTextToTextboxQueue(
                                "This isn't just some game. People die here, now take this seriously\n and get in there and win!");
                        break;
                    case WIN:
                        addTextToTextboxQueue(
                                "Well good job cadet-- I mean private. That's right, I promoted you\nCome back to me again when you want a");
                        addTextToTextboxQueue("real challenge.");
                        break;
                    case RUNNING:
                    case START:
                        addTextToTextboxQueue(
                                "Well? You won't destroy viruses like this, get back in and fight again.");
                        break;
                }
            }
        } else if (!isFlagSet("hasCompleted10Waves")) {
            if (screen.getAsteroidNPCLastMaxWave() == 5) {
                addTextToTextboxQueue(
                        "Your next mission, you'll need to endure against the\nviruses for as long as possible. Finish this mission and");
                addTextToTextboxQueue("I'll make you a lieutenant kid.");
            } else {
                switch (screen.getAsteroidNPCLastState()) {
                    case WIN:
                        addTextToTextboxQueue(
                                "Wow, you're a prodigy. We gotta get you back in there ASAP. I'm promoting you to\ngeneral. Come back to me and I'll");
                        addTextToTextboxQueue("give a final mission.");
                    case RUNNING:
                    case START:
                    case DEAD:
                        addTextToTextboxQueue("Many soldiers have quit at your point. Do not falter. RISE RAGE");
                        break;
                }
            }
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        hidePortrait();

        if (!isFlagSet("hasTalkedToAsteroidNPC")) {
            setFlag("hasTalkedToAsteroidNPC");
        }
    }

    @Override
    protected ScriptState execute() {
        start();
        if (!isFlagSet("hasTalkedToAsteroidNPC")) {
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
        } else if (!isFlagSet("hasCompleted5Waves")) {
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            if (screen != null && screen.getAsteroidNPCLastState() != AsteroidState.WIN) {
                screen.startQuestAsteroid(5);
            } else {
                setFlag("hasCompleted5Waves");
            }
        } else if (!isFlagSet("hasCompleted10Waves")) {
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            if (screen != null && (screen.getAsteroidNPCLastState() != AsteroidState.WIN
                    || screen.getAsteroidNPCLastMaxWave() == 5)) {
                screen.startQuestAsteroid(5);
            } else {
                setFlag("hasCompleted10Waves");
            }

            end();
            return ScriptState.COMPLETED;
        }
    }
}
