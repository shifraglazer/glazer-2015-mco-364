package glazer.Snake;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;



public class SnakeBody {

	private LinkedList<SnakeCell> snake;	
	private final int SIZE;
	private final int ARCSIZE;
	private int width;
	private int height;
	private boolean bangSnake;
	private String direction;

	public SnakeBody(int width, int height, int size, int arcSize) {
		snake = new LinkedList<SnakeCell>();
		ARCSIZE=arcSize;
		SIZE = size;
		this.width=width;
		this.height=height;
		Random random = new Random();
		int x;
		int y;
		do{
		 x = random.nextInt((width-SIZE)/20);
		 y = random.nextInt((height-SIZE)/20);
		}
		while(x<=1||y<=1);
		System.out.println("snake x "+ x+ " y "+ y);
		SnakeCell first = new SnakeCell(x*20, y*20, SIZE, SIZE,ARCSIZE);
		snake.addFirst(first);
		this.direction="Up";
		
	}
	
	public LinkedList<SnakeCell> getSnake(){
		return snake;
	}
	public void getLonger() {
		SnakeCell cell=null;
		switch(direction){
		case "Up":{
			
			 cell = new SnakeCell((snake.getLast().getX()), snake.getLast().getY() + SIZE, SIZE, SIZE,ARCSIZE);
			break;
		}
		case "Down":{
			 cell = new SnakeCell(snake.getLast().getX(), snake.getLast().getY()-SIZE, SIZE, SIZE,ARCSIZE);
			break;
		}
		case "Right":{
			 cell = new SnakeCell((snake.getLast().getX() - SIZE), snake.getLast().getY(), SIZE, SIZE,ARCSIZE);
			break;
		}
		case "Left":{
			cell = new SnakeCell((snake.getLast().getX() + SIZE), snake.getLast().getY(), SIZE, SIZE,ARCSIZE);
			break;
		}
		}
		snake.addLast(cell);

	
	}

	public void removeLast(){
		snake.removeLast();
	}
	public void moveForward(String direction){
		this.direction=direction;
		SnakeCell cell=null;
		switch(direction){
		case "Up":{
			
			 cell = new SnakeCell((snake.getFirst().getX()), snake.getFirst().getY() - SIZE, SIZE, SIZE,ARCSIZE);
			break;
		}
		case "Down":{
			 cell = new SnakeCell(snake.getFirst().getX(), snake.getFirst().getY()+SIZE, SIZE, SIZE,ARCSIZE);
			break;
		}
		case "Right":{
			 cell = new SnakeCell((snake.getFirst().getX() + SIZE), snake.getFirst().getY(), SIZE, SIZE,ARCSIZE);
			break;
		}
		case "Left":{
			cell = new SnakeCell((snake.getFirst().getX() - SIZE), snake.getFirst().getY(), SIZE, SIZE,ARCSIZE);
			break;
		}
		}
		if(occupies(cell.getX(),cell.getY())){
			BangSound sound=new BangSound();
			sound.start();
			bangSnake=true;
		}
		else{
		snake.addFirst(cell);
		//snake.removeLast();
		}
	}
	public void drawSnake(Graphics g) throws IOException {	
	
		Iterator<SnakeCell> iter=snake.iterator();
		if(direction.equals("Up")||direction.equals("Down")){
		iter.next().drawHeadUpDown(g);
		}
		else{
			iter.next().drawHeadSide(g);
		}
		while(iter.hasNext()){
			iter.next().createCell(g);
		}
	}


	public boolean occupies(int x,int y){
		Iterator<SnakeCell> iter=snake.iterator();
		while(iter.hasNext()){
			SnakeCell cell=iter.next();
			if(cell.getX()==x&&cell.getY()==y){
				BangSound sound=new BangSound();
				sound.start();
				return true;
			}
		}
		return false;
	}
	public boolean headAt(int x,int y){
		if(snake.getFirst().getX()==x&& snake.getFirst().getY()==y){
			return true;
		}
		return false;
	}

	public boolean bangWall() {
		SnakeCell first=snake.getFirst();
		if(first.getX()<0||first.getX()>width||first.getY()<0||first.getY()>height){
			Bang2 sound=new Bang2();
			sound.start();
			return true;
		}
		return false;
	}

	public boolean bangSnake() {
		return bangSnake;
	}

	public void setEating(boolean bool) {
		snake.getFirst().setEating(bool);
		
	}
}
