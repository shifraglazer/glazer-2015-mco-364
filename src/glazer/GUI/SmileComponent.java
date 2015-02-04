package glazer.GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class SmileComponent extends JComponent{
	private int count;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */


	@Override
	protected void paintComponent(Graphics g) {
		count++;
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.fillOval(200, 100, 400, 400);
		if(count%2==0){
		
		g.setColor(Color.BLUE);
		g.drawArc(325, 200, 25, 25, 0, -180);
		g.drawArc(450, 200, 25, 25, 0, -180);
	
		}
		else{
			g.setColor(Color.BLUE);
			g.fillOval(325, 200, 25, 25);
			g.fillOval(450, 200, 25, 25);	
		}
		g.setColor(Color.RED);
		g.drawArc(340, 300, 130, 112, 0, -180);
		
		
	}

	
	
}
