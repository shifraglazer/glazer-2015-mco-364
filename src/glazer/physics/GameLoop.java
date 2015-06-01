package glazer.physics;

public class GameLoop extends Thread {

	private World world;

	public GameLoop(World world) {
		this.world = world;
	}

	@Override
	public void run() {
		while (true) {
			world.updateFrame();
			world.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
