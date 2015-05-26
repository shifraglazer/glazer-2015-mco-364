package glazer.paint;

import java.awt.Image;

import javax.swing.ImageIcon;

public class UndoFunction extends Function{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UndoFunction(Canvas canvas) {
		super(canvas);
		addActionListener(new FunctionListener());
		ImageIcon undoPic = new ImageIcon(getClass().getResource("undo.png"));
		setIcon(new ImageIcon(undoPic.getImage().getScaledInstance(30, 15,
				Image.SCALE_SMOOTH)));
	}


	@Override
	public void perform() {
	canvas.undoAction();
	}

}
