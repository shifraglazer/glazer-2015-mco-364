package airplane;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class Rain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Rain(){
		/*
		setSize(200,200);
		setBackground(Color.GRAY);
		RainComponent rain=new RainComponent();
	
		add(rain);
		*/
		
	}
	public void generateRain(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		Random random=new Random();
		int count=800;
		int x;
		int y;
		while(count>0){
			if(count<200){
				g.setColor(Color.GRAY);
			}
			x=random.nextInt(600);
			y=random.nextInt(600);
			g.fillRect(x, y, 1, 3);
			count--;
		}
}
	public static void main(String agrs[]){
		/*
		Rain rain=new Rain();
		rain.setVisible(true);
		 
		 Thread t=new Thread(){
		
			 public void run(){
				 while(true){
					
				 rain.repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 }
			 } 
	 };
		 t.start();
	
			
	*/
		
	}
}
