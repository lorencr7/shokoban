package sd.emse.shokoban;

import model.GameController;


public class Collision {

	public boolean collide(Shape shape1, Shape shape2) {
		if (isCollisionable(shape1) && isCollisionable(shape2)) {
			return true;

		}
		return false;
	}

	private boolean isCollisionable(Shape shape) {
		if ((shape instanceof Box) || (shape instanceof Player)
				|| (shape instanceof Wall)) {
			return true;
		}

		return false;
	}
	
	


}
