package glazer.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Toolbar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton rectangle;
	private JButton pencil;
	private JPanel colors;
	private DrawListener listener;
	private JButton red;
	private JButton orange;
	private JButton yellow;
	private JButton green;
	private JButton blue;
	private JButton black;
	private JButton white;
	private JButton purple;
	private JButton undo;
	private JButton rotate;
	private JMenu thickness;
	private JMenuItem thin;
	private JMenuItem medium;
	private JMenuItem thick;
	private JMenuItem normal;

	public Toolbar(DrawListener listener) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.listener = listener;
		rectangle = new JButton("Draw Rect");
		pencil = new JButton();
		ImageIcon pencilPic = new ImageIcon(getClass()
				.getResource("pencil.png"));
		pencil.setOpaque(false);
		pencil.setBackground(Color.WHITE);
		rectangle.setOpaque(false);
		rectangle.setBackground(Color.WHITE);
		pencil.setIcon(new ImageIcon(pencilPic.getImage().getScaledInstance(30,
				20, Image.SCALE_SMOOTH)));
		colors = new JPanel();
		undo = new JButton();
		undo.setOpaque(false);
		ImageIcon undoPic=new ImageIcon(getClass().getResource("undo.png"));
		undo.setIcon(new ImageIcon(undoPic.getImage().getScaledInstance(30, 20,Image.SCALE_SMOOTH)));
		undo.setBackground(Color.WHITE);
		rotate = new JButton("Rotate 90");
		rotate.setOpaque(false);
		rotate.setBackground(Color.WHITE);
		setUpPallet();
		setUpThickness();
		add(undo);
		add(rotate);
		add(rectangle);
		add(pencil);
		add(colors);
		colors.setLayout(new GridLayout(2, 5));

		ActionListener rect = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.setFunction("rectangle");
			}

		};
		ActionListener rotateListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.rotateCanvas();
			}

		};
		ActionListener undoListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.undoAction();
				;
			}

		};
		ActionListener pencilListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.setFunction("pencil");
			}

		};
		rotate.addActionListener(rotateListener);
		undo.addActionListener(undoListener);
		pencil.addActionListener(pencilListener);
		rectangle.addActionListener(rect);
	}

	public void setUpThickness() {
		thickness = new JMenu("Thickness");
		thickness.setBackground(Color.WHITE);
		thin = new JMenuItem(new Thickness(1));
		ImageIcon thinPic = new ImageIcon(getClass().getResource("Thin.png"));
		thin.setIcon(new ImageIcon(thinPic.getImage().getScaledInstance(40, 1,
				Image.SCALE_SMOOTH)));
		thick = new JMenuItem(new Thickness(7));
		normal = new JMenuItem(new Thickness(3));
		ImageIcon normalPic = new ImageIcon(getClass()
				.getResource("Normal.png"));
		normal.setIcon(new ImageIcon(normalPic.getImage().getScaledInstance(40,
				3, Image.SCALE_SMOOTH)));
		ImageIcon thickPic = new ImageIcon(getClass().getResource("Thick.png"));
		thick.setIcon(new ImageIcon(thickPic.getImage().getScaledInstance(40,
				7, Image.SCALE_SMOOTH)));
		medium = new JMenuItem(new Thickness(5));
		ImageIcon medPic = new ImageIcon(getClass().getResource("Medium.png"));
		medium.setIcon(new ImageIcon(medPic.getImage().getScaledInstance(40, 5,
				Image.SCALE_SMOOTH)));

		thick.setPreferredSize(new Dimension(30, 20));
		thin.setPreferredSize(new Dimension(30, 20));
		normal.setPreferredSize(new Dimension(30, 20));
		thick.setPreferredSize(new Dimension(30, 20));
		thin.setPreferredSize(new Dimension(30, 20));
		thickness.add(thin);
		thickness.add(normal);
		thickness.add(medium);
		thickness.add(thick);
		add(thickness);

	}

	private class Thickness extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private int thick;

		public Thickness(int thick) {
			this.thick = thick;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			listener.setThickness(thick);
		}
	};

	private class ColorListener extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			listener.setColor(button.getBackground());
		}
	};

	public void setUpPallet() {

		red = new JButton(new ColorListener());
		red.setBackground(Color.RED);
		orange = new JButton(new ColorListener());
		orange.setBackground(Color.ORANGE);
		yellow = new JButton(new ColorListener());
		yellow.setBackground(Color.YELLOW);
		green = new JButton(new ColorListener());
		green.setBackground(Color.GREEN);
		blue = new JButton(new ColorListener());
		blue.setBackground(Color.BLUE);
		black = new JButton(new ColorListener());
		black.setBackground(Color.BLACK);
		white = new JButton(new ColorListener());
		white.setBackground(Color.WHITE);
		purple = new JButton(new ColorListener());
		purple.setBackground(Color.MAGENTA);
		colors.add(red);
		colors.add(yellow);
		colors.add(blue);
		colors.add(white);
		colors.add(orange);
		colors.add(green);
		colors.add(purple);
		colors.add(black);
	}
}
