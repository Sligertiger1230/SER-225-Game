package Scripts;

import Utils.Direction;
import Utils.Point;
import Level.Script;
import Level.ScriptState;

public class GraduationCutscene extends Script {

    private int sequence = 0;
    private int moveAmount = 0;
    private boolean judyTalked = false;

    @Override
    protected void setup() {
        lockPlayer();

        nextStep("Graduate!!");

        Point point = getMapTile(2, 3).getLocation();
        player.setX(point.x);
        player.setY(point.y);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        setFlag("Graduation");
    }

    @Override
    protected ScriptState execute() {
        start();
        switch (sequence) {
            case 0:
                player.walk(Direction.DOWN, 2);
                moveAmount += 2;
                if (moveAmount <= 230) {
                    return ScriptState.RUNNING;
                }
                sequence++;
                moveAmount = 0;
                break;
            case 1:
                player.walk(Direction.RIGHT, 2);
                moveAmount += 2;
                if (moveAmount <= 192) {
                    return ScriptState.RUNNING;
                }
                sequence++;
                moveAmount = 0;
                break;
            case 2:
                player.walk(Direction.UP, 2);
                moveAmount += 2;
                if (moveAmount <= 48) {
                    return ScriptState.RUNNING;
                }
                sequence++;
                moveAmount = 0;
                player.stand(Direction.RIGHT);
                break;
            case 3:
                judyConvo();
                if (!isTextboxQueueEmpty()) {
                    return ScriptState.RUNNING;
                }
                sequence++;
                hideTextbox();
                break;
            default:
                break;
        }
        if (sequence <= 3) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }

    public void judyConvo() {
        if (!judyTalked) {
            switch (sequence) {
                case 3:
                    addTextToTextboxQueue(
                            "Congratulations on all students for graduating. \nWe will miss draining you for your student loans.");
                    addTextToTextboxQueue(
                            "All students, one by one please grab your diploma.\nStarting with you Riley");
                    showTextbox();
                    judyTalked = true;
                    break;
            }
        }
    }

}
