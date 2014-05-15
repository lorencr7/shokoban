package sd.emse.shokoban;


public class Collision extends Shape {

	public Collision(Position position) {
		super(position);
	}

	public boolean collide(Shape shape1, Shape shape2) {
		if (isCollisionable(shape1) && isCollisionable(shape2)) {
			return true;
		}
		return false;
	}

	private boolean isCollisionable(Shape shape) {
		return (shape instanceof Collision);
	}
}
