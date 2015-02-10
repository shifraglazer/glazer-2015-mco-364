package glazer.Project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ground extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ScheduledExecutorService executor;
	private Container container;
	private JPanel navigationPanel;
	private JButton up;
	private JButton down;
	private JButton right;
	private JButton left;

	public Ground() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(getContentPane().getMaximumSize());
		this.setResizable(false);
		this.setTitle("Airplane Simulator");
		container = getContentPane();
		container.setLayout(new BorderLayout());
		Airplane plane = new Airplane();
		container.add(plane, BorderLayout.CENTER);
	
		Runnable refresh = new Runnable() {
			public void run() {

				container.repaint();
			}
		};
		navigationPanel=new JPanel();
		up = new JButton();
		down = new JButton();
		right = new JButton();
		left = new JButton();
		container.add(navigationPanel, BorderLayout.NORTH);
		navigationPanel.add(up);
		navigationPanel.add(down);
		navigationPanel.add(right);
		navigationPanel.add(left);
		up.setText("North");
		down.setText("South");
		right.setText("East");
		left.setText("West");
		
		executor = Executors.newScheduledThreadPool(1);
		ActionListener goUp = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				plane.goNorth();	
			}

		};
		ActionListener goDown = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				plane.goSouth();

			}

		};
		ActionListener goLeft = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				plane.goWest();

			}

		};
		ActionListener goRight = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				plane.goEast();

			}

		};
		up.addActionListener(goUp);
		down.addActionListener(goDown);
		right.addActionListener(goRight);
		left.addActionListener(goLeft);
		executor.scheduleAtFixedRate(refresh, 0, 10, TimeUnit.MILLISECONDS);
	}

	public static void main(String args[]) {
		Ground ground = new Ground();
		ground.setVisible(true);
	}
}
