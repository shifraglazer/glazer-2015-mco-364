package glazer.Snake;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Snake extends JFrame implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	private ScheduledExecutorService executor;
	public Snake(){
		setSize(800,800);
		setTitle("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container= getContentPane();
		executor = Executors.newScheduledThreadPool(1);
		SnakeComponent component=new SnakeComponent();
		component.addKeyListener(this);
		component.setFocusable(true);
		
		Runnable paint = new Runnable() {
			public void run() {
			component.goRight();
			container.repaint();
			}
		};
		executor.scheduleAtFixedRate(paint, 0, 1, TimeUnit.SECONDS);	
		container.add(component);
	}
	public static void main(String args[]){
		Snake snake=new Snake();
		snake.setVisible(true);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		// 4 or left arrow then turn to left
		//6 or right then turn to the right
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
