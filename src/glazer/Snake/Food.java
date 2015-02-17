package glazer.Snake;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Food {

	
	public Image getFood() {
		return food;
	}
	private Image food;
	private int x;
	private int y;
	private final int SIZE;
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
	public Food( int size) throws IOException{
		food=ImageIO.read(getClass().getResource("apple2.jpg"));
		SIZE=size;
		
	}

	public void drawFood(Graphics g){
		
		g.drawImage(food, x, y, SIZE, SIZE, null);
		//g.drawRect(x, y,SIZE, SIZE);
		
	}
	
}
