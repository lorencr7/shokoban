package sd.emse.shokoban;

import java.util.ArrayList;

/**
 * This class verify if two shapes collide betweem them
 */
public abstract class Collisionable extends Shape {

	public Collisionable(Position position) {
		super(position);
	}

	/**
	 * @param shape1
	 * @param shape2
	 * @return  True if shape 1 and shape 2 are collisionable object
	 * . False if one of the shape or both are not collisionable object
	 */
	public boolean collide(Shape shape1, Shape shape2) {
		if (isCollisionable(shape1) && isCollisionable(shape2)) {
			return true;
		}
		return false;
	}
	/*
	 *
	 */
	/**
	 * @param shape
	 * @return True if the shape is Collisionable.
	 * 		False if the shape is not Collisionable
	 */
	private boolean isCollisionable(Shape shape) {
		return (shape instanceof Collisionable);
	}
	
	/**
	 *
	 * @param shapes
	 * @return Shape that collide with the actual shape
	 */
	public Shape findCollisionableShape(ArrayList<Shape> shapes) {
		for (Shape shape : shapes) {
			if (this.collide(this, shape)) {
				return shape;
			}
		}
		return null;
	}
}
