package glazer.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BrushListener listener;
	private Color color;
	private int stroke;
	private BufferedImage[] history;
	private int historyPointer;
	private int historyLeft;
	private BufferedImage image;
	
	public Canvas(int width, int height) {
		listener=new PencilListener(this);
		color=Color.BLACK;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);
		this.history = new BufferedImage[8];
		for (int i = 0; i < history.length; i++) {
			history[i] = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
			Graphics graphics2 = history[i].getGraphics();
			graphics2.setColor(Color.WHITE);
			graphics2.fillRect(0, 0, 600, 600);
		}
		historyPointer = 0;
		historyLeft = 1;
		addMouseMotionListener(listener);
		addMouseListener(listener);

	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		listener.draw(g);
	}

	public void rotateCanvas() {
		BufferedImage imageNew = image;
		int degrees = 90;
		AffineTransform transform = AffineTransform.getRotateInstance(
				Math.toRadians(degrees), image.getWidth() / 2,
				image.getHeight() / 2);
		AffineTransformOp atransform = new AffineTransformOp(transform,
				AffineTransformOp.TYPE_BILINEAR);
		Graphics graphics = image.getGraphics();
		graphics.drawImage(atransform.filter(imageNew, null), 0, 0, getWidth(),
				getHeight(), null);
		repaint();
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
		removeMouseListener(this.listener);
		removeMouseMotionListener(this.listener);
		this.listener = listener;
		addMouseMotionListener(listener);
		addMouseListener(listener);
	
	}

	public void commitHistory(BufferedImage image) {
		// pointer is next one to undo
		// +1 is where commit next image
		// pointer is <=6
		if (historyPointer < history.length) {
			history[historyPointer].getGraphics().drawImage(image, 0, 0, null);
			if (historyPointer == history.length - 1) {
				historyPointer = 0;
			} else {
				historyPointer++;
			}
		}
		if (historyLeft < 6) {
			historyLeft++;
		}
		System.out.println("commited. pointer= " + historyPointer);

	}

	public void undoAction() {
		Graphics graphics = image.getGraphics();

		if (historyLeft > 0) {
			// 8 to 1
			if (historyPointer == 0) {
				graphics.drawImage(history[history.length - 2], 0, 0, null);
				historyPointer = history.length - 2;

				// is 0
			} else if (historyPointer == 1) {
				graphics.drawImage(history[history.length - 1], 0, 0, null);
				historyPointer = history.length - 1;

			} else {
				graphics.drawImage(history[historyPointer - 2], 0, 0, null);
				historyPointer--;
			}
			historyLeft--;

		}

		System.out.println("undid. pointer= " + historyPointer);

		repaint();

	}


}
