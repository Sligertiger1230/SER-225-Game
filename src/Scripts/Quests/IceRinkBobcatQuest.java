package Scripts.Quests;

import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Level.NPC;
import Level.PlayerListener;
import Level.Script;
import Level.ScriptState;
import Scripts.CCEClassroom.ChangeMapScript;

import java.security.Principal;
import java.util.ArrayList;

// script for talking to walrus npc
public class IceRinkBobcatQuest extends Script<NPC> {
    private static final String Choice = null;

    // classes that listen to player events can be added to this list
    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    String[] selections = { "Yes", "  No" };
    String[] answers = { "Okay thanks, \n there is a Controll room at the bottom of the rink", "Boo \n How are we gonna Beat Yale now?" };

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key Choice1_KEY = Key.UP;
    protected Key Choice2_KEY = Key.DOWN;

    @Override
    protected void setup() {
        lockPlayer();
        showPortrait("WalrusPortrait.png");
        showTextbox();
        if (isFlagSet("Ice3")){
            if(isFlagSet("Ice2")){
                if(isFlagSet("Ice1")){
                    //creates nathan quest in quest menu
                    createStepList();
                    addStep("Defrost the Ice Rink");
                    createTriggerList();
                    addQuest("Turn down the Heat");

                    addTextToTextboxQueue("Brrrrrrr!");
                    addTextToTextboxQueue("It's really cold in this ice Rink \n The Ice is Freezing too much");
                    addTextToTextboxQueue("Can You help me melt the Ice?");
                    addTextToTextboxQueue("Please go walk into the ice rink below me \n when you are ready to help");
                    addTextToTextboxQueue("You will have to get to the bottom of the rink \n where the thermostat is located");
                    addTextToTextboxQueue("Please be careful \n the ice is slippery");
                    unsetFlag("Ice1");
                }else{
                    addTextToTextboxQueue("Okay some of the ice still hasnt melted \n Can you please go raise the thermostat again");
                    unsetFlag("Ice2");
                }
            }else{
                addTextToTextboxQueue("Okay the is only a little bit of ice left \n Can you please go raise the thermostat one more time");
                unsetFlag("Ice3");
            }
        }
        else{
            addTextToTextboxQueue("Thank you so much for Helping!!!!");
            nextStep("Turn down the Heat");
            getNPC(0).setIsHidden(true);
        }
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
        hidePortrait();
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
