package glazer.paint;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class BrushListener implements MouseListener,MouseMotionListener{

	private Canvas canvas;
	protected int startX;
	protected int startY;	
	protected int lastX;
	protected int lastY;
	
	
	public BrushListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		// TODO Auto-generated method stub
		lastX=event.getX();
		lastY=event.getY();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		startX=event.getX();
		startY=event.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		draw(canvas.getImage().getGraphics());		
	}
	 abstract public void draw(Graphics g);
	

	
}
