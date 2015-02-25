package glazer.snake;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class CrunchSound extends Thread {

	@Override
	public void run() {


		URL urlClick = getClass().getResource("appleCrunch.wav");
		AudioClip click = Applet.newAudioClip(urlClick);
		click.play();
	
	}
}
