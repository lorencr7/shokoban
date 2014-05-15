package sd.emse.shokoban;

import java.util.ArrayList;


public abstract class Collision extends Shape {

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
	
	public Shape findCollisionableShape(ArrayList<Shape> shapes) {
		for (Shape shape : shapes) {
			if (this.collide(this, shape)) {
				return shape;
			}
		}
		return null;
	}
}
