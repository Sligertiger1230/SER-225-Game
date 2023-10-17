package MapEditor;

import Level.Map;
import Maps.CCEClassroom;
import Maps.DrawQuest;
import Maps.TestMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("CCEClassroom");
            add("DrawQuest");
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
            case "DrawQuest":
                return new DrawQuest();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
