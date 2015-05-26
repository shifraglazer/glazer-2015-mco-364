package glazer.paint;

import java.awt.Image;

import javax.swing.ImageIcon;

public class RotateFunction extends Function{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RotateFunction(Canvas canvas) {
		super(canvas);
		addActionListener(new FunctionListener());
		ImageIcon rotate = new ImageIcon(getClass()
				.getResource("rotate.png"));
		rotate=new ImageIcon(rotate.getImage().getScaledInstance(30, 15,
				Image.SCALE_SMOOTH));
		setIcon(rotate);
		
	}

	@Override
	public void perform() {
			canvas.rotateCanvas();
		
	}

}
