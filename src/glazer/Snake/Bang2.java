package glazer.Snake;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Bang2 extends Thread {

	@Override
	public void run() {

		// The wrapper thread is unnecessary, unless it blocks on the
		// Clip finishing; see comments.

		URL urlClick = getClass().getResource("bang2.wav");
		AudioClip click = Applet.newAudioClip(urlClick);
		click.play();

	}
}
