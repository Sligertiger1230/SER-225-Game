/**
 * This class is in charge of all of the audio files for the game.
 * 
 * Introduced: September 27th (Sprint 1)
 * Last updated: September 28th (Sprint 1)
 * @author bjaxqq
 */

package Level;

import Utils.Sound;
import java.util.HashMap;
import java.util.Map;

public class Audio {
    protected static Player player1;
    
    // Creating a hash map to store sound object
    protected static Map<Integer, Sound> sounds = new HashMap<>();

    static {
        // Initializing the sounds map, expandable in the future
        sounds.put(0, new Sound("grass.wav", true));
        sounds.put(1, new Sound("brick.wav", true));
    }

    // Set default sound to grass
    private static Sound walkSound = sounds.get(0);

    // Setting the player object
    public static void setPlayer(Player player) {
        player1 = player;
    }

    // Changing the audio based on what tile index the player is on
    public static void audioChange(int tileIndex) {
        player1.getWalkSound().stop();
        walkSound = sounds.getOrDefault(tileIndex, null);
    }

    // Getting current walk sound
    public static Sound getWalkSound() {
        return walkSound;
    }
}