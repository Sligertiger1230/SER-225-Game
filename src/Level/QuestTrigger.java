package Level;

public class QuestTrigger {
    private Trigger trigger;
    private int mapInt;

    public QuestTrigger(Trigger trigger, int mapInt){
        this.trigger = trigger;
        this.mapInt = mapInt;
    }

    public Trigger getTrigger(){
        return trigger;
    }

    public int getMapInt(){
        return mapInt;
    }

    public void setTrigger(Trigger trigger){
        this.trigger = trigger;
    }

    public void setMapInt(int mapInt){
        this.mapInt = mapInt;
    }

    
}
