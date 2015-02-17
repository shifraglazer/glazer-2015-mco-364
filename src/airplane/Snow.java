package airplane;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class Snow {
	private static final long serialVersionUID = 1L;
	public Snow(){
		/*
		setSize(200,200);
		setBackground(Color.GRAY);
		SnowComponent snow=new SnowComponent();
	
		add(snow);
		*/
		
	}
	public void generateSnow(Graphics g) {
		g.setColor(Color.WHITE);
		Random random=new Random();
		int count=800;
		int x;
		int y;
		int d;
		while(count>0){
			x=random.nextInt(600);
			y=random.nextInt(600);
			d=random.nextInt(4);
			g.fillOval(x, y, d, d);
			count--;
		}
	}
	public static void main(String agrs[]){
		/*
	
		Snow snow=new Snow();
		snow.setVisible(true);
		 
		 Thread t=new Thread(){
		
			 public void run(){
				 while(true){
					
				 snow.repaint();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 }
			 } 
	 };
		 t.start();
	
			}
	
		
	*/

}
}
