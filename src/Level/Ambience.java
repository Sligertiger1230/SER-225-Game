package Level;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Ambience {
    private Clip clip;
    private final URL soundURL2[] = new URL[40];

    public Ambience() {
        soundURL2[27] = getClass().getResource("/sounds/outsideAmbience.wav");
        soundURL2[28] = getClass().getResource("/sounds/insideAmbience.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL2[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        // clip.start();
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
