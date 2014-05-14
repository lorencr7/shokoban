package sd.emse.shokoban;


import model.GameController;

public class Position {
	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @param pos
	 */
	public Position(Position pos) {
		this.x = pos.x;
		this.y = pos.y;
	}

	public int getLengthX() {
		return this.x * GameController.SQUARE_SIZE;
	}

	public int getLengthY() {
		return this.y * GameController.SQUARE_SIZE;
	}

	public Position getNextPosition(Direction direction) {
		Position posAux = new Position(this);
		switch (direction) {
		case NORTH:
			posAux.setY(posAux.getY() - 1);
			break;
		case SOUTH:
			posAux.setY(posAux.getY() + 1);
			break;
		case EAST:
			posAux.setX(posAux.getX() + 1);
			break;
		case WEST:
			posAux.setX(posAux.getX() - 1);
			break;
		default:
			break;
		}
		return posAux;
	}
	
	public Position getPreviousPosition(Direction direction) {
		Position posAux = new Position(this);
		switch (direction) {
		case NORTH:
			posAux.setY(posAux.getY() + 1);
			break;
		case SOUTH:
			posAux.setY(posAux.getY() - 1);
			break;
		case EAST:
			posAux.setX(posAux.getX() - 1);
			break;
		case WEST:
			posAux.setX(posAux.getX() + 1);
			break;
		default:
			break;
		}
		return posAux;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position position = (Position) obj;
			if (position.x == this.x && position.y == this.y) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}
	

	@Override
	public String toString() {
		return "(" +x + "," + y  +")";
	}
}
