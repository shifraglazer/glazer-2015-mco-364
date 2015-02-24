package glazer.Snake;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

public class PlantGrass {

	private ArrayList<Grass> grassList;
	private Image grass;
	public PlantGrass(int width, int height) throws IOException {
		grassList = new ArrayList<Grass>();
		grass = ImageIO.read(getClass().getResource("grass.jpg"));
		int counter = 0;
		Random random = new Random();
		int x;
		int y;

		while (counter < 20) {
			x = random.nextInt(width - 12);
			y = random.nextInt(height - 12);
			counter++;
			Grass n = new Grass(x, y);
			grassList.add(n);

		}
	}

	public void drawGrass(Graphics g) throws IOException {
		Iterator<Grass> iter = grassList.iterator();
		while (iter.hasNext()) {
			iter.next().drawGrass(g,grass);
		}
	}
}
