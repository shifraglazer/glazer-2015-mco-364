package glazer.snake;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Music extends Thread {

	private AudioClip click;

	@Override
	public void run() {

		URL urlClick = getClass().getResource("music.wav");
		click = Applet.newAudioClip(urlClick);

		click.loop();

	}

	public boolean running() {
		return this.isAlive();
	}

	

	public void stopMusic() {
		click.stop();
	}

	public static void main(String args[]) {
		Music music = new Music();
		music.start();
	}
}
