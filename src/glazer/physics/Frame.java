package glazer.physics;

import javax.swing.JFrame;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private World world;

	public Frame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		world = new World();
		setSize(600, 600);
		add(world);
		GameLoop t = new GameLoop(world);
		t.start();

	}

	public static void main(String args[]) {
		Frame frame = new Frame();
		frame.setVisible(true);

	}
}
