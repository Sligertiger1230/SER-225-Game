package Level;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import SpriteFont.SpriteFont;
import Utils.Stopwatch;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

// Represents the game's textbox
// will display the text it is given to its textQueue
// each String in the textQueue will be displayed in the textbox, and hitting the interact key will cycle between additional Strings in the queue
// use the newline character in a String in the textQueue to break the text up into a second line if needed
public class Textbox {
    protected boolean isActive;
    protected final int x = 22;
    protected final int bottomY = 460;
    protected final int topY = 22;
    protected final int fontX = 35;
    protected final int fontBottomY = 464;
    protected final int fontTopY = 34;
    protected final int width = 750;
    protected final int height = 100;
    protected int currentTextItemHovered = 1;
    protected final int textItemSelected = -1;
    protected int compiledCount = 0;
    protected int choice = -1;

    private Queue<String> textQueue = new LinkedList<String>();
    private Queue<String> textQueueFlip = new LinkedList<String>();
    private Queue<String> selectionQueue = new LinkedList<String>();
    private Queue<String> decideTurn = new LinkedList<String>();
    private SpriteFont text = null;
    private SpriteFont[] selectionText = new SpriteFont[10];
    private String[] responseText = new String[10];
    private KeyLocker keyLocker = new KeyLocker();
    private Map map;
    private Key interactKey = Key.SPACE;
    private Stopwatch keyTimer = new Stopwatch();

    public Textbox(Map map) {
	this.map = map;
	keyTimer.setWaitTime(200);
	compiledCount = 0;
	textQueue = new LinkedList<String>();

    }

    public void addText(String text) {
	if (textQueue.isEmpty()) {
	    keyLocker.lockKey(interactKey);
	}
	textQueue.add(text);
	decideTurn.add("0");
    }

    public void addText(String[] text) {
	if (textQueue.isEmpty()) {
	    keyLocker.lockKey(interactKey);
	}
	for (String textItem : text) {
	    textQueue.add(textItem);
	    decideTurn.add("0");
	}
    }

    // adds text followed by selection options underneath (up to 10)
    public void addSelectableText(String textChat, String[] selectionText) {
	selectionQueue.clear();
	compiledCount = 0;
	/*
	int fontY;
	if (!map.getCamera().isAtBottomOfMap()) {
	    fontY = fontBottomY;
	} else {
	    fontY = fontTopY;
	}
	for (int i = 0; i < this.selectionText.length; i++) {
	    this.selectionText[i] = new SpriteFont("", fontX, fontY, "Arial", 30, Color.black);
	}*/
	if (textQueue.isEmpty()) {
	    keyLocker.lockKey(interactKey);
	}
	textQueue.add(textChat);
	if (selectionQueue.isEmpty()) {
	    keyLocker.lockKey(interactKey);
	}
	selectionQueue.add(textChat);
	for (int i = 0; i < selectionText.length; i++) {
	    selectionQueue.add(selectionText[i]);
	}
	for (int i = 0; i < selectionText.length + 1; i++) {

	    this.selectionText[compiledCount] = spriteFontCompile(selectionQueue);
	    compiledCount++;
	}
	    int fontY;
	    if (!map.getCamera().isAtBottomOfMap()) {
		fontY = fontBottomY;
	    } else {
		fontY = fontTopY;
	    }
	for (int i = selectionText.length + 1; i < this.selectionText.length; i++) {
	    this.selectionText[i] = new SpriteFont("", fontX, fontY, "Arial", 30, Color.black);
	}
	decideTurn.add("1");
    }

    // returns whether the textQueue is out of items to display or not
    // useful for scripts to know when to complete
    public boolean isTextQueueEmpty() {
	return textQueue.isEmpty();
    }

    // returns whether the selectionQueue is empty
    public boolean isSelectionQueueEmpty() {
	return selectionQueue.isEmpty();
    }

    // creates spriteFont for each string in a queue
    public SpriteFont spriteFontCompile(Queue<String> selectionQueue) {
	    int fontY;
	    if (!map.getCamera().isAtBottomOfMap()) {
		fontY = fontBottomY;
	    } else {
		fontY = fontTopY;
	    }
	if (!selectionQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
	    String next = selectionQueue.poll();
	    return new SpriteFont(next, fontX, fontY, "Arial", 30, Color.black);
	} else if (selectionQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
	    return new SpriteFont("", fontX, fontY, "Arial", 30, Color.black);
	}
	return null;
    }

    public void setResponses(String[] responses) {

	for (int i = 0; i < responses.length; i++) {
	    this.responseText[i] = responses[i];

	}
    }

    public void update() {

	// if interact key is pressed, remove the current text from the queue to prepare
	if (!textQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
	    String next = textQueue.peek();

	    // if camera is at bottom of screen, text is drawn at top of screen instead of
	    // the bottom like usual
	    // to prevent it from covering the player
	    int fontY;
	    if (!map.getCamera().isAtBottomOfMap()) {
		fontY = fontBottomY;
	    } else {
		fontY = fontTopY;
	    }
	    text = new SpriteFont(next, fontX, fontY, "Arial", 30, Color.black);
	}

	// if interact key is pressed, remove the current text from the queue to prepare
	// for the next text item to be displayed
	if (Keyboard.isKeyDown(interactKey) && !keyLocker.isKeyLocked(interactKey)) {
	    keyLocker.lockKey(interactKey);
	    textQueue.poll();
	    if (!decideTurn.isEmpty()) {
		if (decideTurn.peek().equals("1")) {
		    while (!textQueue.isEmpty()) {
			textQueueFlip.add(textQueue.poll());
		    }
		    textQueue.add(responseText[currentTextItemHovered - 1]);
		    choice = currentTextItemHovered - 1;
		    currentTextItemHovered = 1;
		    while (!textQueueFlip.isEmpty()) {
			textQueue.add(textQueueFlip.poll());
		    }
		}
	    }
	    decideTurn.poll();
	} else if (Keyboard.isKeyUp(interactKey)) {
	    keyLocker.unlockKey(interactKey);
	}

	if (Keyboard.isKeyDown(Key.RIGHT) && keyTimer.isTimeUp() && selectionText[0] != null) {
	    keyTimer.reset();

	    if (currentTextItemHovered != compiledCount - 1) {
		currentTextItemHovered++;
	    }
	} else if (Keyboard.isKeyDown(Key.LEFT) && keyTimer.isTimeUp() && selectionText[0] != null) {
	    keyTimer.reset();

	    if (currentTextItemHovered != 1) {
		currentTextItemHovered--;
	    }
	}

    }

    public void draw(GraphicsHandler graphicsHandler) {
	// if camera is at bottom of screen, textbox is drawn at top of screen instead
	// of the bottom like usual
	// to prevent it from covering the player
	if (!map.getCamera().isAtBottomOfMap()) {
	    graphicsHandler.drawFilledRectangleWithBorder(x, bottomY, width, height, Color.white, Color.black, 2);
	} else {
	    graphicsHandler.drawFilledRectangleWithBorder(x, topY, width, height, Color.white, Color.black, 2);
	}

	if (text != null) {
	    text.drawWithParsedNewLines(graphicsHandler, 10);
	}
	if (!decideTurn.isEmpty()) {
	    if (selectionText[0] != null && decideTurn.peek().equals("1")) {
		if (this.currentTextItemHovered < compiledCount && this.currentTextItemHovered > 0) {
		    selectionText[currentTextItemHovered].setColor(Color.red);
		}
		if (this.currentTextItemHovered < compiledCount - 1) {
		    selectionText[currentTextItemHovered + 1].setColor(Color.black);
		}
		if (this.currentTextItemHovered > 0) {
		    selectionText[currentTextItemHovered - 1].setColor(Color.black);
		}
		selectionText[0].drawWithParsedNewLines(graphicsHandler, 10);
		int x = fontX;
		for (int i = 0; i < compiledCount; i++) {
		    if (selectionText[i + 1] != null) {
			selectionText[i + 1].setY(fontBottomY + 40);
			selectionText[i + 1].setX(x);
			x += (selectionText[i + 1].getText().length() * 17 + 15);
			selectionText[i + 1].drawWithParsedNewLines(graphicsHandler, 10);
		    }
		}
	    }
	}
    }

    public int getChoice() {
	return choice;
    }

    public void setChoice(int choice) {
	this.choice = choice;
    }

    public boolean isActive() {
	return isActive;
    }

    public void setIsActive(boolean isActive) {
	this.isActive = isActive;
    }

    public void setInteractKey(Key interactKey) {
	this.interactKey = interactKey;
    }
}
