package Scripts.IceRink;

import Level.Script;
import Level.ScriptState;
import Scripts.CCEClassroom.ChangeMapScript;
import Utils.Direction;

public class RinkExit extends Script{
    @Override
    protected void setup() {
        new ChangeMapScript(1);
    }

    @Override
    protected void cleanup() {
    }

    
    
    @Override
    public ScriptState execute() {
        return ScriptState.COMPLETED;
    }
}
