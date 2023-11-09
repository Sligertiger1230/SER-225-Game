package Scripts.Quests.NathanQuest;

import java.util.ArrayList;

import Engine.Key;
import Engine.KeyLocker;
import Level.NPC;
import Level.PlayerListener;
import Level.Script;
import Level.ScriptState;
import Utils.Direction;

// trigger script at beginning of game to set that heavy emotional plot
public class NathanScript extends Script<NPC> {

    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    String[] selections = { "No", "  Yes" };
    String[] answers = { "Come back when you're ready to lose.", "Alright, like I hope you're ready to lose." };

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key Choice1_KEY = Key.UP;
    protected Key Choice2_KEY = Key.DOWN;

    private int amountMoved;
    private int sequence = 0;
    private boolean win = false;
    private boolean attempt2 = false;

    @Override
    protected void setup() {
        lockPlayer();
        showPortrait("nathanPortrait.png", 3);
        showTextbox();
        if (!isFlagSet("winRace")){
            if(!isFlagSet("nathanRunning")){
                if (!isFlagSet("nathanReturn")){
                    if (attempt2){
                        addTextToTextboxQueue("Oh so you've come to race?");
                        addTextToTextboxQueue("I doubt this is going to go well for you.");
                    }
                    else {
                        addTextToTextboxQueue("I'm literally the fastest on campus,\nlike there's literally no one quicker.");
                        addTextToTextboxQueue("I don't wear shoes, and it makes me faster.\nWhy are you looking at me like that?");
                        addTextToTextboxQueue("You literally think you can beat me? Like\nI'd like to see you try.");
                        attempt2 = true;
                    }
                    addTextToTextboxQueue("Do you want to race me?", selections, answers);
                }
                else {
                    addTextToTextboxQueue("You're literally too slow for me.\nCome back later when you're faster");
                }
            }
            else {
                addTextToTextboxQueue("Okay we start on go..");
                addTextToTextboxQueue("3...");
                addTextToTextboxQueue("2...");
                addTextToTextboxQueue("1...");
                addTextToTextboxQueue("GO");
            }
        }
        else {
            addTextToTextboxQueue("Fine, I guess you win.");
            addTextToTextboxQueue("This is literally like so stupid.\nI'm like the fastest.");
        }
    }

    @Override
    protected void cleanup() {
        // //removes text and lets player walk 
        unlockPlayer();
        hideTextbox();
        hidePortrait();

        //sets nathan to run if player says yes
        if (!isFlagSet("nathanRunning")) {
            var currentChoice = getChoice();
            if (currentChoice == 1) {
                setFlag("nathanRunning");
            }
        }
        //after nathan returns back, it unsets both run and return
        else if (isFlagSet("nathanReturn")){
            unsetFlag("nathanRunning");
            unsetFlag("nathanReturn");
        }
        //after nathan finishes running, it sets him to return next time you speak to him
        else if (isFlagSet("nathanRunning")){
            if (win){
                setFlag("winRace");
            }
            else {
                setFlag("nathanReturn");
            }
            unsetFlag("nathanRunning");
        }
    }

    @Override
    public ScriptState execute() {
        if (isFlagSet("winRace")){
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            entity.walk(Direction.LEFT, 4);
            amountMoved += 1;
            if (amountMoved >= 120){
                entity.stand(Direction.LEFT);
                amountMoved = 0;
                entity.setIsHidden(true);
                //getNPC(8).setLocation(288, 1584);
                end();
            }
            else {
                return ScriptState.RUNNING;
            }
        }
        //nathan returning
        else if (isFlagSet("nathanReturn")) {
            start();
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            entity.walk(Direction.LEFT, 4);
            amountMoved += 1;
            if (amountMoved >= 120){
                entity.stand(Direction.LEFT);
                amountMoved = 0;
                getNPC(8).setLocation(288, 1584);
                unsetFlag("nathanReturn");
                end();
            }
            else {
                return ScriptState.RUNNING;
            }
        }
        else if (isFlagSet("nathanRunning")){
            start();
            //nathan race
            //player is not locked, but nathan walks
            //sequence determines whether nathan is moving right or down
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }

            
            else if (sequence == 0) {
                entity.walk(Direction.RIGHT, 4);
                amountMoved += 4;
                if (amountMoved >= 4464) {
                    sequence++;
                    amountMoved = 0;
                }
                //if at any point before nathan finishes running and you make it to this point
                //win will be set as true
                // if (player.getX() >= 4500 && player.getY() >= 2300) {
                //     win = true;
                //     setFlag("winRace");
                // }
                return ScriptState.RUNNING;
            }
            else {
                entity.walk(Direction.DOWN, 4);
                amountMoved += 4;
                //nathan finishes running
                if (amountMoved >= 786) {
                    amountMoved = 0;
                    sequence = 0;
                    entity.stand(Direction.LEFT);
                    if (player.getX() >= 4500 && player.getY() >= 2300) {
                        win = true;
                        setFlag("winRace");
                    }
                    end();
                }
                else {
                    //if at any point before nathan finishes running and you make it to this point
                    //win will be set as true
                    // if (player.getX() >= 4500 && player.getY() >= 2300) {
                    //     win = true;
                    //     setFlag("winRace");
                    // }
                    return ScriptState.RUNNING;
                }
            }
        }
        else {
            //setup() function
            start();
            //goes through text
            if (!isTextboxQueueEmpty()) {
                return ScriptState.RUNNING;
            }
            //cleanup() function
            end();
        }
        return ScriptState.COMPLETED;
    }
}
