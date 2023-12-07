package Scripts.Quests;

import Asteroid.AsteroidState;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;

public class AsteroidNPCScript extends Script<NPC> {
    PlayLevelScreen screen;

    public AsteroidNPCScript() {
        this.screen = null;
    }

    public AsteroidNPCScript(PlayLevelScreen screen) {
        this.screen = screen;
    }

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        showPortrait("asteroidNPCPortrait.png", 3);
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
                                "This isn't just some game. People die here, now take\nthis seriously and get in there and win!");
                        break;
                    case WIN:
                        addTextToTextboxQueue(
                                "Well good job cadet-- I mean private. That's right,\nI promoted you. Come back to me again when you");
                        addTextToTextboxQueue("want a real challenge.");
                        break;
                    case RUNNING:
                    case START:
                        addTextToTextboxQueue(
                                "Well? You won't destroy viruses like this, get back in\nand fight again.");
                        break;
                }
            }
        } else if (!isFlagSet("hasCompleted10Waves")) {
            if (screen.getAsteroidNPCLastMaxWave() == 5) {
                addTextToTextboxQueue(
                        "Your next mission, you'll need to endure against the\nviruses for as long as possible. Finish this mission and");
                addTextToTextboxQueue("I'll make you a lieutenant kid. Survive 10 waves");
            } else {
                switch (screen.getAsteroidNPCLastState()) {
                    case WIN:
                        addTextToTextboxQueue(
                                "Wow, you're a prodigy. We gotta get you back in there \nASAP. I'm promoting you to general. Come back to me");
                        addTextToTextboxQueue("and I'll give a final mission.");
                    case RUNNING:
                    case START:
                    case DEAD:
                        addTextToTextboxQueue("Many soldiers have quit at your point. Do not falter.\nRISE RAGE");
                        break;
                }
            }
        } else if (!isFlagSet("hasCompleted15Waves")) {
            if (screen.getAsteroidNPCLastMaxWave() == 10) {
                addTextToTextboxQueue(
                        "Alright, you've gained my respect. Wanna be second\nin command? Beat this final mission. 15 waves.");
            } else {
                switch (screen.getAsteroidNPCLastState()) {
                    case WIN:
                        addTextToTextboxQueue(
                                "Comrades, soliders, the fallen and the forsaken. They\nfought for this very moment.");
                        addTextToTextboxQueue("I, Commander Erwin, knight you as Second-in\n-Command General Ryan.");
                        break;
                    case RUNNING:
                    case DEAD:
                    case START:
                        addTextToTextboxQueue(
                                "You stand on the bodies of the dead, you've almost \npeaked this hill of bloodshed. Don't you dare quit.");
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
            createTriggerList();
            createStepList();
            addStep("Complete your first mission");
            addStep("Complete your second mission");
            addStep("Complete your third mission");
            addQuest("Cyber Warfare");
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
                nextStep("Cyber Warfare");
            }
        } else if (!isFlagSet("hasCompleted10Waves")) {
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            if (screen != null && (screen.getAsteroidNPCLastState() != AsteroidState.WIN
                    || screen.getAsteroidNPCLastMaxWave() == 5)) {
                screen.startQuestAsteroid(10);
            } else {
                setFlag("hasCompleted10Waves");
                nextStep("Cyber Warfare");
            }
        } else if (!isFlagSet("hasCompleted15Waves")) {
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            if (screen != null && (screen.getAsteroidNPCLastState() != AsteroidState.WIN
                    || screen.getAsteroidNPCLastMaxWave() == 10)) {
                screen.startQuestAsteroid(15);
            } else {
                setFlag("hasCompleted15Waves");
                nextStep("Cyber Warfare");
            }
        }
        end();
        return ScriptState.COMPLETED;
    }
}
