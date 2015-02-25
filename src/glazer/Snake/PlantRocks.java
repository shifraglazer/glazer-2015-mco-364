package glazer.snake;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class PlantRocks {
	private ArrayList<Rock> rocks;
	private int size;
	private Image rockImg;

	public PlantRocks(int size, int width, int height) throws IOException {
		rocks = new ArrayList<Rock>();
		this.size = size;
		rockImg = ImageIO.read(getClass().getResource("rock.jpg"));
	}

	public void createRocks(int x, int y) throws IOException {
		Rock rock = new Rock(size, x, y);
		rocks.add(rock);
	}

	public void drawRocks(Graphics g) throws IOException {
		Iterator<Rock> iter = rocks.iterator();
		while (iter.hasNext()) {
			iter.next().drawRock(g, rockImg);
		}
	}

	// check if rock is in location
	public boolean occupies(int x, int y) {
		Iterator<Rock> iter = rocks.iterator();
		while (iter.hasNext()) {
			Rock rock = iter.next();
			if (rock.getX() == x && rock.getY() == y) {
				return true;
			}
		}
		return false;
	}
}
