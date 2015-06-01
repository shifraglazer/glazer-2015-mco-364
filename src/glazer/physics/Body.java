package glazer.physics;

import java.awt.geom.Point2D;

public class Body {

	private Force force;
	private Point2D.Double position;

	public Body(double x, double y, Force force) {
		position = new Point2D.Double(x, y);
		this.force = force;
	}

	public void addForce(Force gravity) {
		force.add(gravity);
	}

	public void update() {
		position.x += force.getX();
		position.y += force.getY();
	}

	public String toString() {
		return position.x + " " + position.y;
	}

	public double getX() {
		return position.x;
	}

	public double getY() {
		return position.y;

	}
}
