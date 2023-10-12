package Level;

import Engine.Config;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import Engine.ScreenManager;
import GameObject.Sprite;
import Utils.Direction;
import Utils.Point;
import Maps.TestMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    This class is for defining a map that is used for a specific level
    The map class handles/manages a lot of different things, including:
    1. tile map -- the map tiles that make up the map
    2. entities in the map -- this includes enemies, enhanced map tiles, and npcs
    3. the map's camera, which does a lot of work itself in the Camera class
    4. adjusting camera location based off of player location
    5. calculating which tile a game object is currently on based on its x and y location
*/

public abstract class Map {
    // the tile map (map tiles that make up the entire map image)
    protected MapTile[] mapTiles;

    // width and height of the map in terms of the number of tiles width-wise and
    // height-wise
    protected int width;
    protected int height;
    protected int mapInt;

    // the tileset this map uses for its map tiles
    protected Tileset tileset;

    // camera class that handles the viewable part of the map that is seen by the
    // player of a game during a level
    protected Camera camera;

    // location player should start on when this map is first loaded
    protected Point playerStartPosition;

    // the location of the "mid point" of the screen
    // this is what tells the game that the player has reached the center of the
    // screen, therefore the camera should move instead of the player
    // this goes into creating that "map scrolling" effect
    protected int xMidPoint, yMidPoint;

    // in pixels, this basically creates a rectangle defining how big the map is
    // startX and Y will always be 0, endX and Y is the number of tiles multiplied
    // by the number of pixels each tile takes up
    protected int startBoundX, startBoundY, endBoundX, endBoundY;

    // the name of the map text file that has the tile map information
    protected String mapFileName;

    // lists to hold map entities that are a part of the map
    protected ArrayList<EnhancedMapTile> enhancedMapTiles;
    protected ArrayList<NPC> npcs;
    protected ArrayList<Trigger> triggers;
    protected ArrayList<Trigger> updatedTriggers;

    protected Script activeInteractScript;

    // if set to false, camera will not move as player moves
    protected boolean adjustCamera = true;

    // map tiles in map that are animated
    protected ArrayList<MapTile> animatedMapTiles;

    // flag manager instance to keep track of flags set while map is loaded
    protected FlagManager flagManager;

    // map's textbox instance
    protected Textbox textbox;

    // map's Portrait instance
    protected Portrait portrait;

    // map's quest menu
    protected QuestMenu questMenu;

    public Map(String mapFileName, Tileset tileset) {
        this.mapFileName = mapFileName;
        this.tileset = tileset;
        setupMap();
        this.startBoundX = 0;
        this.startBoundY = 0;
        this.endBoundX = width * tileset.getScaledSpriteWidth();
        this.endBoundY = height * tileset.getScaledSpriteHeight();
        this.xMidPoint = ScreenManager.getScreenWidth() / 2;
        this.yMidPoint = (ScreenManager.getScreenHeight() / 2);
        this.playerStartPosition = new Point(0, 0);
    }

    // sets up map by reading in the map file to create the tile map
    // loads in enemies, enhanced map tiles, and npcs
    // and instantiates a Camera
    public void setupMap() {
        animatedMapTiles = new ArrayList<>();

        loadMapFile();

        this.enhancedMapTiles = loadEnhancedMapTiles();
        for (EnhancedMapTile enhancedMapTile : this.enhancedMapTiles) {
            enhancedMapTile.setMap(this);
        }

        this.npcs = loadNPCs();
        for (NPC npc : this.npcs) {
            npc.setMap(this);
        }

        this.triggers = loadTriggers();
        for (Trigger trigger : this.triggers) {
            trigger.setMap(this);
        }

        this.loadScripts();

        this.camera = new Camera(0, 0, tileset.getScaledSpriteWidth(), tileset.getScaledSpriteHeight(), this);
        this.textbox = new Textbox(this);
        this.portrait = new Portrait(this);
        //instantiates quest menu that draws on screen
        this.questMenu = new QuestMenu();

    }

    // reads in a map file to create the map's tilemap
    private void loadMapFile() {
        Scanner fileInput;
        try {
            // open map file that is located in the MAP_FILES_PATH directory
            fileInput = new Scanner(new File(Config.MAP_FILES_PATH + this.mapFileName));
        } catch (FileNotFoundException ex) {
            // if map file does not exist, create a new one for this map (the map editor
            // uses this)
            System.out.println(
                    "Map file " + Config.MAP_FILES_PATH + this.mapFileName + " not found! Creating empty map file...");

            try {
                createEmptyMapFile();
                fileInput = new Scanner(new File(Config.MAP_FILES_PATH + this.mapFileName));
            } catch (IOException ex2) {
                ex2.printStackTrace();
                System.out.println("Failed to create an empty map file!");
                throw new RuntimeException();
            }
        }

        // read in map width and height from the first line of map file
        this.width = fileInput.nextInt();
        this.height = fileInput.nextInt();

        // define array size for map tiles, which is width * height (this is a standard
        // array, NOT a 2D array)
        this.mapTiles = new MapTile[this.height * this.width];
        fileInput.nextLine();

        // read in each tile index from the map file, use the defined tileset to get the
        // associated MapTile to that tileset, and place it in the array
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int tileIndex = fileInput.nextInt();
                int xLocation = j * tileset.getScaledSpriteWidth();
                int yLocation = i * tileset.getScaledSpriteHeight();
                MapTile tile = tileset.getTile(tileIndex).build(xLocation, yLocation);
                tile.setMap(this);
                setMapTile(j, i, tile);

                if (tile.isAnimated()) {
                    animatedMapTiles.add(tile);
                }
            }
        }

        fileInput.close();
    }

    // creates an empty map file for this map if one does not exist
    // defaults the map dimensions to 0x0
    private void createEmptyMapFile() throws IOException {
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(Config.MAP_FILES_PATH + this.mapFileName);
        fileWriter.write("0 0\n");
        fileWriter.close();
    }

    // gets player start position based on player start tile (basically the start
    // tile's position on the map)
    public Point getPlayerStartPosition() {
        return playerStartPosition;
    }

    // get position on the map based on a specific tile index
    public Point getPositionByTileIndex(int xIndex, int yIndex) {
        MapTile tile = getMapTile(xIndex, yIndex);
        return new Point(tile.getX(), tile.getY());
    }

    public Tileset getTileset() {
        return tileset;
    }

    public String getMapFileName() {
        return mapFileName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidthPixels() {
        return width * tileset.getScaledSpriteWidth();
    }

    public int getHeightPixels() {
        return height * tileset.getScaledSpriteHeight();
    }

    public MapTile[] getMapTiles() {
        return mapTiles;
    }

    public void setMapInt(int mapInt){
       //0 for main map, 1 for cce 
       this.mapInt = mapInt;
    }
    public int getMapInt(){
       return mapInt;
    }

    public void setMapTiles(MapTile[] mapTiles) {
        this.mapTiles = mapTiles;
    }

    // get specific map tile from tile map
    public MapTile getMapTile(int x, int y) {
        if (isInBounds(x, y)) {
            return mapTiles[getConvertedIndex(x, y)];
        } else {
            return null;
        }
    }

    // set specific map tile from tile map to a new map tile
    public void setMapTile(int x, int y, MapTile tile) {
        if (isInBounds(x, y)) {
            MapTile oldMapTile = getMapTile(x, y);
            animatedMapTiles.remove(oldMapTile);
            mapTiles[getConvertedIndex(x, y)] = tile;
            if (tile.isAnimated()) {
                animatedMapTiles.add(tile);
            }
        }
    }

    // returns a tile based on a position in the map
    public MapTile getTileByPosition(float xPosition, float yPosition) {
        Point tileIndex = getTileIndexByPosition(xPosition, yPosition);
        if (isInBounds(Math.round(tileIndex.x), Math.round(tileIndex.y))) {
            return getMapTile(Math.round(tileIndex.x), Math.round(tileIndex.y));
        } else {
            return null;
        }
    }

    // returns the index of a tile (x index and y index) based on a position in the
    // map
    public Point getTileIndexByPosition(float xPosition, float yPosition) {
        int xIndex = Math.round(xPosition) / tileset.getScaledSpriteWidth();
        int yIndex = Math.round(yPosition) / tileset.getScaledSpriteHeight();
        return new Point(xIndex, yIndex);
    }

    // checks if map tile being requested is in bounds of the tile map array
    private boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    // since tile map array is a standard (1D) array and not a 2D,
    // instead of doing [y][x] to get a value, instead the same can be achieved with
    // x + width * y
    private int getConvertedIndex(int x, int y) {
        return x + width * y;
    }

    // list of enemies defined to be a part of the map, should be overridden in a
    // subclass
    protected void loadScripts() {
    }

    // list of enhanced map tiles defined to be a part of the map, should be
    // overridden in a subclass
    protected ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        return new ArrayList<>();
    }

    // list of npcs defined to be a part of the map, should be overridden in a
    // subclass
    protected ArrayList<NPC> loadNPCs() {
        return new ArrayList<>();
    }

    protected ArrayList<Trigger> loadTriggers() {
        return new ArrayList<>();
    }

    protected ArrayList<Trigger> updateTriggers() {
        return new ArrayList<>();
    }

    public Camera getCamera() {
        return camera;
    }

    public ArrayList<EnhancedMapTile> getEnhancedMapTiles() {
        return enhancedMapTiles;
    }

    public ArrayList<NPC> getNPCs() {
        return npcs;
    }

    public ArrayList<Trigger> getTriggers() {
        return triggers;
    }

    public int getTriggersSize() {
        return triggers.size();
    }

    public ArrayList<MapTile> getAnimatedMapTiles() {
        return animatedMapTiles;
    }

    public Script getActiveInteractScript() {
        return activeInteractScript;
    }

    // this method is only used to set activeInteractScript back to null after the
    // script is finished running
    public void setActiveInteractScript(Script script) {
        activeInteractScript = script;
    }

    public NPC getNPCById(int id) {
        for (NPC npc : npcs) {
            if (npc.getId() == id) {
                return npc;
            }
        }
        return null;
    }

    // returns all active enhanced map tiles (enhanced map tiles that are a part of
    // the current update cycle) -- this changes every frame by the Camera class
    public ArrayList<EnhancedMapTile> getActiveEnhancedMapTiles() {
        return camera.getActiveEnhancedMapTiles();
    }

    // returns all active npcs (npcs that are a part of the current update cycle) --
    // this changes every frame by the Camera class
    public ArrayList<NPC> getActiveNPCs() {
        return camera.getActiveNPCs();
    }

    public ArrayList<Trigger> getActiveTriggers() {
        return camera.getActiveTriggers();
    }

    // add an enhanced map tile to the map's list of enhanced map tiles
    public void addEnhancedMapTile(EnhancedMapTile enhancedMapTile) {
        enhancedMapTile.setMap(this);
        this.enhancedMapTiles.add(enhancedMapTile);
    }

    // add an npc to the map's list of npcs
    public void addNPC(NPC npc) {
        npc.setMap(this);
        this.npcs.add(npc);
    }

    // add a trigger to the map's list of triggers
    public void addTrigger(Trigger trigger) {
        trigger.setMap(this);
        this.triggers.add(trigger);
    }

    public void setAdjustCamera(boolean adjustCamera) {
        this.adjustCamera = adjustCamera;
    }

    public ArrayList<MapEntity> getSurroundingMapEntities(Player player) {
        ArrayList<MapEntity> surroundingMapEntities = new ArrayList<>();

        // gets surrounding tiles
        Point playerCurrentTile = getTileIndexByPosition((int) player.getBoundsX1(), (int) player.getBoundsY1());
        for (int i = (int) playerCurrentTile.y - 1; i <= playerCurrentTile.y + 1; i++) {
            for (int j = (int) playerCurrentTile.x - 1; j <= playerCurrentTile.x + 1; j++) {
                MapTile mapTile = getMapTile(j, i);
                if (mapTile != null && mapTile.getInteractScript() != null) {
                    surroundingMapEntities.add(mapTile);
                }
            }
        }
        // gets active surrounding npcs
        surroundingMapEntities.addAll(getActiveNPCs());
        surroundingMapEntities.addAll(getActiveEnhancedMapTiles());
        return surroundingMapEntities;
    }

    public void entityInteract(Player player) {
        ArrayList<MapEntity> surroundingMapEntities = getSurroundingMapEntities(player);
        ArrayList<MapEntity> playerTouchingMapEntities = new ArrayList<>();
        for (MapEntity mapEntity : surroundingMapEntities) {
            if (mapEntity.getInteractScript() != null && mapEntity.intersects(player.getInteractionRange())) {
                playerTouchingMapEntities.add(mapEntity);
            }
        }
        MapEntity interactedEntity = null;
        if (playerTouchingMapEntities.size() == 1) {
            if (isInteractedEntityValid(playerTouchingMapEntities.get(0), player)) {
                interactedEntity = playerTouchingMapEntities.get(0);
            }
        } else if (playerTouchingMapEntities.size() > 1) {
            MapEntity currentLargestAreaOverlappedEntity = null;
            float currentLargestAreaOverlapped = 0;
            for (MapEntity mapEntity : playerTouchingMapEntities) {
                if (isInteractedEntityValid(mapEntity, player)) {
                    float areaOverlapped = mapEntity.getAreaOverlapped(player.getInteractionRange());
                    if (areaOverlapped > currentLargestAreaOverlapped) {
                        currentLargestAreaOverlappedEntity = mapEntity;
                        currentLargestAreaOverlapped = areaOverlapped;
                    }
                }
            }
            interactedEntity = currentLargestAreaOverlappedEntity;
        }
        if (interactedEntity != null) {
            interactedEntity.getInteractScript().setIsActive(true);
            activeInteractScript = interactedEntity.getInteractScript();
        }
    }

    private boolean isInteractedEntityValid(MapEntity interactedEntity, Player player) {
        Rectangle playerBounds = player.getBounds();
        Rectangle entityBounds = interactedEntity.getBounds();
        boolean xDirValid = true;
        boolean yDirValid = true;
        if (player.getLastWalkingXDirection() == Direction.LEFT && playerBounds.getX1() < entityBounds.getX2()) {
            xDirValid = false;
        }

        else if (player.getLastWalkingXDirection() == Direction.RIGHT && playerBounds.getX2() > entityBounds.getX1()) {
            xDirValid = false;
        }

        else if (player.getLastWalkingXDirection() == Direction.NONE) {
            xDirValid = false;
        }

        if (player.getLastWalkingYDirection() == Direction.UP && playerBounds.getY1() < entityBounds.getY2()) {
            yDirValid = false;
        }

        else if (player.getLastWalkingYDirection() == Direction.DOWN && playerBounds.getY2() > entityBounds.getY1()) {
            yDirValid = false;
        }

        else if (player.getLastWalkingYDirection() == Direction.NONE) {
            yDirValid = false;
        }

        if (!xDirValid && !yDirValid) {
            return false;
        }

        if (playerBounds.getY1() >= entityBounds.getY2()) {
            Rectangle playerTopBounds = new Rectangle(playerBounds.getX(), playerBounds.getY() - 1,
                    playerBounds.getWidth(), 1);
            float areaOverlapped = interactedEntity.getAreaOverlapped(playerTopBounds);
            return areaOverlapped >= Math.min(Math.round(playerBounds.getWidth() / 3f), entityBounds.getWidth());
        } else if (playerBounds.getY2() <= entityBounds.getY1()) {
            Rectangle playerBottomBounds = new Rectangle(playerBounds.getX(), playerBounds.getY2() + 1,
                    playerBounds.getWidth(), 1);
            float areaOverlapped = interactedEntity.getAreaOverlapped(playerBottomBounds);
            return areaOverlapped >= Math.min(Math.round(playerBounds.getWidth() / 3f), entityBounds.getWidth());
        } else if (playerBounds.getX1() >= entityBounds.getX2()) {
            Rectangle playerLeftBounds = new Rectangle(playerBounds.getX() - 1, playerBounds.getY(), 1,
                    playerBounds.getHeight());
            float areaOverlapped = interactedEntity.getAreaOverlapped(playerLeftBounds);
            return areaOverlapped >= Math.min(Math.round(playerBounds.getHeight() / 3f), entityBounds.getHeight());
        } else if (playerBounds.getX2() <= entityBounds.getX()) {
            Rectangle playerRightBounds = new Rectangle(playerBounds.getX2() + 1, playerBounds.getY(), 1,
                    playerBounds.getHeight());
            float areaOverlapped = interactedEntity.getAreaOverlapped(playerRightBounds);
            return areaOverlapped >= Math.min(Math.round(playerBounds.getHeight() / 3f), entityBounds.getHeight());
        }

        return false;
    }

    public void update(Player player) {
        if (adjustCamera) {
            adjustMovementY(player);
            adjustMovementX(player);
        }
        camera.update(player);
        if (textbox.isActive()) {
            textbox.update();
        }

        // updates quest info
        questMenu.update();

        //creates a placeholder version for the most recent triggers added
        updatedTriggers = updateTriggers();

        //the some new triggers were actually added, then run
        if (updatedTriggers != null) {
            //runs a for loop going through every trigger in updatedTriggers
            for (Trigger trigger : this.updatedTriggers) {
                //adds it's flag to flag manager
                flagManager.addFlag((trigger.getExistenceFlag()), false);
                //sets it to current map
                trigger.setMap(this);
                //adds it to map arrayList for triggers
                triggers.add(trigger);
            }
        }
    }

    // based on the player's current X position (which in a level can potentially be
    // updated each frame),
    // adjust the player's and camera's positions accordingly in order to properly
    // create the map "scrolling" effect
    private void adjustMovementX(Player player) {
        // if player goes past center screen (on the right side) and there is more map
        // to show on the right side, push player back to center and move camera forward
        if (player.getCalibratedXLocation() > xMidPoint && camera.getEndBoundX() < endBoundX) {
            float xMidPointDifference = xMidPoint - player.getCalibratedXLocation();
            camera.moveX(-xMidPointDifference);

            // if camera moved past the right edge of the map as a result from the move
            // above, move camera back and push player forward
            if (camera.getEndBoundX() > endBoundX) {
                float cameraDifference = camera.getEndBoundX() - endBoundX;
                camera.moveX(-cameraDifference);
            }
        }
        // if player goes past center screen (on the left side) and there is more map to
        // show on the left side, push player back to center and move camera backwards
        else if (player.getCalibratedXLocation() < xMidPoint && camera.getX() > startBoundX) {
            float xMidPointDifference = xMidPoint - player.getCalibratedXLocation();
            camera.moveX(-xMidPointDifference);

            // if camera moved past the left edge of the map as a result from the move
            // above, move camera back and push player backward
            if (camera.getX() < startBoundX) {
                float cameraDifference = startBoundX - camera.getX();
                camera.moveX(cameraDifference);
            }
        }
    }

    // based on the player's current Y position (which in a level can potentially be
    // updated each frame),
    // adjust the player's and camera's positions accordingly in order to properly
    // create the map "scrolling" effect
    private void adjustMovementY(Player player) {
        // if player goes past center screen (below) and there is more map to show
        // below, push player back to center and move camera upward
        if (player.getCalibratedYLocation() > yMidPoint && camera.getEndBoundY() < endBoundY) {
            float yMidPointDifference = yMidPoint - player.getCalibratedYLocation();
            camera.moveY(-yMidPointDifference);

            // if camera moved past the bottom of the map as a result from the move above,
            // move camera upwards and push player downwards
            if (camera.getEndBoundY() > endBoundY) {
                float cameraDifference = camera.getEndBoundY() - endBoundY;
                camera.moveY(-cameraDifference);
            }
        }
        // if player goes past center screen (above) and there is more map to show
        // above, push player back to center and move camera upwards
        else if (player.getCalibratedYLocation() < yMidPoint && camera.getY() > startBoundY) {
            float yMidPointDifference = yMidPoint - player.getCalibratedYLocation();
            camera.moveY(-yMidPointDifference);

            // if camera moved past the top of the map as a result from the move above, move
            // camera downwards and push player upwards
            if (camera.getY() < startBoundY) {
                float cameraDifference = startBoundY - camera.getY();
                camera.moveY(cameraDifference);
            }
        }
    }

    public void reset() {
        setupMap();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        camera.draw(graphicsHandler);
    }

    public void draw(Player player, GraphicsHandler graphicsHandler) {
        camera.draw(player, graphicsHandler);
        if (portrait.isPortraitActive()) {
            portrait.draw(graphicsHandler);
        }
        if (textbox.isActive()) {
            textbox.draw(graphicsHandler);
        }

        questMenu.draw(graphicsHandler);
    }

    public FlagManager getFlagManager() {
        return flagManager;
    }

    public void setFlagManager(FlagManager flagManager) {
        this.flagManager = flagManager;
    }

    public Textbox getTextbox() {
        return textbox;
    }

    // fetches questMenu
    public QuestMenu getQuestMenu() {
        return questMenu;
    }

    // adds quest
    public void addQuest(Quest newQuest) {
        questMenu.addQuest(newQuest);
    }

    // fetches portrait
    public Portrait getPortrait() {return portrait; }

    public int getEndBoundX() {
        return endBoundX;
    }

    public int getEndBoundY() {
        return endBoundY;
    }
}
