package sd.emse.shokoban;

import java.util.ArrayList;

public class Box extends Collision {
	
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
		previousShapes.addAll(currentShapes);//Tengo que buscar en la posicion anterior a la que me quiero mover y la mia actual, porque puede ser que el jugador ya se haya movido a mi posicion
		boolean playerFound = false;
		for (Shape shape : previousShapes) {//Busco el jugador
			if (shape instanceof Player) {
				playerFound = true;
			}
		}
		if (playerFound) {//Si he encontrado el jugador, puede que me tenga que mover
			Position nextPosition = this.getPosition().getNextPosition(direction);
			ArrayList<Shape> nextShapes = this.getShapesAt(nextPosition, shapes);
			boolean collisionableFoundOnNext = false;
			for (Shape shape : nextShapes) {//busco entre las figuras de la casilla siguiente
				if (this.isCollisionable(shape)) {
					collisionableFoundOnNext = true;
				}
			}
			if (!collisionableFoundOnNext) {//Si en la siguiente no hay ninguna colisionable, me muevo
				this.performMove(direction);
			}
		}
	}
	
	@Override
	public Box clone() {
		Box shape = new Box(this.getPosition());
		return shape;
    }
}
