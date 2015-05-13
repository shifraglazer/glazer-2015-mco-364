package glazer.paint;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class PaintFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;

	public PaintFrame() {
		width = 800;
		height = 600;
		setSize(width, height);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		Canvas canvas = new Canvas(600,600);
		add(canvas, BorderLayout.CENTER);
		DrawListener listener = new DrawListener(canvas);
		canvas.addMouseListener(listener);
		canvas.addMouseMotionListener(listener);

	}

	public static void main(String[] args) {

		PaintFrame frame = new PaintFrame();
		frame.setVisible(true);
	}

}