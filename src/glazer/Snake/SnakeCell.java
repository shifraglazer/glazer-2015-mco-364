package glazer.Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class SnakeCell {



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

	private int x;
	private int y;
	private int width;
	private int height;
	private int arcSize;
	private boolean eating;
	
	public int getArcSize() {
		return arcSize;
	}

	public void setArcSize(int arcSize) {
		this.arcSize = arcSize;
	}

	public SnakeCell(int x, int y, int width, int height,int arcSize) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.arcSize=arcSize;
		this.eating=false;
	}

	public boolean isEating() {
		return eating;
	}

	public void setEating(boolean eating) {
		this.eating = eating;
	}

	public void createCell(Graphics g){
		
		g.setColor(Color.GREEN);
		g.fillRoundRect(x, y, width, height,arcSize,arcSize);
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, width, height,arcSize,arcSize);
		
	}

	public void drawHeadUpDown(Graphics g) throws IOException {
		g.setColor(Color.GREEN);
		g.fillRoundRect(x, y, width, height,arcSize,arcSize);
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, width, height,arcSize,arcSize);
		g.setColor(Color.BLUE);
		g.fillOval(((int)(width/3)+x), ((int)(height/4)+y),(int)(width/5),(int)(height/5));
		g.fillOval(((int)(width/3)+x), ((int)(height/4*2)+y),(int)(width/5),(int)(height/5));	
		g.setColor(Color.RED);
		if(!eating){
		g.drawArc(((int)(width/3*2)+x), ((int)(height/4)+y),(int)(width/3),(int)(height/2),90,-180);
		}
		else{
			g.drawOval((int)(width/3*2)+x, ((int)(height/4)+y),(int)(width/3),(int)(height/2));
			g.drawImage(new Food(10).getFood(),x+7,y+7,12,12,null);
		}
	}

	public void drawHeadSide(Graphics g) throws IOException {
		g.setColor(Color.GREEN);
		g.fillRoundRect(x, y, width, height,arcSize,arcSize);
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, width, height,arcSize,arcSize);
		g.setColor(Color.BLUE);
		g.fillOval(((int)(width/3)+x), ((int)(height/4)+y),(int)(width/5),(int)(height/5));
		g.fillOval(((int)(width/3*2)+x), ((int)(height/4)+y),(int)(width/5),(int)(height/5));	
		g.setColor(Color.RED);
		if(!eating){
		g.drawArc(((int)(height/3)+x), ((int)(width/4*2)+y),(int)(width/2),(int)(height/3),0,-180);
		}
		else{
			g.drawOval(((int)(height/3)+x), ((int)(width/4*2)+y),(int)(width/2),(int)(height/3));	
			g.drawImage(new Food(10).getFood(),x+7,y+7,12,12,null);
		}
	}



	
}
