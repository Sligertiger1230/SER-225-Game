/**
 * This class is in charge of running the sound system for the game.
 * 
 * Introduced: September 27th (Sprint 1)
 * Last updated: September 27th (Sprint 1)
 * @author bjaxqq
 */
package Utils;

// Imports for sound functions
import java.io.File;
import javax.sound.sampled.*;
import Engine.Config;

public class Sound implements LineListener {

    // Tracks if playback is finished or not
    private boolean isPlaybackComplete;
    // Determines if audio should loop or not
    private boolean isLooping;
    // Imports clip object for playback
    private Clip audioClip;

    /**
     * Constructor for sound class that takes audio file name and isLooping boolean
     * @param audioFileName
     * @param loop
     */
    public Sound(String audioFileName, boolean loop) {
        this.isLooping = loop;
        try {
            // Object for audio file in Sounds folder
            File audioFile = new File(Config.SOUNDS_PATH + audioFileName);
            // Gets AudioInputStream from audio file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            // Gets format from AudioInputStream
            AudioFormat audioFormat = audioStream.getFormat();
            // Specifies we are working with Clip
            DataLine.Info audioInfo = new DataLine.Info(Clip.class, audioFormat);
            // Gets clip instance from AudioSystem based on data info
            audioClip = (Clip) AudioSystem.getLine(audioInfo);
            // Adds class as LineListener to get line events
            audioClip.addLineListener(this);
            // Opens audio stream and associates with Clip
            audioClip.open(audioStream);
        } catch (Exception e) {
            // Print exception to audio setup
            e.printStackTrace();
        }
    }

    /**
     * Calls LineListener interface when line event occurs
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP && !isLooping) {
            // If playback stops marked as complete
            isPlaybackComplete = true;
        }
    }

    /**
     * Plays the audio
     */
    public void play() {
        if (audioClip != null) {
            if (isLooping) {
                // if looping is enabled for audio, repeat it infinitely
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            // Plays the audio
            audioClip.start();
        }
    }

    /**
     * Pauses the audio
     */
    public void pause() {
        if (audioClip != null) {
            // Stops the audio
            audioClip.stop();
        }
    }

    /**
     * Starts the audio from the beginning
     */
    public void back() {
        if (audioClip != null) {
            // Brings the audio position to the very beginning
            audioClip.setMicrosecondPosition(0);
        }
    }

    /**
     * Closes the stream
     */
    public void close() {
        if (audioClip != null) {
            // Stops the audio
            audioClip.stop();
            // Closes the audio
            audioClip.close();
        }
    }

    /**
     * Checks if the audio is finished
     * @return
     */
    public boolean isPlaybackComplete() {
        return isPlaybackComplete;
    }
}
