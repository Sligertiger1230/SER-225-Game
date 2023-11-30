package Level;

import java.util.ArrayList;

public class Quest {
    private String questName;
    private int currStep;
    private boolean questCompletionStatus, newQuestStatus;
    private ArrayList<String> stepList;
    private ArrayList<QuestTrigger> triggerList;

    // constructor for new uncompleted quest
    public Quest(String questName, ArrayList<String> stepList, ArrayList<QuestTrigger> triggerList) {
        this.questName = questName;
        this.questCompletionStatus = false;
        this.newQuestStatus = true;
        this.currStep = 0;
        this.stepList = stepList;
        this.triggerList = triggerList;
    }

    // sets most recent status
    public void nextStep() {
        if (!isLastStep())
            currStep++;
        else {
            questCompletionStatus = true;
        }
    }

    // checks if the current step is the last step in the quest
    public boolean isLastStep() {
        if (currStep == stepList.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    // gets most recent step
    public String currStep() {
        return stepList.get(currStep);
    }

    // changes quest status
    public void setQuestStatus(boolean questStatus) {
        this.questCompletionStatus = questStatus;
    }

    // returns the quest name
    public String getQuestName() {
        return questName;
    }

    // retrieves the triggerList of a specific quest
    public ArrayList<QuestTrigger> getTriggerList() {
        return triggerList;
    }

    // returns the quest status
    public boolean isQuestStatus() {
        return questCompletionStatus;
    }

    // reports whether the quest is new or not
    public boolean isNewQuestStatus() {
        return newQuestStatus;
    }

    // sets new quest status
    public void setNewQuestStatus(boolean newQuestStatus) {
        this.newQuestStatus = newQuestStatus;
    }

}
