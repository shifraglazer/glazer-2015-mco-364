package glazer.GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Track extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public Track(){
		
	}
	public void paintComponent(Graphics g){
		g.fillRect(20, 20, 400, 400);
		g.setColor(Color.GREEN);
		g.fillRect(420/4, 440/4, 350, 350);
		
	}
}
