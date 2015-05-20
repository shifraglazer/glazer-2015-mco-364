package glazer.paint;

import javax.swing.JButton;

public class ModeButton extends JButton{

	/**
	 * 
	 */
	private BrushListener listener;
	private static final long serialVersionUID = 1L;
	
	public BrushListener getListener() {
		return listener;
	}

	public ModeButton(BrushListener listener){
		this.listener=listener;
	
	}

}
