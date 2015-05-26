package glazer.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class RectangleOutLineListener extends BrushListener{

	public RectangleOutLineListener(Canvas canvas) {
		super(canvas);
		
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2=setUpGraphics(g);
		int width = (int) Math.abs(startX - lastX);
		int height = (int) Math.abs(startY - lastY);
		
		if (lastX> startX && lastY > startY) {
			g2.drawRect(startX, startY, width, height);
		} else if (lastX < startX && lastY < startY) {
			g2.drawRect(startX-width, startY-height, width, height);
		} else if (lastX < startX && lastY > startY) {
			g2.drawRect(startX-width, startY, width, height);
		} else if (lastX > startX && lastY < startY) {
			g2.drawRect(startX,startY-height, width, height);
		}
		canvas.repaint();
	}

	@Override
	void drawIcon(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(7,7,27,12);
		
	}

}
