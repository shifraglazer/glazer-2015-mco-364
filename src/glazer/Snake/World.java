package glazer.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class World extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// sizes
	private final int SIZE = 20;
	private final int WIDTH;
	private final int HEIGHT;
	private final int ARCSIZE = 15;

	// game objects
	private SnakeBody snake;
	private Food food;
	private PlantRocks rock;
	private PlantGrass grass;
	private Image rockImg;
	private int numRocks;

	// game controls
	private boolean sound;
	private boolean gameOver;
	private boolean gameStart;

	private Random random;

	public World(int width, int height, int num) throws IOException {

		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		WIDTH = width;
		HEIGHT = height;
		numRocks = num;
		random = new Random();
		gameStart = false;
		sound = true;
		food = new Food(SIZE);

		// set up board
		snake = new SnakeBody(WIDTH, HEIGHT, SIZE, ARCSIZE);
		rock = new PlantRocks(SIZE, width, height);
		grass = new PlantGrass(width, height);

		plantRocks(num);
		plantFood();

		rockImg = ImageIO.read(getClass().getResource("rock.jpg"));

	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public void plantRocks(int num) throws IOException {
		int x;
		int y;
		int count = 0;
		while (count < num) {
			do {
				x = random.nextInt((WIDTH - SIZE) / 20) * 20;
				y = random.nextInt((HEIGHT - SIZE) / 20) * 20;
			} while (snake.occupies(x, y) || rock.occupies(x, y)
					|| snake.occupies(x, y - 1) || snake.occupies(x, y - 2));
			rock.createRocks(x, y);
			count++;
		}

	}

	public void movingRocks(Graphics g) throws IOException {
		int x;
		int y;
		int count = 0;
		int num = 5;
		while (count < num) {
			do {
				x = random.nextInt((WIDTH - SIZE) / 20) * 20;
				y = random.nextInt((HEIGHT - SIZE) / 20) * 20;
			} while (snake.occupies(x, y) || rock.occupies(x, y)
					|| snake.occupies(x, y - 1) || snake.occupies(x, y - 2));
			// new Rock(SIZE, x, y).drawRock(g);
			g.drawImage(rockImg, x, y, SIZE, SIZE, null);
			;
			count++;
		}
	}

	public void recreateWorld(int width, int height, int num)
			throws IOException {
		// recreates the board when start new game
		gameOver = false;
		food = new Food(SIZE);
		snake = new SnakeBody(WIDTH, HEIGHT, SIZE, ARCSIZE);
		this.rock = new PlantRocks(SIZE, width, height);
		grass = new PlantGrass(width, height);
		this.numRocks = num;
		plantRocks(num);
		plantFood();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(185, 122, 87));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (gameStart) {
			if (gameOver) {
				drawGameOver(g);
			} else {
				try {
					if (numRocks == 8) {
						movingRocks(g);
					}

					grass.drawGrass(g);
					snake.drawSnake(g);
					rock.drawRocks(g);
				} catch (IOException e) {

					e.printStackTrace();
				}
				food.drawFood(g);

				g.setColor(Color.YELLOW);
				g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
			}
		} else {
			drawInstructions(g);
		}
	}

	public void setGameStart(boolean start) {
		this.gameStart = start;
	}

	public void drawInstructions(Graphics g) {
		// border snake
		for (int i = 0; i < WIDTH; i += 15) {
			g.setColor(Color.GREEN);
			g.fillRoundRect(0, i, 15, 15, 10, 10);
			g.setColor(Color.BLACK);
			g.drawRoundRect(0, i, 15, 15, 10, 10);
		}
		g.setColor(Color.GREEN);
		g.fillRoundRect(15, 0, 15, 15, 10, 10);
		g.setColor(Color.BLACK);
		g.drawRoundRect(15, 0, 15, 15, 10, 10);
		g.setColor(Color.GREEN);
		g.fillRoundRect(30, 0, 15, 15, 10, 10);
		g.setColor(Color.BLACK);
		g.drawRoundRect(30, 0, 15, 15, 10, 10);

		// snake face
		g.setColor(Color.BLUE);
		g.fillOval(((int) (15 / 3) + 30), ((int) (15 / 4) + 0), (int) (15 / 5),
				(int) (15 / 5));
		g.fillOval(((int) (15 / 3 * 2) + 30), ((int) (15 / 4) + 0),
				(int) (15 / 5), (int) (15 / 5));
		g.setColor(Color.RED);
		g.drawArc(((int) (15 / 3) + 30), ((int) (15 / 4 * 2) + 0),
				(int) (15 / 2), (int) (15 / 3), 0, -180);

		// instructions
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GREEN);
		Font font = new Font("Serif", Font.BOLD, 30);
		g2.setFont(font);
		g2.drawString("SNAKE", 105, 50);
		font = new Font("Serif", Font.PLAIN, 20);
		g2.setFont(font);
		g2.drawString("Game Rules:", 30, 80);
		font = new Font("Serif", Font.PLAIN, 15);
		g2.setFont(font);
		g2.drawString("Eat apples to grow", 30, 107);

		g2.drawString("Do not bang into walls, rocks or snake", 30, 137);
		font = new Font("Serif", Font.BOLD, 12);
		g2.setFont(font);
		g2.drawString("Optional features:", 80, 170);
		font = new Font("Serif", Font.PLAIN, 12);
		g2.setFont(font);
		g2.drawString("MUTE", 40, 190);
		g2.drawString("CHOOSE LEVEL", 180, 190);
		font = new Font("Serif", Font.PLAIN, 12);
		g2.setFont(font);
		g2.drawString("Toggles sound", 20, 210);
		g2.drawString("Background music/ sound effects", 20, 230);
		g2.drawString("Beginner/ Intermediate/ Advanced", 130, 210);
		g2.drawString("(apple crunch, bang into wall, snake, rocks)", 20, 250);
		font = new Font("Serif", Font.PLAIN, 12);
		g2.setFont(font);
		g2.drawString("Navigate with numberpad, arrows, or numbers", 30, 287);
	}

	public void drawGameOver(Graphics g) {
		// snake caught up
		for (int i = 0; i < WIDTH; i += 15) {
			g.setColor(Color.GREEN);
			g.fillRoundRect(0, i, 15, 15, 10, 10);
			g.fillRoundRect(i, 0, 15, 15, 10, 10);
			g.fillRoundRect(WIDTH - 15, i, 15, 15, 10, 10);
			g.fillRoundRect(i, HEIGHT - 15, 15, 15, 10, 10);
			g.setColor(Color.BLACK);
			g.drawRoundRect(0, i, 15, 15, 10, 10);
			g.drawRoundRect(i, 0, 15, 15, 10, 10);
			g.drawRoundRect(WIDTH - 15, i, 15, 15, 10, 10);
			g.drawRoundRect(i, HEIGHT - 15, 15, 15, 10, 10);

		}
		// snake frown
		g.setColor(Color.BLUE);
		g.fillOval(((int) (15 / 3) + 0), ((int) (15 / 4) + 0), (int) (15 / 5),
				(int) (15 / 5));
		g.fillOval(((int) (15 / 3 * 2) + 0), ((int) (15 / 4) + 0),
				(int) (15 / 5), (int) (15 / 5));
		g.setColor(Color.RED);
		g.drawArc(((int) (15 / 3) + 0), ((int) (15 / 4 * 3) + 0),
				(int) (15 / 2), (int) (15 / 3), 0, 180);

		// game over
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GREEN);
		Font font = new Font("Serif", Font.BOLD, 80);
		g2.setFont(font);
		g2.drawString("GAME", 30, 120);
		g2.drawString("OVER", 30, 220);
	}

	public void updateSnake(String direction) {
		snake.moveForward(direction);
	}

	public void checkEat() throws InterruptedException {
		if (snake.headAt(food.getX(), food.getY())) {
			snake.setEating(true);
			if (sound) {
				CrunchSound sound = new CrunchSound();
				sound.start();
			}

			plantFood();
		} else {
			snake.setEating(false);
			snake.removeLast();
		}
	}

	public boolean checkGameOver() {
		if (snake.bangWall()) {
			if (sound) {
				Bang2 sound = new Bang2();
				sound.start();
			}
			gameOver = true;
			return true;

		} else if (snake.bangSnake()) {
			gameOver = true;
			return true;
		} else if (bangRock()) {
			gameOver = true;
			return true;
		} else {
			return false;
		}
	}

	public void plantFood() {

		int x;
		int y;
		do {
			x = random.nextInt((WIDTH - SIZE) / 20) * 20;
			y = random.nextInt((HEIGHT - SIZE) / 20) * 20;
		} while (snake.occupies(x, y) || rock.occupies(x, y));
		food.setX(x);
		food.setY(y);

	}

	public boolean bangRock() {
		if (rock.occupies(snake.getSnake().getFirst().getX(), snake.getSnake()
				.getFirst().getY())) {
			Bang2 sound = new Bang2();
			sound.start();
			return true;
		} else {
			return false;
		}
	}

}
