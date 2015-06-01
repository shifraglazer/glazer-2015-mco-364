package glazer.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class World extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Random random = new Random();
	private List<Body> list;
	private Force gravity;

	public World() {
		setSize(600, 600);
		gravity = new Force(0, -9.8);
		list = new ArrayList<Body>();
		Force force = new Force(0, 0);
		for (int i = 0; i < 45; i++) {
			double x = random.nextInt(600);
			double y = random.nextInt(600);
			list.add(new Body(x, y, new Force(0, 0)));
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		// g.translate(0, 600);
		g.setColor(Color.BLACK);
		for (int i = 0; i < list.size(); i++) {
			Body body = list.get(i);
			body.addForce(gravity);
			body.update();
			System.out.println(body);
			g.drawOval((int) body.getX(), (int) body.getY(), 2, 2);
		}
	}

	public Force getGravity() {
		return gravity;
	}

	public void setGravity(Force gravity) {
		this.gravity = gravity;
	}

	public void updateFrame() {
		for (int i = 0; i < list.size(); i++) {
			Body body = list.get(i);
			gravity.add(gravity);
			body.update();
			// System.out.println(body);
		}
		repaint();
	}
}
