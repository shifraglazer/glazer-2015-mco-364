package glazer.Snake;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Music extends Thread{

	@Override
	public void run() {

		URL urlClick = getClass().getResource("music.wav");
		AudioClip click = Applet.newAudioClip(urlClick);
		click.play();
	}
	public static void main(String args[]){
		Music music=new Music();
		music.start();
	}
}
