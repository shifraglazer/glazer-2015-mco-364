package glazer.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Smile extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	private ScheduledExecutorService executor;
	public Smile(){
		setSize(800,800);
		setTitle("Smile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container= getContentPane();
		executor = Executors.newScheduledThreadPool(1);
		SmileComponent smile=new SmileComponent(container);
		Runnable paint = new Runnable() {
			public void run() {
				smile.setSize(container.getSize());
			container.repaint();
			}
		};
		executor.scheduleAtFixedRate(paint, 0, 10, TimeUnit.MILLISECONDS);	
		container.setLayout(new BorderLayout());
		
		
		container.add(smile,BorderLayout.CENTER);
		
	}
	 public static void main(String args[]){
		 Smile s=new Smile();
		 s.setVisible(true);
		 /*
		 Thread t=new Thread(){
		
			 public void run(){
				 while(true){
					
				 s.repaint();
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
