/**
 * This class is in charge of all of the audio files for the game.
 * 
 * Introduced: September 27th (Sprint 1)
 * Last updated: October 1st (Sprint 2)
 * @author bjaxqq
 */

package Level;

import Utils.Sound;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class Audio {
    protected static Player player1;
    protected static Map<Integer, Sound> sounds = new HashMap<>();
    private static Sound walkSound = null;
    private static Random random = new Random();

    static {
        for (int i = 0; i <= 17; i++) {
            sounds.put(0, new Sound("grass1.wav", true));
            sounds.put(0, new Sound("grass2.wav", true));
            sounds.put(0, new Sound("grass3.wav", true));
            sounds.put(0, new Sound("grass4.wav", true));
            sounds.put(0, new Sound("grass5.wav", true));
            sounds.put(0, new Sound("grass6.wav", true));

            sounds.put(0, new Sound("bricks1.wav", true));
            sounds.put(0, new Sound("bricks2.wav", true));
            sounds.put(0, new Sound("bricks3.wav", true));
            sounds.put(0, new Sound("bricks4.wav", true));
            sounds.put(0, new Sound("bricks5.wav", true));
            sounds.put(0, new Sound("bricks6.wav", true));

            sounds.put(0, new Sound("carpet1.wav", true));
            sounds.put(0, new Sound("carpet2.wav", true));
            sounds.put(0, new Sound("carpet3.wav", true));
            sounds.put(0, new Sound("carpet4.wav", true));

            sounds.put(0, new Sound("door1.wav", true));
            sounds.put(0, new Sound("door2.wav", true));

            sounds.put(0, new Sound("overworld.wav", true));
        }
    }

    public static void setPlayer(Player player) {
        player1 = player;
    }

    public static Sound getWalkSound() {
        return walkSound;
    }

    public static void playFootstepSound (AudioState audioState) {
        int minIndex;
        int maxIndex;

        switch (audioState) {
            case GRASS:
                minIndex = 0;
                maxIndex = 5;
                break;
            case BRICK:
                minIndex = 6;
                maxIndex = 11;
                break;
            case CARPET:
                minIndex = 12;
                maxIndex = 15;
                break;
            case DOOR:
                minIndex = 16;
                maxIndex = 17;
                break;
            default:
                minIndex = 0;
                maxIndex = 5;
        }

        int randomIndex = minIndex + random.nextInt(maxIndex - minIndex + 1);

        if (walkSound != null) {
            walkSound.stop();
        }

        walkSound = sounds.get(randomIndex);
        walkSound.play();
    }

    public static void playBackgroundMusic() {
        Sound backgroundMusic = sounds.get(18);
        if (backgroundMusic != null) {
            backgroundMusic.play();
        }
    }
}