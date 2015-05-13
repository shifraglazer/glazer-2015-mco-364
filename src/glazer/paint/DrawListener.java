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

	public DrawListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		Graphics graphics=canvas.getImage().getGraphics();
		graphics.setColor(Color.BLACK);
		graphics.drawLine(this.x, this.y, x, y);
		this.x = x;
		this.y = y;
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
		Graphics graphics=canvas.getImage().getGraphics();
		graphics.setColor(Color.BLACK);
		graphics.drawLine(x, y, x, y);
		this.x = x;
		this.y = y;
		canvas.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
