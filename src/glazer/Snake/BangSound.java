package glazer.Snake;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;


public class BangSound extends Thread{

	@Override
	public void run() {


		URL urlClick = getClass().getResource("bang.wav");
		AudioClip click = Applet.newAudioClip(urlClick);
		click.play();

	}
}
