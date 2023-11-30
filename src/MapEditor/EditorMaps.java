package MapEditor;

import Level.Map;
import Maps.CCEClassroom;
import Maps.DrawQuest;
import Maps.IceRink;
import Maps.IceRink2;
import Maps.IceRinkNPC;
import Maps.IceRink1;
import Maps.TutorialRoom;
import Maps.TestMap;
import Maps.OrientationRoom;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("OrientationRoom");
            add("TestMap");
            add("TitleScreen");
            add("CCEClassroom");
            add("DrawQuest");
            add("IceRink");
            add("TutorialRoom");
            add("IceRink2");
            add("IceRink1");
            add("IceRinkNPC");
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
            case "IceRink":
                return new IceRink();
            case "OrientationRoom":
                return new OrientationRoom();
            case "TutorialRoom":
                return new TutorialRoom();
            case "IceRink2":
                return new IceRink2();
            case "IceRink1":
                return new IceRink1();
            case "IceRinkNPC":
                return new IceRinkNPC();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
