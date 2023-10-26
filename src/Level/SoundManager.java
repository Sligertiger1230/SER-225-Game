package Level;

public class SoundManager {
    private Sound backgroundMusic;

    public SoundManager() {
        backgroundMusic = new Sound();
    }

    public void playBackgroundMusic(int musicIndex) {
        backgroundMusic.setFile(musicIndex);
        backgroundMusic.loop();
    }

}
