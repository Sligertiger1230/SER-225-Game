package Level;

public class QuestTrigger {
    private Trigger trigger;
    private int mapId;

    public QuestTrigger(Trigger trigger, int mapId){
        this.trigger = trigger;
        this.mapId = mapId;
    }

    public Trigger getTrigger(){
        return trigger;
    }

    public int getMapId(){
        return mapId;
    }

    
}
