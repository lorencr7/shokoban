package sd.emse.shokoban;

import java.util.ArrayList;

public class Box extends Collisionable {
	
	public Box(Position position) {
		super(position);
		this.setImageName("sokoban/box.jpg");
		this.setIndex(2);
	}
	
	@Override
	public void move(Direction direction, ArrayList<Shape> shapes) {
		Position previousPosition = this.getPosition().getNextPosition(direction);
		ArrayList<Shape> previousShapes = this.getShapesAt(previousPosition, shapes);
		ArrayList<Shape> currentShapes = this.getShapesAt(this.getPosition(), shapes);
		previousShapes.addAll(currentShapes);
		//Look in the previous position and my current position: perhaps the player has moved before, 
		//and it can be occupying my position
		boolean playerFound = false;
		for (Shape shape : previousShapes) {//Search the player
			if (shape instanceof Player) {
				playerFound = true;
			}
		}
		if (playerFound) {//In this case, I would need to move. 
			Position nextPosition = this.getPosition().getNextPosition(direction);
			ArrayList<Shape> nextShapes = this.getShapesAt(nextPosition, shapes);
			Shape collisionableShape = this.findCollisionableShape(nextShapes);
			if (collisionableShape == null) {
				this.performMove(direction);
			}
		}
	}
	
	@Override
	public boolean canBePushed() {
		return true;
	}
}
