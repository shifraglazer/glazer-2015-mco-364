package glazer.project;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

public class Airplane extends JComponent {

	private static final long serialVersionUID = 1L;
	// private Image image;
	private int width;
	private int height;
	private boolean flying;

	public Airplane() {
		this.width = 400;
		this.height = 400;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(width, height, 10, 10);

	}

	public void goNorth() {
		height--;
	}

	public void goSouth() {
		height++;
	}

	public void goEast() {
		width++;	
	}

	public void goWest() {
		width--;
	}
}
