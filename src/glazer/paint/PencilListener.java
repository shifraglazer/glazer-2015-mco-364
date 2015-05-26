package glazer.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PencilListener extends BrushListener {


	public PencilListener(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void draw(Graphics g) {
		Graphics graphics=canvas.getImage().getGraphics();
		Graphics2D g2= setUpGraphics(graphics);
		g2.drawLine(startX,startY,lastX,lastY);
		startX=lastX;
		startY=lastY;
	}

	@Override
	void drawIcon(Graphics g) {
		ImageIcon pencilPic = new ImageIcon(getClass()
				.getResource("pencil.png"));
		pencilPic=new ImageIcon(pencilPic.getImage().getScaledInstance(20, 20,
				Image.SCALE_SMOOTH));
		g.drawImage(pencilPic.getImage(),7,4,null);
	}



}
