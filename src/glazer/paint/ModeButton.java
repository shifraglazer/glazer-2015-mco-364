package glazer.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

public class ModeButton extends JButton{

	/**
	 * 
	 */
	private BrushListener listener;
	private static final long serialVersionUID = 1L;
	
	public BrushListener getListener() {
		return listener;
	}

	public ModeButton(BrushListener listener){
		this.listener=listener;
		setBackground(Color.WHITE);
		setOpaque(false);
		setMinimumSize(new Dimension(20,30));
	
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		listener.drawIcon(g);
	}

}
