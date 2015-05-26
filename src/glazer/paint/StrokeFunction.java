package glazer.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class StrokeFunction extends Function{

	private static final long serialVersionUID = 1L;

	private int stroke;
	public StrokeFunction(Canvas canvas,ImageIcon icon,int stroke) {
		super(canvas);
		setIcon(icon);
		this.stroke=stroke;
		addActionListener(new FunctionListener());
	
		
	}

	public void setStroke(int stroke){
		this.stroke=stroke;
	}
	@Override
	public void perform() {
		canvas.setStroke(stroke);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(stroke));
		//g2.drawLine(0, 7, 30, 7);
	}

}
