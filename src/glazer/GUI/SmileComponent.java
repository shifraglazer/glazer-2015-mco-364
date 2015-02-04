package glazer.GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class SmileComponent extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */


	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.fillOval(200, 100, 400, 400);
		g.setColor(Color.BLUE);
		g.fillOval(325, 150, 25, 25);
		g.fillOval(450, 150, 25, 25);
		g.setColor(Color.RED);
		g.drawArc(350, 300, 112, 112, 0, -180);
		
		
		
	}

	
	
}
