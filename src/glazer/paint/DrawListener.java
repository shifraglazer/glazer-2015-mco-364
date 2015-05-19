package glazer.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class DrawListener implements MouseListener, MouseMotionListener {

	private int x;
	private int y;
	private Canvas canvas;
	private Color color;
	private String function;
	private BufferedImage temp;
	private BufferedImage[] history;
	private int historyPointer;
	private int historyLeft;
	private int thick;

	public DrawListener(Canvas canvas) {
		this.thick=4;
		this.canvas = canvas;
		this.history = new BufferedImage[8];
		for(int i=0;i<history.length;i++){
			history[i]= new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
			Graphics graphics=history[i].getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, 600, 600);
		}
			historyLeft=0;
		this.color = Color.BLACK;
		this.function = "pencil";
		this.temp = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		Graphics graphics = canvas.getImage().getGraphics();
		Graphics2D g2=(Graphics2D)graphics;
		g2.setColor(color);
		g2.setStroke(new BasicStroke(thick));
		if (function.equals("pencil")) {
			g2.drawLine(this.x, this.y, x, y);
			this.x = x;
			this.y = y;
		} else if (function.equals("rectangle")) {
			int width = (int) Math.abs(this.x - x);
			int height = (int) Math.abs(this.y - y);
			g2.drawImage(temp,0, 0, null);
			g2.drawRect(this.x, this.y, width, height);
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
		Graphics2D g2=(Graphics2D)graphics;
		g2.setStroke(new BasicStroke(thick));
		g2.setColor(color);
		if (function.equals("pencil")) {
			g2.drawLine(x, y, x, y);
			this.x = x;
			this.y = y;
		} else if (function.equals("rectangle")) {
			this.x = x;
			this.y = y;
			temp=new BufferedImage(canvas.getWidth(),canvas.getHeight(),BufferedImage.TYPE_INT_RGB);
			Graphics tempG=temp.getGraphics();
			tempG.drawImage(canvas.getImage(), 0, 0, null);
		}
		canvas.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent event) {

		int x = event.getX();
		int y = event.getY();
		Graphics graphics = canvas.getImage().getGraphics();
		Graphics2D g2=(Graphics2D)graphics;
		g2.setStroke(new BasicStroke(thick));
		g2.setColor(color);
		if (function.equals("rectangle")) {
			g2.drawImage(temp, 0, 0, null);
			g2.drawRect(this.x, this.y, (int) Math.abs(x - this.x),
					(int) Math.abs(y - this.y));
		}
		commitHistory(canvas.getImage());
		canvas.repaint();
		
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setFunction(String string) {
		this.function = string;

	}

	public void commitHistory(BufferedImage image) {

		if (historyPointer < history.length-1) {
			history[++historyPointer].getGraphics().drawImage(image,0,0,null);
		} else {
			historyPointer = 0;
			history[historyPointer].getGraphics().drawImage(image,0,0,null);
		}
		if(historyLeft<8){
			historyLeft++;
		}
		System.out.println("commited. pointer= " + historyPointer);
		
	}

	public void undoAction() {
		Graphics graphics = canvas.getImage().getGraphics();

	if(historyLeft>0){
		if (historyPointer >0) {
			graphics.drawImage( history[historyPointer-1], 0,
					0, null);
			historyPointer--;

		} else {
			graphics.drawImage( history[history.length - 1], 0,
					0, null);
			historyPointer = history.length - 1;
		}
		historyLeft--;

	}
		
		System.out.println("undid. pointer= " + historyPointer);
	
		canvas.repaint();

	}

	public void rotateCanvas() {
		BufferedImage image = canvas.getImage();
		int degrees=90;
		AffineTransform transform = AffineTransform.getRotateInstance(
				Math.toRadians(degrees), image.getWidth() / 2,
				image.getHeight() / 2);
		AffineTransformOp atransform = new AffineTransformOp(transform,
				AffineTransformOp.TYPE_BILINEAR);
		Graphics graphics = canvas.getImage().getGraphics();
		graphics.drawImage(atransform.filter(image, null), 0, 0,
				canvas.getWidth(),canvas.getHeight(), null);
		canvas.repaint();
	}

	public void setThickness(int thick) {
	this.thick=thick;
		
	}

	public void resizeCanvas() {
		
		BufferedImage image=new BufferedImage(canvas.getWidth(),canvas.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics tempGraphics=image.getGraphics();
		tempGraphics.setColor(Color.WHITE);
		tempGraphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		tempGraphics.drawImage(canvas.getImage(), 0,0, null);
		canvas.setImage(image);
		canvas.repaint();
	}

}
