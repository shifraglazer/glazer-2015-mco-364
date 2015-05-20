package glazer.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class Canvas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BrushListener listener;


	private BufferedImage image;

	public Canvas(int width, int height) {
		listener=new PencilListener();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		listener.draw(g);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public BrushListener getListener() {
		return listener;
	}

	public void setListener(BrushListener listener) {
		this.listener = listener;
	}
}
