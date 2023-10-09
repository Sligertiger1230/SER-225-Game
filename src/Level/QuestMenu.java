package Level;

import java.awt.Color;
import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import SpriteFont.SpriteFont;
import GameObject.Sprite;
import Engine.Screen;

public class QuestMenu extends Screen {
    private boolean menuActive = false;
    // coordinates for sprite
    private float x = 600;
    private float y = 5;
    private int completedQuests;
    // spritefont array that accounts for all 5 quest name spaces
    private SpriteFont[] questText, questStepText;
    // how sprite is stored
    private Sprite questMenuGraphic;
    private ArrayList<Quest> quests;
    private KeyLocker keyLocker = new KeyLocker();
    private Key toggleMenu = Key.Q;

    // default constructor
    public QuestMenu() {
        this.questMenuGraphic = new Sprite(ImageLoader.load("questBoard.png"), x, y);
        this.quests = new ArrayList<Quest>(5);
        this.questText = new SpriteFont[5];
        this.questStepText = new SpriteFont[5];
        this.completedQuests = 0;
    }

    // retrieves arraylist of quests
    public ArrayList<Quest> getQuests() {
        return quests;
    }

    // checks if empty
    public boolean isEmpty() {
        if (quests.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setNewQuestStatus(int index, boolean newQuestStatus){
        quests.get(index).setNewQuestStatus(newQuestStatus);
    }

    public boolean isNewQuestStatus(int index){
        return quests.get(index).isQuestStatus();
    }

    //retrieves triggerList of specific quest in quest menu
    public ArrayList<Trigger> getTriggerList(int index){
        return quests.get(index).getTriggerList();
    }

    // adds quest to array list
    public void addQuest(Quest newQuest) {
        if (quests.size() == 5) {
            throw new IllegalStateException("Quest array is full");
        }

        quests.add(newQuest);
    }

    // removes quest from array list
    public Quest removeQuest(int index) {
        if (isEmpty()) {
            return null;
        } else {
            Quest placeholder = quests.get(index);
            quests.remove(index);

            // assigns the current index with the quest from the next index, and moves
            // onwards until reaches end
            for (int x = index; index < quests.size() && quests.size() != 1; index++) {
                quests.set(x, quests.get(x + 1));
            }
            return placeholder;
        }
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    //updates the game every frame if a quest has been completed or not
    @Override
    public void update() {
        for (int index = 0; index < quests.size(); index++) {
            // if quest is completed, remove it from quest log
            if (quests.get(index).isQuestStatus()) {
                quests.remove(index);
                completedQuests++;
            }
        }
    }

    //draws the quest menu whenever the user presses q. is on toggle controls.
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        //if user presses q, lock it so it only activates one and lets the game know menu should be active
        if (Keyboard.isKeyDown(toggleMenu) && !keyLocker.isKeyLocked(toggleMenu)) {
            keyLocker.lockKey(toggleMenu);
            menuActive = !menuActive;
        } 
        //when user lets go of q key, it unlocks the key so user can press it again when they want it to dissapear
        else if (Keyboard.isKeyUp(toggleMenu)) {
            keyLocker.unlockKey(toggleMenu);
        }

        //while the menu should be active, creates the quest menu
        if (menuActive) {
            // draws sprite of quest menu
            questMenuGraphic.draw(graphicsHandler);
            // handles up to five quests being dispalyed
            for (int index = 0; index < quests.size(); index++) {

                // assigns quest names in quest to SpriteFont array
                questText[index] = new SpriteFont(quests.get(index).getQuestName(), (x + 35), (y + 72) + (40 * index),
                        "Comic Sans", 10, Color.BLACK);
                // draws the SpriteFonts on the quest menu
                questText[index].drawWithParsedNewLines(graphicsHandler, 5);
                // checks to make sure the current step is not null
                if (quests.get(index).getClass() != null) {
                    // SpriteFont of the most current step for each quest
                    questStepText[index] = new SpriteFont("To do: " + quests.get(index).getCurrStep(), (x + 35),
                            (y + 87) + (40 * index),
                            "Comic Sans", 8, Color.BLACK);
                    // draws most recent step
                    questStepText[index].drawWithParsedNewLines(graphicsHandler, 5);
                }

            }
        }
    }
}
