package Scripts.Quests;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class JaiswalDrawQuestDrawRoom extends Script<NPC> {
    @Override
    protected void setup() {
        nextStep("Dr. J's Logo Dilemma");
        lockPlayer();
        showPortrait("jaiswalPortrait.png");
        showTextbox();

        if (isFlagSet("hasTalkedToJavaJohn")) {
            addTextToTextboxQueue("Why thank you, dear boy!");
            addTextToTextboxQueue("I couldn't have done this without your amazing\nhelp!");
            addTextToTextboxQueue("Well, I must be off. I have some SER120 projects\nto meticulously grade.");
            addTextToTextboxQueue("I swear if I see one more person use 'i' in\ntheir for-loop...");
            addTextToTextboxQueue("...Ahh, never mind. Good luck with the rest\nof your journey!");
            addTextToTextboxQueue("Use the sign in the bottom right when you are\nready to leave.");
        } else {
            addTextToTextboxQueue("Alright, we have arrived!");
            addTextToTextboxQueue(
                    "My favorite superhero is Superman, and I want to\nmake my logo the emblem of his.");
            addTextToTextboxQueue("The problem is that some of the tiles are missing.");
            addTextToTextboxQueue(
                    "If you can match the tiles to where the blank spaces\nare, that would be amazing!");
            addTextToTextboxQueue(
                    "Just push the tiles by walking up to them and moving\nthem in any direction you need.");
            addTextToTextboxQueue(
                    "Come back and see me when you're done!\nIt will say in the terminal when you have finished.");
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hidePortrait();
        hideTextbox();

        if (!isFlagSet("hasTalkedToJavaJohn")) {
            // set flag so john will react differently when talked to again
            setFlag("hasTalkedToJavaJohn");
        }
        // if user has picked up glasses
        if (isFlagSet("hasPickedUpGlasses")) {
            // hides user since they reached off screen
            // updates next step, since there is no next step quest just completes
            nextStep("Help Java John get his glasses");
        }
    }

    @Override
    public ScriptState execute() {
        // runs this code if you haven't talked to java john
        if (!isFlagSet("hasTalkedToJavaJohn")) {
            // setup() function
            start();
            // goes through text
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            // cleanup() function
            end();
        } else if (isFlagSet("hasPickedUpGlasses")) {
            // setup function
            start();
            // if there is text run
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
        } else if (isFlagSet("hasTalkedToJavaJohn")) {
            // setup function
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            // cleanup function
            end();
        }
        return ScriptState.COMPLETED;
    }
}
