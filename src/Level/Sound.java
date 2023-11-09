package Level;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip clip;
    private final URL soundURL[] = new URL[40];

    public Sound() {
        // Music
        soundURL[0] = getClass().getResource("/sounds/overworld.wav");
        soundURL[17] = getClass().getResource("/sounds/drawing.wav");
        soundURL[18] = getClass().getResource("/sounds/cce.wav");
        soundURL[19] = getClass().getResource("/sounds/icerink.wav");
        soundURL[20] = getClass().getResource("/sounds/intro.wav");

        // Grass
        soundURL[1] = getClass().getResource("/sounds/grass1.wav");
        soundURL[2] = getClass().getResource("/sounds/grass2.wav");
        soundURL[3] = getClass().getResource("/sounds/grass3.wav");
        soundURL[4] = getClass().getResource("/sounds/grass4.wav");
        soundURL[5] = getClass().getResource("/sounds/grass5.wav");
        soundURL[6] = getClass().getResource("/sounds/grass6.wav");

        // Brick
        soundURL[7] = getClass().getResource("/sounds/bricks1.wav");
        soundURL[8] = getClass().getResource("/sounds/bricks2.wav");
        soundURL[9] = getClass().getResource("/sounds/bricks3.wav");
        soundURL[10] = getClass().getResource("/sounds/bricks4.wav");
        soundURL[11] = getClass().getResource("/sounds/bricks5.wav");
        soundURL[12] = getClass().getResource("/sounds/bricks6.wav");

        // Carpet
        soundURL[13] = getClass().getResource("/sounds/carpet1.wav");
        soundURL[14] = getClass().getResource("/sounds/carpet2.wav");
        soundURL[15] = getClass().getResource("/sounds/carpet3.wav");
        soundURL[16] = getClass().getResource("/sounds/carpet4.wav");

        // Cursor (for menu and options)
        soundURL[21] = getClass().getResource("/sounds/cursor.wav");

        // NPC Sounds
        soundURL[22] = getClass().getResource("/sounds/interact.wav");
        soundURL[23] = getClass().getResource("/sounds/teleport.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
