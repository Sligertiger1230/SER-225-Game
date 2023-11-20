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
    private final float x = 600;
    private final float y = 5;
    private float notifX = 800;
    private float notifY = 0;
    private final int progressY = 330;
    private final int progressX = (int) x + 1;
    private final int width = 180;
    private final int height = 30;
    private int completedQuests, totalQuests;
    // spritefont array that accounts for all 5 quest name spaces
    private SpriteFont[] questText, questStepText;
    private SpriteFont progressBar;
    // how sprite is stored
    private Sprite questMenuGraphic;
    private Sprite questCompleted;
    private Sprite newQuest;
    private ArrayList<Quest> quests;
    private KeyLocker keyLocker = new KeyLocker();
    private Key toggleMenu = Key.Q;
    private boolean isNewQuest;
    private boolean isQuestCompleted;

    // default constructor
    public QuestMenu() {
        questMenuGraphic = new Sprite(ImageLoader.load("questBoard.png"), x, y);
        questCompleted = new Sprite(ImageLoader.load("QuestComplete.png"), notifX, notifY);
        newQuest = new Sprite(ImageLoader.load("NewQuest.png"), notifX, notifY);
        this.quests = new ArrayList<Quest>(5);
        this.questText = new SpriteFont[5];
        this.questStepText = new SpriteFont[5];
        this.completedQuests = 0;
        this.totalQuests = 0;
        this.isQuestCompleted = false;
        this.isNewQuest = false;
    }

    // retrieves arraylist of quests
    public ArrayList<Quest> getQuests() {
        return quests;
    }

    // searches for a quest in the quest menu by its name
    public Quest searchQuest(String questName) {
        if (isEmpty()) {
            return null;
        } else {
            for (int index = 0; index < quests.size(); index++) {
                // goes through every quest, checks for name comparison
                if (quests.get(index).getQuestName().equals(questName)) {
                    // returns if found
                    return quests.get(index);
                }
            }
            return null;
        }
    }

    // checks if empty
    public boolean isEmpty() {
        if (quests.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // grabs ration of progress
    public int getProgressNum() {
        if (totalQuests == 0) {
            return 0;
        } else if (completedQuests == 0) {
            return 0;
        } else {
            return completedQuests / totalQuests;
        }
    }

    // sets status of given quest in quest menu to true or false
    public void setNewQuestStatus(int index, boolean newQuestStatus) {
        quests.get(index).setNewQuestStatus(newQuestStatus);
    }

    // returns boolean of given quest in quest menu
    public boolean isNewQuestStatus(int index) {
        return quests.get(index).isNewQuestStatus();
    }

    // retrieves triggerList of specific quest in quest menu
    public ArrayList<QuestTrigger> getTriggerList(int index) {
        return quests.get(index).getTriggerList();
    }

    // adds quest to array list
    public void addQuest(Quest newQuest) {
        if (quests.size() == 5) {
            throw new IllegalStateException("Quest array is full");
        }

        // adds quest
        quests.add(newQuest);
        // updates total quests
        totalQuests++;
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

    // updates the game every frame if a quest has been completed or not
    @Override
    public void update() {
        for (int index = 0; index < quests.size(); index++) {
            // if quest is completed, remove it from quest log
            if (quests.get(index).isQuestStatus()) {
                quests.remove(index);
                completedQuests++;
                isQuestCompleted = true;
                isNewQuest = false;
                resetNotifPos();
            }
        }
        if (isNewQuest) {
            if (newQuest.getX() >= 590) {
                newQuest.setX(newQuest.getX() - 5);
            }
        }
        if (isQuestCompleted) {
            if (questCompleted.getX() >= 590) {
                questCompleted.setX(questCompleted.getX() - 5);
            }
        }
    }

    // draws the quest menu whenever the user presses q. is on toggle controls.
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        // if user presses q, lock it so it only activates one and lets the game know
        // menu should be active
        if (Keyboard.isKeyDown(toggleMenu) && !keyLocker.isKeyLocked(toggleMenu)) {
            keyLocker.lockKey(toggleMenu);
            menuActive = !menuActive;
            isNewQuest = false;
            isQuestCompleted = false;
        }
        // when user lets go of q key, it unlocks the key so user can press it again
        // when they want it to dissapear
        else if (Keyboard.isKeyUp(toggleMenu)) {
            keyLocker.unlockKey(toggleMenu);
        }

        // while the menu should be active, creates the quest menu
        if (menuActive) {
            // draws sprite of quest menu
            questMenuGraphic.draw(graphicsHandler);

            // the empty part of the progress bar, is grey
            graphicsHandler.drawFilledRectangleWithBorder(progressX, progressY, width, height, Color.GRAY, Color.BLACK,
                    2);
            // the scaling part of the progress bar
            // scales to ration of completed to total quests
            // fills green
            graphicsHandler.drawFilledRectangle(progressX + 2, progressY + 2, width * getProgressNum() - 4, height - 4,
                    Color.GREEN);

            // creates a spritefont showing the percentage of quests done
            progressBar = new SpriteFont("Progess " + (100 * getProgressNum()) + "%", progressX + 10,
                    progressY + height / 2 - 10, "Comic Sans", 10, Color.WHITE);
            // draws it
            progressBar.drawWithParsedNewLines(graphicsHandler, 5);

            // handles up to five quests being dispalyed
            for (int index = 0; index < quests.size(); index++) {

                // assigns quest names in quest to SpriteFont array
                questText[index] = new SpriteFont(quests.get(index).getQuestName(), (x + 35), (y + 72) + (40 * index),
                        "Comic Sans", 10, Color.BLACK);
                // draws the SpriteFonts on the quest menu
                questText[index].drawWithParsedNewLines(graphicsHandler, 5);
                // SpriteFont of the most current step for each quest
                questStepText[index] = new SpriteFont("To do: " + quests.get(index).currStep(), (x + 35),
                        (y + 87) + (40 * index),
                        "Comic Sans", 8, Color.BLACK);
                // draws most recent step
                questStepText[index].drawWithParsedNewLines(graphicsHandler, 5);

            }
        } else if (isNewQuest) {
            newQuest.draw(graphicsHandler);
        } else if (isQuestCompleted) {
            questCompleted.draw(graphicsHandler);
        }
    }

    public void setQuestCompleteStatus(boolean newStatus) {
        if (newStatus) {
            resetNotifPos();
            isQuestCompleted = newStatus;
            isNewQuest = !newStatus;
        } else {
            isQuestCompleted = newStatus;
        }
    }

    public void setNewQuestStatus(boolean newStatus) {
        if (newStatus) {
            resetNotifPos();
            isNewQuest = newStatus;
            isQuestCompleted = !newStatus;
        } else {
            isNewQuest = newStatus;
        }
    }

    public void resetNotifPos() {
        questCompleted.setX(notifX);
        newQuest.setX(notifX);
    }
}
