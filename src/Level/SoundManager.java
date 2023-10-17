package Level;

public class SoundManager {
    private Sounds backgroundMusic;

    public SoundManager() {
        backgroundMusic = new Sounds();
    }

    public void playBackgroundMusic(int musicIndex) {
        backgroundMusic.setFile(musicIndex);
        backgroundMusic.loop();
    }

}
