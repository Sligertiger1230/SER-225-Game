package MapEditor;

import Level.Map;
import Maps.CCEClassroom;
import Maps.TestMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("CCEClassroom");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "CCEClassroom":
                return new CCEClassroom();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
