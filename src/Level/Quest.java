package Level;

public class Quest {
    private String questName, currStep;
    private boolean questCompletionStatus;

    //constructor for name and status
    public Quest(String questName, boolean questCompletionStatus, String currStep) {
        this.questName = questName;
        this.questCompletionStatus = questCompletionStatus;
        this.currStep = currStep;
    }

    //constructor for just name
    public Quest(String questName, String currStep) {
        this.questName = questName;
        this.questCompletionStatus = false;
        this.currStep = currStep;
    }

    public void setCurrStep(String currStep){
        this.currStep = currStep;
    }

    public String getCurrStep(){
        return currStep;
    }

    //changes quest status
    public void setQuestStatus(boolean questStatus) {
        this.questCompletionStatus = questStatus;
    }

    //returns the quest name
    public String getQuestName() {
        return questName;
    }

    //returns the quest status
    public boolean isQuestStatus() {
        return questCompletionStatus;
    }

}
