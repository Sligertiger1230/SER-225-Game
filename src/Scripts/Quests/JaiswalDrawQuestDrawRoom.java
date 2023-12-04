package Scripts.Quests;

import EnhancedMapTiles.PushableBlueTile;
import EnhancedMapTiles.PushableRedTile;
import EnhancedMapTiles.PushableYellowTile;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class JaiswalDrawQuestDrawRoom extends Script<NPC> {

    private PushableBlueTile blueTile;
    private PushableRedTile redTile;
    private PushableYellowTile yellowTile;

    public void setBlueTile(PushableBlueTile blueTile) {
        this.blueTile = blueTile;
    }

    public void setRedTile(PushableRedTile redTile) {
        this.redTile = redTile;
    }

    public void setYellowTile(PushableYellowTile yellowTile) {
        this.yellowTile = yellowTile;
    }

    @Override
    protected void setup() {
        nextStep("Dr. J's Logo Dilemma");
        lockPlayer();
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

    private boolean areTilesInPlace() {
        if (blueTile == null || redTile == null || yellowTile == null) {
            return false;
        }

        int blueMinX = 706;
        int blueMaxX = 726;
        int blueMinY = 566;
        int blueMaxY = 586;

        int redMinX = 323;
        int redMaxX = 343;
        int redMinY = 376;
        int redMaxY = 396;

        int yellowMinX = 423;
        int yellowMaxX = 443;
        int yellowMinY = 566;
        int yellowMaxY = 586;

        // Check if all tiles are within their respective bounds
        return (blueTile.getX() >= blueMinX && blueTile.getX() <= blueMaxX &&
                blueTile.getY() >= blueMinY && blueTile.getY() <= blueMaxY) &&
                (redTile.getX() >= redMinX && redTile.getX() <= redMaxX &&
                        redTile.getY() >= redMinY && redTile.getY() <= redMaxY)
                &&
                (yellowTile.getX() >= yellowMinX && yellowTile.getX() <= yellowMaxX &&
                        yellowTile.getY() >= yellowMinY && yellowTile.getY() <= yellowMaxY);
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
        if (areTilesInPlace()) {
            if (!isFlagSet("questCompleted")) {
                setFlag("questCompleted");
                System.out.println("Quest Complete!");
                return ScriptState.COMPLETED;
            }
        }
        return ScriptState.COMPLETED;
    }
}
