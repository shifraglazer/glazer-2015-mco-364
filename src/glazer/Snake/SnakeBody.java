package glazer.Snake;

import java.util.ArrayList;
import java.util.Random;

public class SnakeBody {

	private ArrayList<SnakeCell> snake;
	private int lastX;
	private int lastY;
	
	public SnakeBody(){
		snake=new ArrayList<SnakeCell>();
		Random random=new Random();
		int x=random.nextInt();
		SnakeCell first=new SnakeCell();
	}
	public void getLonger(){
		snake.add(new SnakeCell())
	}
}
