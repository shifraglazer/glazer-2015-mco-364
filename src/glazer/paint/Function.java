package glazer.paint;

import java.awt.Color;

import javax.swing.JButton;

public abstract class  Function extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Canvas canvas;
	public Function (Canvas canvas){
		this.canvas=canvas;
		setOpaque(false);
		setBackground(Color.WHITE);
	}
	public abstract void perform();
}
