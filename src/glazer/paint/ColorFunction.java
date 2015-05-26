package glazer.paint;

import java.awt.Color;

public class ColorFunction extends Function{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	public ColorFunction(Canvas canvas,Color color,FunctionListener function) {
		super(canvas);
		this.color=color;
		setOpaque(true);
		this.setBackground(color);
		addActionListener(function);
		
	}

	public void setColor(Color color){
		this.color=color;
	}
	@Override
	public void perform() {
		canvas.setColor(color);
		
	}

}
