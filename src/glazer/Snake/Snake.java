package glazer.snake;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Snake extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	private ScheduledExecutorService executor;
	private ScheduledExecutorService executeMusic;
	private String direction;
	private World world;
	// private Music music;
	// game controls
	private boolean gameStart;
	private boolean sound;
	private boolean gameOver;
	// menu
	private JMenu options;
	private JMenuBar menu;
	private JMenuItem restartGame;
	private JMenuItem mute;

	// private JMenu gameOptions;
	// private boolean changing;

	public Snake() throws IOException {

		setLayout(new BorderLayout());
		setTitle("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setResizable(false);
		setLocationRelativeTo(null);

		mute = new JMenuItem("Mute");
		mute.addActionListener(muteGame);
		gameStart = false;

		restartGame = new JMenuItem("Play Game");
		restartGame.addActionListener(playGame);
		this.world = new World(300, 300, 5);
		world.addKeyListener(this);
		world.setFocusable(true);
		container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(world, BorderLayout.CENTER);

		sound = true;
		// changing = false;

		// set up menu
		setUpMenu();

		direction = "Up";
		gameOver = false;
		// music = new Music();
		setIconImage(new Food(20).getFood());
		executor = Executors.newScheduledThreadPool(1);
		executeMusic = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(paint, 0, 400, TimeUnit.MILLISECONDS);
		executeMusic.scheduleAtFixedRate(playMusic, 0, 20, TimeUnit.SECONDS);
		pack();

	}

	public void setUpMenu() {
		menu = new JMenuBar();
		menu.add(mute);
		options = new JMenu("Levels");
		JMenuItem[] play = new JMenuItem[3];
		String[] playOptions = { "Beginner", "Intermediate", "Advanced" };
		for (int i = 0; i < play.length; i++) {
			play[i] = new JMenuItem(playOptions[i]);
			play[i].addActionListener(listener);
			options.add(play[i]);
		}
		menu.add(restartGame);
		menu.add(options);
		this.setJMenuBar(menu);
	}

	Runnable playMusic = new Runnable() {
		public void run() {

			if (sound) {
				Music m = new Music();
				m.start();
			}
		}

	};
	Runnable paint = new Runnable() {
		public void run() {

			if (!gameOver && gameStart) {

				world.updateSnake(direction);
				try {
					world.checkEat();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				gameOver = world.checkGameOver();

				container.repaint();
			}

		}

	};

	@Override
	public void keyReleased(KeyEvent e) {

		// 4 or left arrow then turn to left
		// 6 or right then turn to the right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_6
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD6) {

			direction = "Right";
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_4
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
			// component.goLeft();
			direction = "Left";

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_2
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD2) {

			direction = "Down";
		} else if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_8
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD8) {

			direction = "Up";
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	ActionListener playGame = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			// changing = true;
			try {
				world.recreateWorld(300, 300, 5);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			direction = "Up";

			// changing = false;
			gameOver = false;
			world.setGameStart(true);
			gameStart = true;
		}

	};
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String option = (String) item.getText();
			option = option.toLowerCase();
			try {
				switch (option) {
				case "beginner": {
					// changing=true;
					world.recreateWorld(300, 300, 5);
					break;
				}

				case "intermediate": {
					// changing = true;
					world.recreateWorld(300, 300, 10);
					break;
				}
				case "advanced": {
					// changing = true;
					world.recreateWorld(300, 300, 8);
					break;
				}
				}
				gameOver = false;
				// changing = false;
				world.setGameStart(true);
				gameStart = true;
				direction = "Up";
				executeMusic.shutdownNow();
				executeMusic = Executors.newScheduledThreadPool(1);
				executeMusic.scheduleAtFixedRate(playMusic, 0, 20,
						TimeUnit.SECONDS);
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
	};
	ActionListener muteGame = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (sound) {
				sound = false;
				world.setSound(false);
				executeMusic.shutdownNow();
			} else {
				sound = true;
				world.setSound(true);
				executeMusic = Executors.newScheduledThreadPool(1);
				executeMusic.scheduleAtFixedRate(playMusic, 0, 20,
						TimeUnit.SECONDS);
			}

		}

	};

	public static void main(String args[]) {
		Snake snake;
		try {
			snake = new Snake();
			snake.setVisible(true);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
