package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.BobcatGirl;
import NPCs.EmoBoy;
import NPCs.IrishKid;
import NPCs.Judy;
import NPCs.OrientationBoy;
import NPCs.OrientationBoy2;
import NPCs.OrientationGirl;
import Scripts.GraduationCutscene;
import Scripts.OrientationRoom.BobcatGirlScript;
import Scripts.OrientationRoom.EmoBoyScript;
import Scripts.OrientationRoom.IrishKidScript;
import Scripts.OrientationRoom.JudyScript;
import Scripts.OrientationRoom.OrientationBoy2Script;
import Scripts.OrientationRoom.OrientationBoyScript;
import Scripts.OrientationRoom.OrientationGirlScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class Graduation extends Map{


    public Graduation() {
        super("Graduation.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 3).getLocation(); 

        this.mapInt = 10;
        this.idSwitch = 10;
    }
    
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Judy judy = new Judy(0, getMapTile(16, 7).getLocation().subtractY(40));
        judy.setInteractScript(new JudyScript());
        npcs.add(judy);

        ArrayList<Point> points = new ArrayList<Point>();
        for (int npcIndex = 0; npcIndex < 6; npcIndex++){
            points.add(getMapTile(5, 7 - npcIndex).getLocation());
        }

        OrientationBoy orientationBoy = new OrientationBoy(1, points.get(0));
        orientationBoy.setInteractScript(new OrientationBoyScript());
        npcs.add(orientationBoy);

        OrientationGirl oggirl1 = new OrientationGirl(2, points.get(1));
        oggirl1.setInteractScript(new OrientationGirlScript());
        npcs.add(oggirl1);

        EmoBoy emoboy = new EmoBoy(3, points.get(2));
        emoboy.setInteractScript(new EmoBoyScript());
        npcs.add(emoboy);

        BobcatGirl bbGirl = new BobcatGirl(4, points.get(3));
        bbGirl.setInteractScript(new BobcatGirlScript());
        npcs.add(bbGirl);

        IrishKid irishkid = new IrishKid(5, points.get(4));
        irishkid.setInteractScript(new IrishKidScript());
        npcs.add(irishkid);

        OrientationBoy2 ogBoy2 = new OrientationBoy2(6, points.get(5));
        ogBoy2.setInteractScript(new OrientationBoy2Script());
        npcs.add(ogBoy2);

        return npcs;

    }

    public void loadScripts() {
        
    }
    
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger((int)getMapTile(2, 3).getX(), (int)getMapTile(2, 3).getY(), 48, 48, new GraduationCutscene(), "Graduation"));
        return triggers;
    }
}
