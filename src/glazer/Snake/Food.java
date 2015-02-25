package glazer.snake;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Food {

	private Image food;
	private int x;
	private int y;
	private final int SIZE;

	public Food(int size) throws IOException {
		food = ImageIO.read(getClass().getResource("apple2.png"));
		SIZE = size;

	}

	public Image getFood() {
		return food;
	}

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

	public void drawFood(Graphics g) {

		g.drawImage(food, x, y, SIZE, SIZE, null);

	}

}
