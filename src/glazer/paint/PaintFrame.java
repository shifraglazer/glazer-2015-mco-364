package glazer.paint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class PaintFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	private Toolbar toolbar;
	private JSplitPane pane;

	public PaintFrame() {
		width = 800;
		height = 600;
		setSize(width, height);
		pane = new JSplitPane();
		Canvas canvas = new Canvas(600, 600);
		canvas.setPreferredSize(new Dimension(600, 600));
		pane.setLeftComponent(canvas);
		pane.setRightComponent(new JPanel());
		DrawListener listener = new DrawListener(canvas);
		toolbar = new Toolbar(listener);
		setJMenuBar(toolbar);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(pane, BorderLayout.CENTER);
		canvas.addMouseListener(listener);
		canvas.addMouseMotionListener(listener);

		ComponentListener change = new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
		
				listener.resizeCanvas();
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

		};
		canvas.addComponentListener(change);
	}

	public static void main(String[] args) {

		PaintFrame frame = new PaintFrame();
		frame.setVisible(true);
	}

}