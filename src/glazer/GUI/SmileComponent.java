package glazer.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JComponent;

public class SmileComponent extends JComponent {
	private int count;
	private boolean close;
	private Container container;
	
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
		g.setColor(Color.green);
		g.fillOval(390, 270, 40,30);
		g.setColor(Color.BLUE);
		g.fillOval(325, 200, 25, 25);
		
		
		switch (count) {
		case 0: {
			g.fillOval(450, 200, 25, 25);
			break;
			
		}
		case 1: {
			g.fillArc(450, 201, 25, 25, 0, 180);
			g.fillArc(450, 200, 25, 25, 0, -180);
			break;
		}
		case 2: {
			g.fillArc(450, 203, 25, 25, 0, 180);
			g.fillArc(450, 200, 25, 25, 0, -180);
			break;
		}
		case 3: {
			g.fillArc(450, 205, 25, 25, 0, 180);
			g.fillArc(450, 200, 25, 25, 0, -180);
			break;
		}
		case 4: {
			g.fillArc(450, 206, 25, 25, 0, 180);
			g.fillArc(450, 200, 25, 25, 0, -180);
			break;
		}
		case 5: {
			g.fillArc(450, 200, 25, 25, 0, -180);
			g.setColor(Color.YELLOW);
			g.fillArc(450, 200, 25, 15, 0, -180);
			break;
		}
		case 6: {
			g.fillArc(450, 200, 25, 25, 0, -180);
			g.setColor(Color.YELLOW);
			g.fillArc(450, 200, 25, 17, 0, -180);
			break;
		}
		case 7: {
			g.fillArc(450, 200, 25, 25, 0, -180);
			g.setColor(Color.YELLOW);
			g.fillArc(450, 200, 25, 20, 0, -180);
			break;
		}
		case 8: {
			g.fillArc(450, 200, 25, 25, 0, -180);
			g.setColor(Color.YELLOW);
			g.fillArc(450, 200, 25, 22, 0, -180);

			break;
		}
		case 9: {
			
			g.drawArc(450, 200, 25, 25, 0, -180);
			break;
		}
		}
		if(close && count <9){
			count++;
		}
		else if( count==9){
			close=false;
			count--;
			
		}
		else if(!close&& count>0){
		if(count==8){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			count --;
		}
		else if(!close && count==0){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
			close=true;
		}
		
	
		 
		g.setColor(Color.RED);
		g.drawArc(340, 300, 130, 112, 0, -180);

	}

	public SmileComponent(Container container) {
		this.container=container;
		this.count = 0;
		this.close = false;
		this.setSize(container.getSize());
	}

}
