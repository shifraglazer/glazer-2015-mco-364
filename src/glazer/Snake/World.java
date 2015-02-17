package glazer.Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class World extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SnakeBody snake;
	private Food food;
	private final int SIZE = 20;
	private final int WIDTH;
	private final int HEIGHT;
	private final int ARCSIZE=15;

	public World(int width, int height) throws IOException {
	
		setMinimumSize(new Dimension(width,height));
		setPreferredSize(new Dimension(width,height));
		WIDTH = width;
		HEIGHT = height;
		System.out.println(this.getWidth());
		food = new Food(SIZE);
		snake = new SnakeBody(WIDTH, HEIGHT, SIZE,ARCSIZE);
		plantFood();
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(185,122,87));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		snake.drawSnake(g);
		food.drawFood(g);
	}

	public void updateSnake(String direction) {
		snake.moveForward(direction);
	}

	public void checkEat() {
		if (snake.headAt(food.getX(), food.getY())) {
			snake.getLonger();
			plantFood();
		}
	}
	public boolean checkGameOver(){
		if(snake.bangWall()){
			JOptionPane.showMessageDialog(null, "bang wall");
			return true;
			
		}
		else if(snake.bangSnake()){
			JOptionPane.showMessageDialog(null, "bang snake");
			return true;
		}
		else{
			return false;
		}
	}
	public void plantFood() {
		Random random = new Random();
		int x;
		int y;
		do {
			x = random.nextInt((WIDTH-SIZE) / 20);
			y = random.nextInt((HEIGHT-SIZE) / 20);
		} while (snake.occupies(x * 20, y * 20));
		food.setX(x * 20);
		food.setY(y * 20);
		System.out.println("Food x " + x + " y " + y);
	}

}
