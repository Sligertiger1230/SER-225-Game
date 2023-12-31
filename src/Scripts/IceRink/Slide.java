package Scripts.IceRink;

import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Level.MapEntity;
import Level.Script;
import Level.ScriptState;
import Utils.Direction;

// trigger script at beginning of game to set that heavy emotional plot
public class Slide extends Script {

    protected KeyLocker keyLocker = new KeyLocker();
    protected Key MOVE_LEFT_KEY = Key.LEFT;
    protected Key MOVE_RIGHT_KEY = Key.RIGHT;
    protected Key MOVE_UP_KEY = Key.UP;
    protected Key MOVE_DOWN_KEY = Key.DOWN;
    protected Key INTERACT_KEY = Key.SPACE;
    protected Key SPRINT_KEY = Key.SHIFT;

    

    @Override
    protected void setup() {
    }

    @Override
    protected void cleanup() {
        setFlag("PuzzleStarted");
        hideTextbox();
    }

    
    
    @Override
    public ScriptState execute() {


        if(player.getLastWalkingXDirection() == Direction.RIGHT  ){
            float firstX = player.getX();
            //3 times becaue you want it to be 3x the inital speed changing the dx value deosnt do anything
            player.moveXHandleCollision(1);
            player.moveXHandleCollision(1);
            player.moveXHandleCollision(1);
            float finalX = player.getX();
            if(firstX == finalX){
                unlockPlayer();
            }else{
                lockPlayer();
            }

        }
        else if(player.getLastWalkingYDirection() == Direction.UP){
            float firstY = player.getY();
            player.moveYHandleCollision(-1);
            player.moveYHandleCollision(-1);
            player.moveYHandleCollision(-1);
            float finalY = player.getY();
            if(firstY == finalY){
                unlockPlayer();
            }else{
                lockPlayer();
            }

        }
        else if(player.getLastWalkingXDirection() == Direction.LEFT){
            float firstX = player.getX();
            player.moveXHandleCollision(-1);
            player.moveXHandleCollision(-1);
            player.moveXHandleCollision(-1);
            float finalX = player.getX();
            if(firstX == finalX){
                unlockPlayer();
            }else{
                lockPlayer();
            }

        }
        else if(player.getLastWalkingYDirection() == Direction.DOWN){
            float firstY = player.getY();
            player.moveYHandleCollision(1);
            player.moveYHandleCollision(1);
            player.moveYHandleCollision(1);
            float finalY = player.getY();
            if(firstY == finalY){
                unlockPlayer();
            }else{
                lockPlayer();
            }

        }

        return ScriptState.RUNNING;
    }
}
