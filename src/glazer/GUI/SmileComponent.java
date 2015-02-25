package glazer.gui;

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
		int width=container.getWidth();
		int height=container.getHeight();
		int smileWidth=(int)(width*.8);
		int smileHeight=(int)(height*.8);
		int smileX=(width-smileWidth)/2;
		int smileY=(height-smileHeight)/2;
		g.setColor(Color.YELLOW);
		g.fillOval(smileX,smileY, smileWidth, smileHeight);
		g.setColor(Color.green);
		g.fillOval((int)((smileWidth/2)+smileX), (int)((smileHeight/2)+smileY), (int)(smileWidth*.10),(int)(smileHeight*.10));
		g.setColor(Color.BLUE);
		int eyeWidth=(int)(smileWidth*.08);
		int eyeHeight=(int)(smileHeight*.08);
		int eyeY=(int)((smileHeight*.25)+smileY);
		g.fillOval((int)(smileWidth*.30)+smileX, eyeY, eyeWidth, eyeHeight);
		int eyeX=(int)(smileWidth*.70)+smileX;
		int eye2Y=(int)((smileHeight*.25)+smileY);
		
		switch (count) {
		case 0: {
			g.fillOval(eyeX, eye2Y, eyeWidth, eyeHeight);
			break;
			
		}
		case 1: {
			g.fillArc(eyeX, eye2Y+1, eyeWidth, eyeHeight, 0, 180);
			g.fillArc(eyeX, eye2Y, eyeWidth, eyeHeight, 0, -180);
			break;
		}
		case 2: {
			g.fillArc(eyeX, eye2Y+3, eyeWidth, eyeHeight, 0,180);
			g.fillArc(eyeX, eye2Y, eyeWidth, eyeHeight, 0, -180);
			break;
		}
		case 3: {
			g.fillArc(eyeX, eye2Y+5, eyeWidth, eyeHeight, 0, 180);
			g.fillArc(eyeX, eye2Y, eyeWidth, eyeHeight, 0, -180);
			break;
		}
		case 4: {
			g.fillArc(eyeX, eye2Y+6, eyeWidth, eyeHeight, 0, 180);
			g.fillArc(eyeX,eye2Y, eyeWidth, eyeHeight, 0, -180);
			break;
		}
		case 5: {
			g.fillArc(eyeX, eye2Y, eyeWidth, eyeHeight, 0, -180);
			g.setColor(Color.YELLOW);
			g.fillArc(eyeX, eye2Y, eyeWidth, (int)(eyeHeight*.6), 0, -180);
			break;
		}
		case 6: {
			g.fillArc(eyeX, eye2Y, eyeWidth, eyeHeight, 0, -180);
			g.setColor(Color.YELLOW);
			g.fillArc(eyeX, eye2Y, eyeWidth, (int)(eyeHeight*.68), 0, -180);
			break;
		}
		case 7: {
			g.fillArc(eyeX, eye2Y, eyeWidth, eyeHeight, 0, -180);
			g.setColor(Color.YELLOW);
			g.fillArc(eyeX, eye2Y, eyeWidth, (int)(eyeHeight*.9), 0, -180);
			break;
		}
		case 8: {
			g.fillArc(eyeX, eye2Y,eyeWidth, eyeHeight, 0, -180);
			g.setColor(Color.YELLOW);
			g.fillArc(eyeX, eye2Y, eyeWidth, (int)(eyeHeight*.88), 0, -180);

			break;
		}
		case 9: {
			
			g.drawArc(eyeX, eye2Y, eyeWidth, eyeHeight, 0, -180);
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
		g.drawArc((int)((smileWidth*.25)+smileX), (int)((smileHeight*.45)+smileY),(int)(smileWidth*.50) , (int)(height*.3), 0, -180);

	}

	public SmileComponent(Container container) {
		this.container=container;
		this.count = 0;
		this.close = false;
		this.setSize(container.getSize());
	}

}
