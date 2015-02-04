package glazer.GUI;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class Smile extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Smile(){
		setSize(800,800);
		setTitle("Smile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container= getContentPane();
		
		container.setLayout(new BorderLayout());
		SmileComponent smile=new SmileComponent();
		
		container.add(smile);
		smile.setSize(container.getSize());
	}
	 public static void main(String args[]){
		 Smile s=new Smile();
		 s.setVisible(true);
		 Thread t=new Thread(){
		
			 public void run(){
				 while(true){
				 s.repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 }
			 } 
	 };
		 t.start();

}
}
