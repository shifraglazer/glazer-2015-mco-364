package glazer.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public abstract class BrushListener implements MouseListener,MouseMotionListener{

	protected Canvas canvas;
	protected int startX;
	protected int startY;	
	protected int lastX;
	protected int lastY;
	
	
	public BrushListener(Canvas canvas) {
		this.canvas = canvas;
		BufferedImage image=canvas.getImage();
		//Graphics graphics=canvas.getImage().getGraphics();
		//graphics.setColor(Color.WHITE);
		//graphics.fillRect(0, 0,600 ,600);
		//graphics.setColor(Color.BLACK);
	}
	
	public Graphics2D setUpGraphics(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(canvas.getColor());
		g2.setStroke(new BasicStroke(canvas.getStroke()));
		return g2;
	}

	@Override
	public void mouseDragged(MouseEvent event) {
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
	public void mouseReleased(MouseEvent event) {
		lastX=event.getX();
		lastY=event.getY();
		draw(canvas.getImage().getGraphics());	
		canvas.commitHistory(canvas.getImage());
		canvas.repaint();
	}
	 abstract public void draw(Graphics g);
	

	
}
