package sd.emse.shokoban;

import java.util.ArrayList;

public class Player extends Collisionable {

	public Player(Position position) {
		super(position);
		this.setImageName("sokoban/player.png");
		this.setIndex(2);
	}
	@Override
	public void move(Direction direction, ArrayList<Shape> shapes) {
		Position nextPosition = this.getPosition().getNextPosition(direction);
		ArrayList<Shape> nextShapes = this.getShapesAt(nextPosition, shapes);
		Shape collisionableShape = this.findCollisionableShape(nextShapes);
		if (collisionableShape == null) {
			this.performMove(direction);
		} else {
			if (collisionableShape.canBePushed()) {
				Position nextNextPosition = nextPosition.getNextPosition(direction);
				ArrayList<Shape> nextNextShapes = this.getShapesAt(nextNextPosition, shapes);
				Shape collisionableShape2 = this.findCollisionableShape(nextNextShapes);
				if (collisionableShape2 == null) {
					this.performMove(direction);
				}
			}
			
		}
	}
}
