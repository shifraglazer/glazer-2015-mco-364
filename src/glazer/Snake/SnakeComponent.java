package glazer.Snake;

import java.awt.Graphics;

import javax.swing.JComponent;

public class SnakeComponent extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private int fwidth;
	private int fheight;
	public SnakeComponent(){
		width=100;
		height=30;
		fwidth=300;
		fheight=600;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(width,height, 20, 20);
		g.drawRect(fwidth,fheight,20,20);
		
	}
	public void goRight(){
		width+=20;
	}
	

}
