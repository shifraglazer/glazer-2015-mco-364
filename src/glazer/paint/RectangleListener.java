package glazer.paint;

import java.awt.Graphics;

public class RectangleListener extends BrushListener{

	public RectangleListener(Canvas canvas) {
		super(canvas);
		
	}

	@Override
	public void draw(Graphics g) {
		int width = (int) Math.abs(startX - lastX);
		int height = (int) Math.abs(startY - lastY);
		
		if (lastX> startX && lastY > startY) {
			g.drawRect(startX, startY, width, height);
		} else if (lastX < startX && lastY < startY) {
			g.drawRect(startX-width, startY-height, width, height);
		} else if (lastX < startX && lastY > startY) {
			g.drawRect(startX-width, startY, width, height);
		} else if (lastX > startX && lastY < startY) {
			g.drawRect(startX,startY-height, width, height);
		}
	}

}
