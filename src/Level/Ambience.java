package Level;

import javax.sound.sampled.*;
import java.net.URL;

public class Ambience {
    private Clip clip;
    private final URL[] soundURL2 = new URL[40];
    private FloatControl floatControl;

    private int volumeScale = 3;
    private float[] volumeValues = { -80f, -20f, -12f, -5f, 1f, 6f };

    public Ambience() {
        soundURL2[27] = getClass().getResource("/sounds/outsideAmbience.wav");
        soundURL2[28] = getClass().getResource("/sounds/insideAmbience.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL2[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
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

    public void checkVolume() {
        float volume = volumeValues[volumeScale];
        floatControl.setValue(volume);
    }

    public int getVolumeScale() {
        return volumeScale;
    }

    public void setVolumeScale(int volumeScale) {
        this.volumeScale = volumeScale;
        checkVolume();
    }
}
