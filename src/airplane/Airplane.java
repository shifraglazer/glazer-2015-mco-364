package airplane;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Airplane {

	
	private Image plane;
	private int x;
	private int y;
	private final int SIZE;
	
	public Airplane(int size) throws IOException {
		plane = ImageIO.read(getClass().getResource("airplane.jpg"));
		SIZE = size;

	}
	public Image getAirplane() {
		return plane;
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



	public void drawAirplane(Graphics g) {
		g.drawImage(plane, x, y, SIZE, SIZE, null);
		// g.drawRect(x, y,SIZE, SIZE);

	}

}
