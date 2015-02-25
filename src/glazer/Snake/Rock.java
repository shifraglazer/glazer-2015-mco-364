package glazer.snake;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

public class Rock {
	//private Image rock;
	private int x;
	private int y;
	private final int SIZE;

	public Rock(int size, int x, int y) throws IOException {
		// rock = ImageIO.read(getClass().getResource("rock.jpg"));
		SIZE = size;
		this.x = x;
		this.y = y;

	}

	/*
	 * public Image getRock() { return rock; }
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void drawRock(Graphics g,Image rock) {

		g.drawImage(rock, x, y, SIZE, SIZE, null);

	}

}
