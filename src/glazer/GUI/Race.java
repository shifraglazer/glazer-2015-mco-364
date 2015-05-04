package glazer.GUI;

import javax.swing.JFrame;

public class Race extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Track track;
	public Race(){
		this.setSize(600, 600);
		setLocationRelativeTo(null);
		track=new Track();
		 this.getContentPane().add(track);
	}
	public static void main(String args[]){
		Race race=new Race();
		race.setVisible(true);
	}
}
