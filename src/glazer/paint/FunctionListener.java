package glazer.paint;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class FunctionListener extends AbstractAction{
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		Function function=(Function) e.getSource();
		function.perform();
	}
}
