package glazer.paint;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Toolbar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ModeButton rectangle;
	private ModeButton pencil;
	private ModeButton rectangleFill;
	private JPanel colors;
	private Canvas canvas;
	private UndoFunction undo;
	private RotateFunction rotate;
	private JMenu thickness;
	private StrokeFunction thin;
	private StrokeFunction medium;
	private StrokeFunction thick;
	private StrokeFunction normal;

	public Toolbar(Canvas canvas) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.canvas = canvas;
		rectangle = new ModeButton(new RectangleOutLineListener(canvas));
		rectangleFill = new ModeButton(new RectangleFillListener(canvas));
		pencil = new ModeButton(new PencilListener(canvas));
		rectangle.setText("    ");
		rectangleFill.setText("    ");
		pencil.setText("  ");
		colors = new JPanel();
		undo = new UndoFunction(canvas);
		rotate = new RotateFunction(canvas);
		setUpPallet();
		setUpThickness();
		add(undo);
		add(rotate);
		add(rectangle);
		add(rectangleFill);
		add(pencil);
		add(colors);
		
		colors.setLayout(new GridLayout(2, 5));

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ModeButton button = (ModeButton) e.getSource();
				canvas.setListener(button.getListener());
			}

		};							
		pencil.addActionListener(actionListener);
		rectangle.addActionListener(actionListener);
		rectangleFill.addActionListener(actionListener);
	}

	public ModeButton getRectangle(){
		return this.rectangle;
	}
	public void setUpThickness() {
		thickness = new JMenu("Thickness");
		thickness.setBackground(Color.WHITE);
		ImageIcon thinPic = new ImageIcon(getClass().getResource("Thin.png"));
		thinPic=new ImageIcon(thinPic.getImage().getScaledInstance(40, 1,
				Image.SCALE_SMOOTH));
		ImageIcon normalPic = new ImageIcon(getClass()
				.getResource("Normal.png"));
		normalPic=new ImageIcon(normalPic.getImage().getScaledInstance(40,
				3, Image.SCALE_SMOOTH));
		ImageIcon medPic = new ImageIcon(getClass().getResource("Medium.png"));
		medPic=new ImageIcon(medPic.getImage().getScaledInstance(40, 5,
			Image.SCALE_SMOOTH));
		ImageIcon thickPic = new ImageIcon(getClass().getResource("Thick.png"));
		thickPic=new ImageIcon(thickPic.getImage().getScaledInstance(40,
				7, Image.SCALE_SMOOTH));
		thin = new StrokeFunction(canvas,thinPic,1);
		thick = new StrokeFunction(canvas,thickPic,7);
		normal = new StrokeFunction(canvas,normalPic,3);
		medium = new StrokeFunction(canvas,medPic,5);
		thickness.add(thin);
		thickness.add(normal);
		thickness.add(medium);
		thickness.add(thick);
		add(thickness);

	}
	public void setUpPallet() {

		ColorFunction red = new ColorFunction(canvas,Color.RED,new FunctionListener());
		ColorFunction orange = new ColorFunction(canvas,Color.ORANGE,new FunctionListener());
		ColorFunction yellow = new ColorFunction(canvas,Color.YELLOW,new FunctionListener());
		ColorFunction green = new ColorFunction(canvas,Color.GREEN,new FunctionListener());
		ColorFunction blue = new ColorFunction(canvas,Color.BLUE,new FunctionListener());
		ColorFunction black = new ColorFunction(canvas,Color.BLACK,new FunctionListener());
		ColorFunction white = new ColorFunction(canvas,Color.WHITE,new FunctionListener());
		ColorFunction purple = new ColorFunction(canvas,Color.MAGENTA,new FunctionListener());
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
