package airplane;

import java.io.IOException;

public class StartApp {

	public static void main(String[] args) {
		try {
			World world = new World();
			new GameLoopThread(world).start();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}