package Level;

import java.util.ArrayList;

public class Quest {
    private String questName, currStep;
    private boolean questCompletionStatus, newQuestStatus;
    private ArrayList<Trigger> triggerList;

    // constructor for new uncompleted quest
    public Quest(String questName, String currStep, ArrayList<Trigger> triggerList) {
        this.questName = questName;
        this.questCompletionStatus = false;
        this.newQuestStatus = true;
        this.currStep = currStep;
        this.triggerList = triggerList;

    }

    // sets most recent status
    public void setCurrStep(String currStep) {
        this.currStep = currStep;
    }

    // gets most recent step
    public String getCurrStep() {
        return currStep;
    }

    // changes quest status
    public void setQuestStatus(boolean questStatus) {
        this.questCompletionStatus = questStatus;
    }

    // returns the quest name
    public String getQuestName() {
        return questName;
    }

    //retrieves the triggerList of a specific quest
    public ArrayList<Trigger> getTriggerList(){
        return triggerList;
    }


    // returns the quest status
    public boolean isQuestStatus() {
        return questCompletionStatus;
    }

    //reports whether the quest is new or not
    public boolean isNewQuestStatus() {
        return isNewQuestStatus();
    }

    //sets new quest status
    public void setNewQuestStatus(boolean newQuestStatus){
        this.newQuestStatus = newQuestStatus;
    }

}
