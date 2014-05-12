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
		this.x=pos.x;
		this.y=pos.y;
	}

	public int getLengthX() {
		return this.x * GameController.unitSize;
	}

	public int getLengthY() {
		return this.y * GameController.unitSize;
	}

	public Position obtainNewPosition(Position pos, String direction) {
		Position posAux = new Position(pos.getX(), pos.getY());
		switch (direction) {
		case "North":
			posAux.setY(posAux.getY() - 1);
			break;
		case "South":
			posAux.setY(posAux.getY() + 1);
			break;

		case "East":
			posAux.setX(posAux.getX() + 1);
			break;
		case "West":
			posAux.setX(posAux.getX() - 1);
			break;
		default:
			break;
		}
		return posAux;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position other = (Position) obj;
			return (other.x== x && other.y == y);
		}
		return super.equals(obj);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" +x + "," + y  +")";
	}
}
