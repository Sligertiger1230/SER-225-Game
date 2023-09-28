/**
 * This class is in charge of all of the audio files for the game.
 * 
 * Introduced: September 27th (Sprint 1)
 * Last updated: September 27th (Sprint 1)
 * @author bjaxqq
 */

package Level;

import Utils.Sound;

public class Audio {
    
    // Reference to player object
    protected static Player player1;

    // References to sound files
    protected static Sound grass = new Sound("grass.wav", true);
    protected static Sound brick = new Sound("brick.wav", true);

    /**
     * Sets player object for handling sounds
     * @param player
     */
    public static void setPlayer(Player player) {
        player1 = player;
    }

    /**
     * Sets walk sound to grass footsteps and associates with player object
     * Note: This needs to be updated in the future with terrain detection (September 27th)
     * @param sound
     */
    public static void setWalkSound(Sound sound) {
        grass = sound;
        if(player1 != null) {
            player1.setWalkSound(grass);
        }
    }

    /**
     * Gets walk sound for grass footsteps
     * @return
     */
    public static Sound getWalkSound() {
        return grass;
    }
}