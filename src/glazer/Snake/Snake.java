package glazer.Snake;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Snake extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	private ScheduledExecutorService executor;
	private String direction;
	private World world;
	private boolean gameOver;
	private Music music;

	public Snake() throws IOException {
		setLayout(new BorderLayout());
		setTitle("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container = getContentPane();
		executor = Executors.newScheduledThreadPool(1);
		music=new Music();
		world=new World(200,200,music);
		world.addKeyListener(this);
		world.setFocusable(true);
		
		direction = "Up";
		gameOver=false;
		Runnable paint = new Runnable() {
			public void run() {
			
				if(!gameOver){
				
				world.updateSnake(direction);
				try {
					world.checkEat();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gameOver=world.checkGameOver();
				if(gameOver){
					JOptionPane.showMessageDialog(null, "Game Over");
					
				}
				container.repaint();
				}
				
			}
			
		};
		setIconImage(new Food(20).getFood());
		executor.scheduleAtFixedRate(paint, 0, 500, TimeUnit.MILLISECONDS);
		container.add(world,BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		music.start();
	}

	public static void main(String args[]) {
		Snake snake;
		try {
			snake = new Snake();
			snake.setVisible(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// 4 or left arrow then turn to left
		// 6 or right then turn to the right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			direction = "Right";
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			//component.goLeft();
			direction = "Left";

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			direction = "Down";
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {

			direction = "Up";
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
