package sd.emse.shokoban;

import java.util.ArrayList;

public class Player extends Collision {

	public Player(Position position) {
		super(position);
		this.setImageName("sokoban/player.png");
		this.setIndex(2);
	}

	@Override
	public void move(Direction direction, ArrayList<Shape> shapes) {
		Position nextPosition = this.getPosition().getNextPosition(direction);
		ArrayList<Shape> nextShapes = this.getShapesAt(nextPosition, shapes);
		boolean collisionableFoundOnNext = false;
		for (Shape shape : nextShapes) {//Miro en las siguientes figuras si hay alguna colisionable
			if (this.isCollisionable(shape)) {//Si una de las siguientes es colisionable
				collisionableFoundOnNext = true;
				if (shape instanceof Box) {//Si es una caja, todavia puedo moverme
					Position nextNextPosition = nextPosition.getNextPosition(direction);
					ArrayList<Shape> nextNextShapes = this.getShapesAt(nextNextPosition, shapes);
					boolean collisionableFoundOnNextNext = false;
					for (Shape shape2 : nextNextShapes) {//Busco alguna figura colisionable en la siguiente de la siguiente
						if (this.isCollisionable(shape2)) {
							collisionableFoundOnNextNext = true;
						}
					}
					if (!collisionableFoundOnNextNext) {//Si la siguiente de la siguiente no es colisionable, me puedo mover
						this.performMove(direction);
					}
				}
			} 
		}
		if (!collisionableFoundOnNext) {//Si en la siguiente casilla no hay figuras colisionables, me puedo mover
			this.performMove(direction);
		}
	}
	
	@Override
	public Player clone() {
		Player shape = new Player(this.getPosition());
		return shape;
    }
}
