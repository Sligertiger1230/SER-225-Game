/**
 * This class is in charge of all of the audio files for the game.
 * 
 * Introduced: September 27th (Sprint 1)
 * Last updated: October 1st (Sprint 2)
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
        // Grass footsteps
        sounds.put(0, new Sound("grass1.wav", true));
        sounds.put(1, new Sound("grass2.wav", true));
        sounds.put(2, new Sound("grass3.wav", true));
        sounds.put(3, new Sound("grass4.wav", true));
        sounds.put(4, new Sound("grass5.wav", true));
        sounds.put(5, new Sound("grass6.wav", true));

        // Brick footsteps
        sounds.put(6, new Sound("brick1.wav", true));
        sounds.put(7, new Sound("brick2.wav", true));
        sounds.put(8, new Sound("brick3.wav", true));
        sounds.put(9, new Sound("brick4.wav", true));
        sounds.put(10, new Sound("brick5.wav", true));
        sounds.put(11, new Sound("brick6.wav", true));

        // Door opening sounds
        sounds.put(12, new Sound("door1.wav", true));
        sounds.put(13, new Sound("door2.wav", true));

        sounds.put(14, new Sound("animalCrossing.wav", true));
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

    // Function for playing background music (september 29th)
    // Will change this based on what map/area the player is in later once we have
    // that set up
    public static void playBackgroundMusic() {
        Sound backgroundMusic = sounds.get(14);
        if (backgroundMusic != null) {
            backgroundMusic.play();
        }
    }
}