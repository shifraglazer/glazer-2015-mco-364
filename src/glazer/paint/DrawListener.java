package glazer.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawListener implements MouseListener, MouseMotionListener {

	private int x;
	private int y;
	private Canvas canvas;
	private Color color;
	private String function;

	public DrawListener(Canvas canvas) {
		this.canvas = canvas;
		this.color = Color.BLACK;
		this.function="pencil";
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		Graphics graphics = canvas.getImage().getGraphics();
		graphics.setColor(color);
		if(function.equals("pencil")){
		graphics.drawLine(this.x, this.y, x, y);
		this.x = x;
		this.y = y;
		}
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		
		int x = event.getX();
		int y = event.getY();
		Graphics graphics = canvas.getImage().getGraphics();
		graphics.setColor(color);
		if(function.equals("pencil")){
		graphics.drawLine(x, y, x, y);
		this.x = x;
		this.y = y;
		}
		else if(function.equals("rectangle")){
		this.x=x;
		this.y=y;
		}
		canvas.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent event) {

		int x = event.getX();
		int y = event.getY();
		Graphics graphics = canvas.getImage().getGraphics();
		graphics.setColor(color);
		if(function.equals("rectangle")){
			graphics.drawRect(this.x, this.y, (int)Math.abs(x-this.x), (int)Math.abs(y-this.y));
		}
		canvas.repaint();

	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setFunction(String string) {
		this.function=string;
		
	}

}
