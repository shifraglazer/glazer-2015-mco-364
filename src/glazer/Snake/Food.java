package glazer.Snake;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;

public class Food extends Image{


	
	public Food(){
		this.setImage(getClass().getResource("mouse.jpg"));
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 20;
	}
}
