package glazer.Snake;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

public class Grass {
	
	private int x;
	private int y;
	private final int SIZE;

	public Grass(int x, int y) throws IOException {
		
		this.x = x;
		this.y = y;
		SIZE = 12;

	}


	public void drawGrass(Graphics g,Image grass) throws IOException {

		g.drawImage(grass, x, y, SIZE, SIZE, null);

	}

}
