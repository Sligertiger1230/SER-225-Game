package Level;

public class QuestTrigger {
    private Trigger trigger;
    private String mapFileName;

    public QuestTrigger(Trigger trigger, String mapFileName){
        this.trigger = trigger;
        this.mapFileName = mapFileName;
    }

    public Trigger getTrigger(){
        return trigger;
    }

    public String getMapFileName(){
        return mapFileName;
    }

    
}
