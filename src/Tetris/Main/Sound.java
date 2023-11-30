package Tetris.Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

public class Sound {
	Clip musicClip;
	URL url[] = new URL[10];

	public Sound() {
		url[0] = getClass().getResource("/tetris.wav");
		url[1] = getClass().getResource("/rotate.wav");
		url[2] = getClass().getResource("/combodouble.wav");
		url[3] = getClass().getResource("/harddrop.wav");
		url[4] = getClass().getResource("/landing.wav");
		url[5] = getClass().getResource("/combosingle.wav");
		url[6] = getClass().getResource("/softdrop.wav");
		url[7] = getClass().getResource("/combotetris.wav");
		url[8] = getClass().getResource("/combotriple.wav");
	}

	public void play(int i, boolean music) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url[i]);
			Clip clip = AudioSystem.getClip();

			if (music) {
				musicClip = clip;
			}

			clip.open(audioInputStream);
			clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if (event.getType() == Type.STOP) {
						clip.close();
					}
				}
			});
			audioInputStream.close();
			clip.start();

		} catch (Exception e) {

		}
	}

	public void loop() {
		musicClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		musicClip.stop();
		musicClip.close();
	}
}
